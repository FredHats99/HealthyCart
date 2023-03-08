package com.cappellinispirito.ispwproject202223jfx.view.boundaries;


import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.BarcodeToInformationBean;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowProductInfoOpenFoodFactsAPIBoundary{

    private static ShowProductInfoOpenFoodFactsAPIBoundary instance;

    public void findProductInfoByBarcode(BarcodeToInformationBean bean) throws IOException, ParseException {
        String barcode = bean.getBarcodeSearch();
        CloseableHttpClient httpClient = null;
        try{
            httpClient = HttpClients.createDefault();
            JSONParser parser = new JSONParser();
            // Send a GET request to the API
            HttpGet request = new HttpGet("https://world.openfoodfacts.org/api/v0/product/" + barcode + ".json");
            CloseableHttpResponse response = httpClient.execute(request);

            // Read the response
            String json = EntityUtils.toString(response.getEntity());

            // Parse the JSON response
            JSONObject obj = (JSONObject) parser.parse(json);
            JSONObject product = (JSONObject) obj.get("product");

            float energy;
            float sugars;
            float protein;
            float saturatedFat;
            float fiber;
            float salt;
            int fruitPercentage;

            // Extract the product data
            String name = (String) product.get("product_name");
            String image = (String) product.get("image_url");
            String ingredients = (String) product.get("ingredients_text");

            JSONObject nutritionalValues = (JSONObject) product.get("nutriments");
            JSONObject alternativeNutritionalValues = (JSONObject) product.get("nutriscore_data");

            fruitPercentage = getFruitPercentageParsed(nutritionalValues);

            energy = Float.parseFloat(String.valueOf(nutritionalValues.get("energy")));
            sugars = Float.parseFloat(String.valueOf(nutritionalValues.get("sugars")));
            protein = Float.parseFloat(String.valueOf(nutritionalValues.get("proteins")));
            saturatedFat = Float.parseFloat(String.valueOf(nutritionalValues.get("saturated-fat")));
            fiber = Float.parseFloat(String.valueOf(alternativeNutritionalValues.get("fiber")));
            salt = Float.parseFloat(String.valueOf(nutritionalValues.get("salt")));

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
            bean.setSaturatedFats(saturatedFat);
            bean.setFibers(fiber);
            bean.setSalt(salt);
            bean.setIsBiological(isBio);
            bean.setIsBeverage(isBeverage);
            bean.setIngredients(ingredients);
            bean.setAdditives(additivesList);
            bean.setName(name);
            bean.setImage(image);
        } catch (IOException | NumberFormatException e){
            Logger logger = Logger.getLogger(ShowProductInfoOpenFoodFactsAPIBoundary.class.getName());
            logger.log(Level.INFO, e.getMessage());
        } catch (NullPointerException e){
            //TODO: Some products don't provide the values needed to calculate the score, but still have a nutri-score provided. It should be used whenever nutritional values are not found...
            Logger logger = Logger.getLogger(ShowProductInfoOpenFoodFactsAPIBoundary.class.getName());
            logger.log(Level.INFO,String.format("Couldn't find further information for this product...%n"));
            bean.setCalories((float) -1);
            bean.setSugars((float) -1);
            bean.setProteins((float) -1);
            bean.setSalt((float) -1);
            bean.setSaturatedFats((float) -1);
            bean.setFibers((float) -1);
            bean.setFruitPercentage((float) -1);
            bean.setIsBiological(false);
            bean.setIsBeverage(false);
        } finally {
            assert httpClient != null;
            httpClient.close();
        }

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
    private int getFruitPercentageParsed(JSONObject nutritionalValues){
        int fp;
        try{
            fp = Math.toIntExact((Long) nutritionalValues.get("fruits-vegetables-nuts-estimate-from-ingredients_100g"));
        } catch (ClassCastException e){
            fp = (int) Math.floor((Double) nutritionalValues.get("fruits-vegetables-nuts-estimate-from-ingredients_100g"));
        } catch (NullPointerException e){
            fp = 0;
        }
        return fp;
    }
}
