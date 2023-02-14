package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.BarcodeToInformationBean;
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

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ShowProductInfoOpenFoodFactsAPIBoundary{

    private static ShowProductInfoOpenFoodFactsAPIBoundary instance;

    public void findProductInfoByBarcode(BarcodeToInformationBean bean) throws IOException, ParseException, FailedQueryToOpenFoodFacts {
        String barcode = bean.getBarcodeSearch();
        System.out.format("I will search for barcode: %s\n", barcode);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONParser parser = new JSONParser();
        // Send a GET request to the API
        HttpGet request = new HttpGet("https://world.openfoodfacts.org/api/v0/product/" + barcode + ".json");
        CloseableHttpResponse response = httpClient.execute(request);

        // Read the response
        String json = EntityUtils.toString(response.getEntity());

        // Parse the JSON response
        JSONObject obj = (JSONObject) parser.parse(json);
        JSONObject product = (JSONObject) obj.get("product");
        if(product == null){
            throw new FailedQueryToOpenFoodFacts("product not found!");
        }
        // Extract the product data
        String name = (String) product.get("product_name");
        String image = (String) product.get("image_url");
        String ingredients = (String) product.get("ingredients_text");

        JSONObject nutritionalValues = (JSONObject) product.get("nutriments");
        JSONObject alternativeNutritionalValues = (JSONObject) product.get("nutriscore_data");
        int fruitPercentage;
        try{
            fruitPercentage = Math.toIntExact((Long) nutritionalValues.get("fruits-vegetables-nuts-estimate-from-ingredients_100g"));
        } catch (ClassCastException e){
            fruitPercentage = (int) Math.floor((Double) nutritionalValues.get("fruits-vegetables-nuts-estimate-from-ingredients_100g"));
        } catch (NullPointerException e){
            fruitPercentage = 0;
        }

        System.out.format("FruitPercentage: %s\n", fruitPercentage);
        float energy = Float.parseFloat(String.valueOf(nutritionalValues.get("energy")));
        System.out.format("Energy: %s\n", energy);
        float sugars = Float.parseFloat(String.valueOf(nutritionalValues.get("sugars")));
        System.out.format("Sugars: %s\n", sugars);
        float protein = Float.parseFloat(String.valueOf(nutritionalValues.get("proteins")));
        System.out.format("Proteins: %s\n", protein);
        float saturated_fat = Float.parseFloat(String.valueOf(nutritionalValues.get("saturated-fat")));
        System.out.format("Saturated_fats: %s\n", saturated_fat);
        float fiber = Float.parseFloat(String.valueOf(alternativeNutritionalValues.get("fiber")));
        System.out.format("Fibers: %s\n", fiber);
        float salt = Float.parseFloat(String.valueOf(nutritionalValues.get("salt")));
        System.out.format("Salt: %s\n", salt);

        JSONArray additivesArray = (JSONArray) product.get("additives_original_tags");
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
        bean.setName(name);
        bean.setImage(image);
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
        try{
            return labels.contains("organic");
        } catch (NullPointerException e){
            return false;
        }
    }

    private ShowProductInfoOpenFoodFactsAPIBoundary(){}

    public static ShowProductInfoOpenFoodFactsAPIBoundary getInstance() {
        if(instance == null){
            instance = new ShowProductInfoOpenFoodFactsAPIBoundary();
        }
        return instance;
    }
}
