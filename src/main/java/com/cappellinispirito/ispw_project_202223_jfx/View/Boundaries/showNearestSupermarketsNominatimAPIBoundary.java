package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.positionBean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class showNearestSupermarketsNominatimAPIBoundary implements APIProxyBoundary{

    JSONArray jsonArray;
    @Override
    public void getNearestSupermarkets(positionBean bean) throws Exception {
        String address = bean.getAddress();

        String url = "https://nominatim.openstreetmap.org/search?q=" + address + "&format=json&limit=5&addressdetails=1&polygon_svg=1&namedetails=1";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        jsonArray = (JSONArray) JSONValue.parse(response.toString());
        extractInformation(bean, jsonArray);
    }

    public static void extractInformation(positionBean bean, JSONArray supermarketsArray) {
        for (Object obj : supermarketsArray) {
            JSONObject supermarket = (JSONObject) obj;
            String name = (String) supermarket.get("display_name");
            bean.appendToSupermarketsNames(name);
            String address = (String) supermarket.get("address");
            bean.appendToSupermarketsAddress(address);
            Float distance = (Float) supermarket.get("distance");
            bean.appendToSupermarketsDistance(distance);
        }
    }
}
