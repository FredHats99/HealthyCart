package com.cappellinispirito.ispwproject202223jfx.view.boundaries;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.PositionBean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;



import java.util.*;


public class ShowNearestSupermarketsNominatimApiBoundary implements APIProxyBoundary{

    private final List<String> supermarketsName = new ArrayList<>();
    private final List<Float> supermarketsDistances = new ArrayList<>();


    private static final String API_KEY = "vq1WMd-HmyVaO3Clys3zdKn-WuXGZpoC-b8iQkHrxZRcuN3nALrvuu5zuJO-9ogE8L-QBsnutO3SYmchDTAVQfuinaTHsOg1JBfpN8UZ8OxRipd2gMa5O0xvHYYAZHYx";


    @Override
    public void getNearestSupermarkets(PositionBean bean) throws IOException {
        String address = bean.getAddress().replace(" ","+");
        List<String> whiteListSupermarkets = new ArrayList<>();
        whiteListSupermarkets.add("Conad");
        whiteListSupermarkets.add("Esselunga");
        whiteListSupermarkets.add("Carrefour");
        whiteListSupermarkets.add("Lidl");
        whiteListSupermarkets.add("Auchan");
        whiteListSupermarkets.add("Eurospin");
        whiteListSupermarkets.add("Md");
        whiteListSupermarkets.add("Todis");
        whiteListSupermarkets.add("Penny-market");
        whiteListSupermarkets.add("Coop");

        OkHttpClient client = new OkHttpClient();


        // Encode the address to be used in the API request URL
        String encodedAddress = address.replace(" ", "+");

        // Create the Yelp Fusion API request URL
        String url = "https://api.yelp.com/v3/businesses/search?location=" + encodedAddress +
                "&term=Supermarkets&categories=grocery&sort_by=distance";






        // Create the API request with authorization header
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + API_KEY)
                .build();

        // Execute the API request and get the response
        Response response = client.newCall(request).execute();

        // Get the response body as a string
        String responseBody = response.body().string();

        // Print the response body
        System.out.println(responseBody);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);

        // Get the array of business objects from the response
        JsonNode businessesNode = rootNode.get("businesses");

        // Loop through the array of business objects and print the distance value for each supermarket
        for (JsonNode businessNode : businessesNode) {
            String name = businessNode.get("name").asText();
            String marketAddress = businessNode.get("location").get("address1").asText();
            double distance = (businessNode.get("distance").asDouble())/1000;
            System.out.println(name + " - " + distance + " meters");
            if(whiteListSupermarkets.contains(name)){
                bean.appendToSupermarketsNames(name);
                bean.appendToSupermarketsAddress(marketAddress);
                bean.appendToSupermarketsDistance((float) Math.round(distance*100f)/100f);
            }
        }
    }
}



