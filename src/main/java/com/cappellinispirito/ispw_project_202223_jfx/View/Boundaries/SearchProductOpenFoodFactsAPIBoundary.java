package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
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
import java.net.URLEncoder;
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

    public void findProductByName(NameImageBarcodeFromSearchBeanClass bean) throws IOException, ParseException, FailedQueryToOpenFoodFacts {

        List<String> resultsNames = new ArrayList<>();
        List<String> resultsImages = new ArrayList<>();
        List<String> resultsBarcodes = new ArrayList<>();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONParser parser = new JSONParser();

        // Send a GET request to the API
        System.out.println("Connecting...");
        String search = bean.getNameToSearch().replace(" ", "%20");
        HttpGet request = new HttpGet("https://world.openfoodfacts.org/cgi/search.pl?search_terms="+ search +"&search_simple=1&action=process&json=1&tagtype_0=languages&tag_contains_0=contains&tag_0=italian");
        CloseableHttpResponse response = httpClient.execute(request);

        // Read the response
        System.out.println("Providing results..");
        String json = EntityUtils.toString(response.getEntity());

        // Parse the JSON response
        JSONObject obj = (JSONObject) parser.parse(json);
        JSONArray products = (JSONArray) obj.get("products");
        if (products.isEmpty()) {
            throw new FailedQueryToOpenFoodFacts("Products not found!");
        }
        // Convert the products array to a list of Product objects
        int i;
        for (i=0;i<6;i++) {
            //Dunno why but products provided are always 2*3 = 6...
            JSONObject productData = (JSONObject) products.get(i);
            String barcode = (String) productData.get("code");
            String productName = (String) productData.get("product_name");
            String image = (String) productData.get("image_url");

            System.out.println(barcode);
            System.out.println(image);
            System.out.println(productName);

            resultsNames.add(productName);
            resultsImages.add(image);
            resultsBarcodes.add(barcode);
        }

        bean.setResultsNames(resultsNames);
        bean.setResultsImages(resultsImages);
        bean.setResultsBarcodes(resultsBarcodes);
    }
}
