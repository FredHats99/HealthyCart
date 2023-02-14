package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ShowNearestSupermarketsController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nearestSupermarketBeanInterface;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.nearestSupermarketBeanClass;

import java.util.List;

public class ShowNearestSupermarketsCustomerView {
    private List<String> supermarketsNames;
    private List<String> supermarketsAddresses;
    private List<Float> supermarketsDistances;

    public void showNearestFrom(String address) throws Exception {
        nearestSupermarketBeanInterface bean = new nearestSupermarketBeanClass();
        bean.setSearch(address);
        ShowNearestSupermarketsController showNearestSupermarketsController = ShowNearestSupermarketsController.getInstance();
        showNearestSupermarketsController.getNearestSupermarkets(bean);
        supermarketsNames = bean.getSupermarketsNamesList();
        supermarketsAddresses = bean.getSupermarketsAddressesList();
        supermarketsDistances = bean.getSupermarketsNamesDistances();
        //now display them pls!
    }

    public List<String> getSupermarketsNames() {
        return supermarketsNames;
    }

    public List<String> getSupermarketsAddresses() {
        return supermarketsAddresses;
    }

    public List<Float> getSupermarketsDistances() {
        return supermarketsDistances;
    }
}
