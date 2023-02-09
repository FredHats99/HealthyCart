package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.supermarketsToProductsBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SearchProductsFromSupermarketBoundary {

    List<Item> productsList;

    public void searchProductsBySupermarket(supermarketsToProductsBean bean) {
        String supermarketName = bean.getSupermarket().getSupermarketName();
        //TODO: Logic for retrieving a lot of items id
        //TODO: Logic for instantiating an Item class for each data collection
        //TODO: Logic for creating a List of references to those Items
        bean.setSoldProducts(productsList);
    }
}
