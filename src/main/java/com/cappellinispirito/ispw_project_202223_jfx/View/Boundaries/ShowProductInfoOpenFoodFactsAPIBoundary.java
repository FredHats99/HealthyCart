package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.BarcodeToInformationBean;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ShowProductInfoOpenFoodFactsAPIBoundary{
    private CloseableHttpClient httpClient;
    private JSONParser parser;
    private static ShowProductInfoOpenFoodFactsAPIBoundary instance;

    public void findProductInfoByBarcode(BarcodeToInformationBean bean) throws IOException, ParseException {
        String barcode = bean.getBarcodeSearch();
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

        JSONArray additivesArray = (JSONArray) JSONValue.parse((Reader) product.get("additives"));
        List<String> additivesList = new ArrayList<>();
        int i;
        for(i=0;i< additivesArray.size();i++){
            additivesList.add((String) additivesArray.get(i));
        }
        // Check if the "organic" label is present in the labels array

        boolean isBio = isOrganic(product);
        boolean isBeverage = isBeverage(product);

        bean.setFruitPercentage((float) fruitPercentage);
        bean.setCalories(energy);
        bean.setSugars(sugars);
        bean.setProteins(protein);
        bean.setSaturatedFats(saturated_fat);
        bean.setFibers(fiber);
        bean.setSalt(salt);
        bean.setIsBiological(isBio);
        bean.setIsBeverage(isBeverage);
        bean.setIngredients(ingredients);
        bean.setAdditives(additivesList);
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

    private ShowProductInfoOpenFoodFactsAPIBoundary(){}

    public static ShowProductInfoOpenFoodFactsAPIBoundary getInstance() {
        if(instance == null){
            instance = new ShowProductInfoOpenFoodFactsAPIBoundary();
        }
        return instance;
    }
}
