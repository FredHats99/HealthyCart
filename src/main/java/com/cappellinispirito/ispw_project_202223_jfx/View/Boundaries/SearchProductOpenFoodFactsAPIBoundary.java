package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.ResultsFromSearchBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameImageBarcodeFromSearchBeanClass;
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
import java.util.HashMap;
import java.util.List;

public class SearchProductOpenFoodFactsAPIBoundary {
    private static SearchProductOpenFoodFactsAPIBoundary instance;

    private SearchProductOpenFoodFactsAPIBoundary(){}

    public static SearchProductOpenFoodFactsAPIBoundary getInstance() {
        if(instance==null){
            instance = new SearchProductOpenFoodFactsAPIBoundary();
        }
        return instance;
    }

    public void findProductByName(NameImageBarcodeFromSearchBeanClass bean) throws IOException, ParseException {

        List<String> resultsNames = new ArrayList<>();
        List<String> resultsImages = new ArrayList<>();
        List<String> resultsBarcodes = new ArrayList<>();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONParser parser = new JSONParser();

        // Send a GET request to the API
        HttpGet request = new HttpGet("https://world.openfoodfacts.org/api/v0/search.json?search_terms=" + bean.getNameToSearch());
        CloseableHttpResponse response = httpClient.execute(request);

        // Read the response
        String json = EntityUtils.toString(response.getEntity());

        // Parse the JSON response
        JSONObject obj = (JSONObject) parser.parse(json);
        JSONArray products = (JSONArray) obj.get("products");

        // Convert the products array to a list of Product objects
        for (Object product : products) {
            JSONObject productData = (JSONObject) product;
            String barcode = (String) productData.get("code");
            String productName = (String) productData.get("product_name");
            String image = (String) productData.get("image_url");
            resultsNames.add(productName);
            resultsImages.add(image);
            resultsBarcodes.add(barcode);
        }

        bean.setResultsNames(resultsNames);
        bean.setResultsImages(resultsImages);
        bean.setResultsBarcodes(resultsBarcodes);
    }
}
