package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ShowNearestSupermarketsController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nearestSupermarketBeanInterface;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.nearestSupermarketBeanClass;

public class ShowNearestSupermarketsCustomerView {
    //a lot of fxml logic...

    public void showNearestFrom() throws Exception {
        //suppose I obtained user input...
        String address = "";
        nearestSupermarketBeanInterface bean = new nearestSupermarketBeanClass();
        bean.setSearch(address);
        //Should be singleton(?)
        ShowNearestSupermarketsController showNearestSupermarketsController = ShowNearestSupermarketsController.getInstance();
        showNearestSupermarketsController.getNearestSupermarkets(bean);


        //now display them pls!
    }
    //should use beans tho...
    public void pickSupermarket(String name, String address){
        ShowNearestSupermarketsController showNearestSupermarketsController = ShowNearestSupermarketsController.getInstance();
        showNearestSupermarketsController.createSupermarket(name, address);
    }
}
