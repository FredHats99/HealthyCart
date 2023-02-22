package com.cappellinispirito.ispwproject202223jfx.view.boundaries;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.SupermarketsToProductsBean;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchProductsFromSupermarketOpenFoodFactsAPIBoundary {
    List<String> sellableProductsNames = new ArrayList<>();
    List<String> sellableProductsImages = new ArrayList<>();
    List<String> sellableProductsBarcodes = new ArrayList<>();

    public void searchProductsBySupermarket(SupermarketsToProductsBean bean) throws IOException {
        CloseableHttpClient httpClient = null;
        try{
            httpClient = HttpClients.createDefault();
            JSONParser parser = new JSONParser();

            String supermarketName = bean.getSupermarket().getSupermarketName();

                // Replace spaces in the supermarket name with "%20" for the API call

                // Send a GET request to the API
                String search = supermarketName.replace(" ", "+");
                HttpGet request = new HttpGet("https://it.openfoodfacts.org/cgi/search.pl?search_terms="+ search +"&search_tag=supermarket&json=1");
                CloseableHttpResponse response = httpClient.execute(request);

                // Read the response
                String json = EntityUtils.toString(response.getEntity());

                // Parse the JSON response

                JSONObject obj = (JSONObject) parser.parse(json);
                JSONArray products = (JSONArray) obj.get("products");
                if (products.isEmpty()) {
                    throw new FailedQueryToOpenFoodFacts("Products not found!");
                }

                // Do something with the lists
                for (Object o : products) {
                    JSONObject product = (JSONObject) o;

                    sellableProductsNames.add((String) product.get("product_name"));
                    sellableProductsImages.add((String) product.get("image_url"));
                    sellableProductsBarcodes.add((String) product.get("code"));
                }
            bean.setSellableProductsNames(sellableProductsNames);
            bean.setSellableProductsImage(sellableProductsImages);
            bean.setSellableProductsBarcode(sellableProductsBarcodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert httpClient != null;
            httpClient.close();
        }

    }
}
