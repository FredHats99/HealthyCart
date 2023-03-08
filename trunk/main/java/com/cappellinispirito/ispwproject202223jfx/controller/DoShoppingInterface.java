package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.ShopBean;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.view.Observer;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;

public interface DoShoppingInterface {
    void setUpShop(ShopBean bean) throws IOException;
    void addItemToCart(ShopBean bean) throws FailedQueryToOpenFoodFacts, IOException, ParseException, SQLException;
    void removeItemFromCart(ShopBean bean);
    void saveCart() throws SQLException;
    void loadNewPage() throws IOException, FailedQueryToOpenFoodFacts;
    void registerObserver(Observer observer);
}
