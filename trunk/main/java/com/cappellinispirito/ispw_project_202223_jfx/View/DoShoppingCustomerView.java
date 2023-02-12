package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.doShoppingController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.shopBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.shopBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DoShoppingCustomerView {
    private shopBean bean;
    private String nameOfItemToAdd;
    private String nameOfItemToRemove;
    private int cartScore;
    private doShoppingController controller;
    private List<String> sellableProductsNames;
    private List<String> sellableProductsImages;



    public DoShoppingCustomerView() throws FailedQueryToOpenFoodFacts {
        //Whenever this View starts up, invokes a Controller for obtaining items to put into the cart.
        controller = new doShoppingController();
        bean = new shopBeanClass();
        controller.setUpShop(bean);
        sellableProductsNames = bean.getSellableProductName();
        sellableProductsImages = bean.getSellableProductImage();
    }

    public void addItem() throws SQLException, FailedQueryToOpenFoodFacts, IOException, ParseException {
        bean.setItemToAdd(nameOfItemToAdd);
        controller.addItemToCart(bean);
        getCartHealthScore();
    }

    public void removeItem(){
        bean.setItemToRemove(nameOfItemToRemove);
        controller.removeItemFromCart(bean);
        getCartHealthScore();
    }

    public void saveCart() throws SQLException {
        controller.saveCart();
    }

    public void getCartHealthScore(){
        cartScore = bean.getCartHealthScore();
    }
}
