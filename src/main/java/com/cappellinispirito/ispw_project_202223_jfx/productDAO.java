package com.cappellinispirito.ispw_project_202223_jfx;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface productDAO {
    Item getProductByBarcode(String barcode) throws IOException, ParseException;
    List<String> searchProducts(String query) throws IOException, ParseException;

    boolean isBeverage(JSONObject product);

    boolean isOrganic(JSONObject product) throws IOException;
}
