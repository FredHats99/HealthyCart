package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.SingletonInstance;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.barcodeBean;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ShowProductInfoOpenFoodFactsAPIBoundary implements SingletonInstance {
    private CloseableHttpClient httpClient;
    private JSONParser parser;
    private SingletonInstance instance;

    public void findProductInfoByBarcode(barcodeBean bean) throws IOException, ParseException {
        String barcode = bean.getBarcode();
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

        String additives = String.valueOf(product.get("additives"));

        // Check if the "organic" label is present in the labels array

        boolean isBio = isOrganic(product);
        boolean isBeverage = isBeverage(product);

        bean.setFruitPercentage(fruitPercentage);
        bean.setEnergy(energy);
        bean.setSugars(sugars);
        bean.setProtein(protein);
        bean.setSaturatedFats(saturated_fat);
        bean.setFibers(fiber);
        bean.setSalt(salt);
        bean.setIsBio(isBio);
        bean.setIsBeverage(isBeverage);
        bean.setName(name);
        bean.setImageUrl(imageUrl);
        bean.setIngredients(ingredients);
        bean.setAdditives(additives);
    }

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

    public boolean isOrganic(JSONObject product) {
        String labels = (String) product.get("labels");
        return labels.contains("organic");
    }

    @Override
    public SingletonInstance getInstance() {
        if(instance == null){
            instance = new ShowProductInfoOpenFoodFactsAPIBoundary();
        }
        return instance;
    }
}
