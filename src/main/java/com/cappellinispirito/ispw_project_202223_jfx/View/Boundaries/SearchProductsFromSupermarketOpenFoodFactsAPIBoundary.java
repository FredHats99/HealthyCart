package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.supermarketsToProductsBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SearchProductsFromSupermarketOpenFoodFactsAPIBoundary {

    List<String> sellableProductsNames;
    List<String> sellableProductsImages;
    List<String> sellableProductsBarcodes;

    public void searchProductsBySupermarket(supermarketsToProductsBean bean) throws FailedQueryToOpenFoodFacts{
        String supermarketName = bean.getSupermarket().getSupermarketName();
        try {
            // Replace spaces in the supermarket name with "%20" for the API call
            supermarketName = supermarketName.replace(" ", "%20");

            URL url = new URL("https://world.openfoodfacts.org/api/v0/product/search?tagtype_0=labels&tag_contains_0=contains&tag_0=" + supermarketName + "&page_size=20");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            JSONObject jsonResponse = (JSONObject) JSONValue.parse(response.toString());
            if(jsonResponse == null){
                throw new FailedQueryToOpenFoodFacts("Products for supermarket not found!");
            }
            JSONArray products = (JSONArray) jsonResponse.get("products");

            for (Object o : products) {
                JSONObject product = (JSONObject) o;
                sellableProductsNames.add((String) product.get("product_name"));
                sellableProductsImages.add((String) product.get("image_url"));
                sellableProductsBarcodes.add((String) product.get("code"));
            }
        } catch (Exception e) {
            System.out.println("An error occurred while calling the OpenFoodFacts API: " + e.getMessage());
        }
        bean.setSellableProductsNames(sellableProductsNames);
        bean.setSellableProductsImage(sellableProductsImages);
        bean.setSellableProductsBarcode(sellableProductsBarcodes);
    }
}
