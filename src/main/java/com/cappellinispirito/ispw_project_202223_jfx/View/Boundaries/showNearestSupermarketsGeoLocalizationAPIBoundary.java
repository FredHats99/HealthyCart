package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.positionBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class showNearestSupermarketsGeoLocalizationAPIBoundary implements APIProxyBoundary{
    private static final String API_URL = "https://nominatim.openstreetmap.org/search?";

    @Override
    public void getNearestSupermarkets(positionBean bean) {
        String address = bean.getAddress();
        /*try {
            String urlString = API_URL +
                    "q=" + address +
                    "&format=json" +
                    "&limit=5";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output.append(line);
            }
            conn.disconnect();
            JSONArray results = new JSONArray(output.toString());
            for (int i = 0; i < results.length(); i++) {
                JSONObject result = results.getJSONObject(i);
                String name = result.getString("display_name");
                String address = result.getString("display_name");
                float distance = (float) result.getDouble("distance");
                supermarkets.add(new Supermarket(name, address, distance));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}
