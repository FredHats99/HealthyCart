package com.cappellinispirito.ispw_project_202223_jfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ProductDAOImpl implements productDAO {
    private CloseableHttpClient httpClient;
    private JSONParser parser;

    public ProductDAOImpl() {
        httpClient = HttpClients.createDefault();
        parser = new JSONParser();
    }

    @Override
    public Item getProductByBarcode(String barcode) throws IOException, ParseException {
        // Send a GET request to the API
        HttpGet request = new HttpGet("https://world.openfoodfacts.org/api/v0/product/" + barcode + ".json");
        CloseableHttpResponse response = httpClient.execute(request);

        // Read the response
        String json = EntityUtils.toString(response.getEntity());

        // Parse the JSON response
        JSONObject obj = (JSONObject) parser.parse(json);
        JSONObject product = (JSONObject) obj.get("product");

        // Extract the product data
        String name = (String) product.get("product_name");
        String imageUrl = (String) product.get("image_url");
        String ingredients = (String) product.get("ingredients_text");
        int fruitPercentage;
        try{
            fruitPercentage = (int) product.get("fruits_vegetables_nuts_100g");
        } catch (NullPointerException e){
            fruitPercentage = 0;
        }
        int energy = (int) product.get("energy");
        float sugars = (float) product.get("sugars");
        float protein = (float) product.get("proteins");
        float saturated_fat = (float) product.get("saturated-fat");
        float fiber = (float) product.get("fiber");
        float salt = (float) product.get("salt");
        JSONArray additives = (JSONArray) product.get("additives");

        // Convert the additives array to a list of strings
        List<String> additivesList = new ArrayList<>();
        for (Object additive : additives) {
            additivesList.add((String) additive);
        }


        // Check if the "organic" label is present in the labels array

        boolean isBio = isOrganic(product);
        boolean isBeverage = isBeverage(product);

        // Create a Product object
        Item p = new Item(energy, sugars, saturated_fat, salt, fruitPercentage, fiber, protein, additivesList,isBio,isBeverage,0, name);
        p.setImageUrl(imageUrl);
        p.setIngredients(ingredients);

        return p;
    }

    @Override
    public List<String> searchProducts(String name) throws IOException, ParseException {
        // Send a GET request to the API
        HttpGet request = new HttpGet("https://world.openfoodfacts.org/api/v0/search.json?search_terms=" + name);
        CloseableHttpResponse response = httpClient.execute(request);

        // Read the response
        String json = EntityUtils.toString(response.getEntity());

        // Parse the JSON response
        JSONObject obj = (JSONObject) parser.parse(json);
        JSONArray products = (JSONArray) obj.get("products");

        // Convert the products array to a list of Product objects
        List<String> productList = new ArrayList<>();
        for (Object product : products) {
            JSONObject productData = (JSONObject) product;
            //String barcode = (String) productData.get("code");
            String productName = (String) productData.get("product_name");
            productList.add(productName);
        }

        return productList;
    }


    @Override
    public boolean isBeverage(JSONObject product) {

        // Get the categories array
        JSONArray categories = (JSONArray) product.get("categories");

        // Check if the product is a beverage
        for (Object o : categories) {
            String category = (String) o;
            if (category.contains("beverages") || category.contains("drinks")) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isOrganic(JSONObject product) {
        // Get the labels array
        JSONArray labels = (JSONArray) product.get("labels");

        // Check if the "organic" label is present in the labels array
        for (Object label : labels) {
            if (label.equals("organic")) {
                return true;
            }
        }

        return false;
    }
}


