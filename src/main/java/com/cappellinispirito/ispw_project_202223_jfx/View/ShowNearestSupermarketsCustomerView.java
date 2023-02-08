package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ShowNearestSupermarketsController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nearestSupermarketBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.positionBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.nearestSupermarketBeanClass;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.positionBeanClass;

import java.util.HashMap;

public class ShowNearestSupermarketsCustomerView {
    //a lot of fxml logic...

    public void showNearestFrom(){
        //suppose I obtained user input...
        String search = "";
        nearestSupermarketBean bean = new nearestSupermarketBeanClass();
        bean.setSearch(search);
        //Should be singleton(?)
        ShowNearestSupermarketsController showNearestSupermarketsController = new ShowNearestSupermarketsController();
        showNearestSupermarketsController.getNearestSupermarkets(bean);

        //now display them pls!
    }
    //should use beans tho...
    public void pickSupermarket(String name, String address){
        ShowNearestSupermarketsController showNearestSupermarketsController = new ShowNearestSupermarketsController();
        showNearestSupermarketsController.createSupermarket(name, address);
    }
}
