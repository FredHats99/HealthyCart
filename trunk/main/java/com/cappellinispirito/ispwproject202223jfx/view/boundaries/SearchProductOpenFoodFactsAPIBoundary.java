package com.cappellinispirito.ispwproject202223jfx.view.boundaries;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NameImageBarcodeFromSearchBeanClass;
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

public class SearchProductOpenFoodFactsAPIBoundary {
    private static SearchProductOpenFoodFactsAPIBoundary instance;

    private SearchProductOpenFoodFactsAPIBoundary(){}

    public static SearchProductOpenFoodFactsAPIBoundary getInstance() {
        if(instance==null){
            instance = new SearchProductOpenFoodFactsAPIBoundary();
        }
        return instance;
    }

    public void findProductByName(NameImageBarcodeFromSearchBeanClass bean) throws IOException, ParseException, FailedQueryToOpenFoodFacts {

        List<String> resultsNames = new ArrayList<>();
        List<String> resultsImages = new ArrayList<>();
        List<String> resultsBarcodes = new ArrayList<>();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONParser parser = new JSONParser();

        // Send a GET request to the API
        String search = bean.getNameToSearch().replace(" ", "+");
        HttpGet request = new HttpGet("https://it.openfoodfacts.org/cgi/search.pl?search_terms="+ search +"&search_simple=1&action=process&json=1");
        CloseableHttpResponse response = httpClient.execute(request);

        // Read the response
        String json = EntityUtils.toString(response.getEntity());

        // Parse the JSON response
        JSONObject obj = (JSONObject) parser.parse(json);
        JSONArray products = (JSONArray) obj.get("products");
        if (products.isEmpty()) {
            throw new FailedQueryToOpenFoodFacts("Products not found!");
        }
        // Convert the products array to a list of Product objects
        int i = 0;
        int j = 0;
        JSONObject productData;
        while(i<6) {
            try{
                productData = (JSONObject) products.get(j);
            } catch (IndexOutOfBoundsException e){
                break;
            }

            String barcode = (String) productData.get("code");
            String productName = (String) productData.get("product_name");
            String image = (String) productData.get("image_url");

            if(image == null || productName == null){
                i--;
            } else {
                resultsNames.add(productName);
                resultsImages.add(image);
                resultsBarcodes.add(barcode);
            }
            i++;
            j++;
        }

        bean.setResultsNames(resultsNames);
        bean.setResultsImages(resultsImages);
        bean.setResultsBarcodes(resultsBarcodes);
    }
}
