package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.supermarketsToProductsBean;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class SearchProductsFromSupermarketOpenFoodFactsAPIBoundary {

    private static final int MAX_PRODUCTS = 20;
    List<String> sellableProductsNames = new ArrayList<>();
    List<String> sellableProductsImages = new ArrayList<>();
    List<String> sellableProductsBarcodes = new ArrayList<>();

    public void searchProductsBySupermarket(supermarketsToProductsBean bean) throws FailedQueryToOpenFoodFacts{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONParser parser = new JSONParser();

        String supermarketName = bean.getSupermarket().getSupermarketName();
        try {
            // Replace spaces in the supermarket name with "%20" for the API call

            // Send a GET request to the API
            System.out.println("Connecting...");
            String search = supermarketName.replace(" ", "+");
            HttpGet request = new HttpGet("https://it.openfoodfacts.org/cgi/search.pl?search_terms="+ search +"&search_tag=supermarket&json=1");
            CloseableHttpResponse response = httpClient.execute(request);

            // Read the response
            System.out.println("Providing results..");
            String json = EntityUtils.toString(response.getEntity());

            // Parse the JSON response

            JSONObject obj = (JSONObject) parser.parse(json);
            //System.out.println(obj);
            JSONArray products = (JSONArray) obj.get("products");
            if (products.isEmpty()) {
                throw new FailedQueryToOpenFoodFacts("Products not found!");
            }

            // Do something with the lists
        System.out.println(products);
        for (Object o : products) {
                JSONObject product = (JSONObject) o;

                sellableProductsNames.add((String) product.get("product_name"));
                sellableProductsImages.add((String) product.get("image_url"));
                sellableProductsBarcodes.add((String) product.get("code"));
            }
        } catch (Exception e) {
             //System.out.println("An error occurred while calling the OpenFoodFacts API: " + e.getMessage());
            e.printStackTrace();
        }
        bean.setSellableProductsNames(sellableProductsNames);
        bean.setSellableProductsImage(sellableProductsImages);
        bean.setSellableProductsBarcode(sellableProductsBarcodes);
    }
}
