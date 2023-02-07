package com.cappellinispirito.ispw_project_202223_jfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
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
        System.out.println(name);
        String imageUrl = (String) product.get("image_url");
        String ingredients = (String) product.get("ingredients_text");

        JSONObject nutritionalValues = (JSONObject) product.get("nutriments");
        JSONObject alternativeNutritionalValues = (JSONObject) product.get("nutriscore_data");

        int fruitPercentage = Math.toIntExact((Long) nutritionalValues.get("fruits-vegetables-nuts-estimate-from-ingredients_100g"));
        System.out.println(fruitPercentage);
        float energy = Float.parseFloat(String.valueOf(nutritionalValues.get("energy")));
        System.out.println(energy);
        float sugars = Float.parseFloat(String.valueOf(nutritionalValues.get("sugars")));
        System.out.println(sugars);
        float protein = Float.parseFloat(String.valueOf(nutritionalValues.get("proteins")));
        System.out.println(protein);
        float saturated_fat = Float.parseFloat(String.valueOf(nutritionalValues.get("saturated-fat")));
        System.out.println(saturated_fat);
        float fiber = Float.parseFloat(String.valueOf(alternativeNutritionalValues.get("fiber")));
        System.out.println(fiber);
        float salt = Float.parseFloat(String.valueOf(nutritionalValues.get("salt")));
        System.out.println(salt);

        JSONArray additives = (JSONArray) product.get("additives");
        // Convert the additives array to a list of strings

        List<String> additivesList = new ArrayList<>();
        try{
            for (Object additive : additives) {
                additivesList.add((String) additive);
            }
        } catch(NullPointerException ignored){}


        // Check if the "organic" label is present in the labels array

        boolean isBio = isOrganic(product);
        boolean isBeverage = isBeverage(product);

        // Create a Product object
        Item p = new Item(energy,sugars,saturated_fat,salt,fruitPercentage,fiber,protein, additivesList,isBio,isBeverage,0, name);
        p.setImageUrl(imageUrl);
        p.setIngredients(ingredients);

        return p;
    }

    @Override
    public boolean isBeverage(JSONObject product) {

        // Get the categories array
        JSONArray keywords = (JSONArray) product.get("_keywords");
        for (Object keyword : keywords) {
            if (keyword == "beverage") {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOrganic(JSONObject product) {
        String labels = (String) product.get("labels");
        return labels.contains("organic");
    }

}


