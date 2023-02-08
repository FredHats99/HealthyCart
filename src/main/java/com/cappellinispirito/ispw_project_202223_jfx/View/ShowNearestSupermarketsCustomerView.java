package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ShowNearestSupermarketsController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nearestSupermarketBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.nearestSupermarketBeanClass;

import java.util.HashMap;

public class ShowNearestSupermarketsCustomerView {
    //a lot of fxml logic...

    public void showNearestFrom(){
        //suppose I obtained user input...
        String search = "";
        nearestSupermarketBean bean = new nearestSupermarketBeanClass();
        bean.setSearch(search);
        ShowNearestSupermarketsController showNearestSupermarketsController = new ShowNearestSupermarketsController();
        showNearestSupermarketsController.searchProducts(bean);
        HashMap<String, String> supermarketsList = bean.getSupermarketList();

        //now display them pls!
    }
}
