package com.cappellinispirito.ispwproject202223jfx.view.boundaries;


import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.SupermarketsToProductsBean;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SearchProductsFromSupermarketOpenFoodFactsAPIBoundary {
    int interfacePage = 0;
    int apiPage=0;
    List<String> sellableProductsNames = new ArrayList<>();
    List<String> sellableProductsImages = new ArrayList<>();
    List<String> sellableProductsBarcodes = new ArrayList<>();
    JsonArray products;
    String search;

    public void searchProductsBySupermarket(SupermarketsToProductsBean bean) throws IOException {
        try{
            String supermarketName = bean.getSupermarket().getSupermarketName();
            search = supermarketName.replace(" ", "+");
            interfacePage =1;
            apiPage=1;
            getJsonFromOpenFoodFacts(false);

                // Do something with the lists
            int maxIndex = Math.min(products.size(), 9);
            for (int i=0;i<maxIndex;i++) {
                JsonObject product = products.get(i).getAsJsonObject();
                tryToGetName(product);
                tryToGetImage(product);
                sellableProductsBarcodes.add(product.get("code").getAsString());
            }
            bean.setSellableProductsNames(sellableProductsNames);
            bean.setSellableProductsImage(sellableProductsImages);
            bean.setSellableProductsBarcode(sellableProductsBarcodes);
            bean.setPage(interfacePage);
        } catch (Exception e) {
            Logger logger = Logger.getLogger(SearchProductsFromSupermarketOpenFoodFactsAPIBoundary.class.getName());
            logger.log(Level.INFO, e.getMessage());
        }
    }

    private void tryToGetImage(JsonObject product) {
        try{
            sellableProductsImages.add(product.get("image_url").getAsString());
        } catch (RuntimeException e){
            sellableProductsImages.add(null);
        }
    }

    public void searchProductsBySupermarketLoadNewPage(SupermarketsToProductsBean bean) throws IOException, FailedQueryToOpenFoodFacts {
        int pageItems = 9;
        try{
            for (int i = pageItems * interfacePage; i< pageItems *(interfacePage +1); i++) {
                JsonObject product = products.get(i).getAsJsonObject();
                tryToGetName(product);
                tryToGetImage(product);
                sellableProductsBarcodes.add(product.get("code").getAsString());
            }
        } catch (IndexOutOfBoundsException e){
            getJsonFromOpenFoodFacts(true);
            int oldIndex = 9-(24*(apiPage))%pageItems;
            for(int i = 0; i< oldIndex; i++){
                JsonObject product = products.get(i+24*apiPage).getAsJsonObject();
                tryToGetName(product);
                tryToGetImage(product);
                sellableProductsBarcodes.add(product.get("code").getAsString());
            }
            apiPage++;
        } finally {
            bean.setSellableProductsNames(sellableProductsNames);
            bean.setSellableProductsImage(sellableProductsImages);
            bean.setSellableProductsBarcode(sellableProductsBarcodes);
            bean.setPage(interfacePage);
            interfacePage++;
        }
    }

    private void tryToGetName(JsonObject product) {
        try{
            sellableProductsNames.add(product.get("product_name").getAsString());
        } catch (NullPointerException e){
            sellableProductsNames.add("???");
        }
    }
    private void getJsonFromOpenFoodFacts(boolean extend) throws IOException, FailedQueryToOpenFoodFacts {
        URL url;
        if(extend){
            url = new URL(String.format("https://it.openfoodfacts.org/store/%s?sort_by=popularity&page=%d&json=1", search, apiPage+1));
        } else {
            url = new URL(String.format("https://it.openfoodfacts.org/store/%s?sort_by=popularity&page=%d&json=1", search,1));
        }
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        Gson gson = new Gson();
        JsonObject rootObject = gson.fromJson(response.toString(), JsonObject.class);
        if (extend) {
            products.addAll(rootObject.getAsJsonArray("products"));
        } else {
            products = rootObject.getAsJsonArray("products");
        }

        // Parse the JSON response
        if (products.isEmpty()) {
            throw new FailedQueryToOpenFoodFacts("Products not found!");
        }
    }
}
