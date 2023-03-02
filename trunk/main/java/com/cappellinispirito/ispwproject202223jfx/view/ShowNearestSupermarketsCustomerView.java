package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.ShowNearestSupermarketsController;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.NearestSupermarketBeanInterface;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NearestSupermarketBeanClass;

import java.io.IOException;
import java.util.List;

public class ShowNearestSupermarketsCustomerView {
    private List<String> supermarketsNames;
    private List<String> supermarketAddresses;
    private List<Float> supermarketsDistances;

    public void showNearestFrom(String address) throws IOException {
        NearestSupermarketBeanInterface bean = new NearestSupermarketBeanClass();
        bean.setSearch(address);
        ShowNearestSupermarketsController showNearestSupermarketsController = ShowNearestSupermarketsController.getInstance();
        showNearestSupermarketsController.getNearestSupermarkets(bean);
        supermarketsNames = bean.getSupermarketsNamesList();
        supermarketAddresses = bean.getSupermarketsAddressesList();
        supermarketsDistances = bean.getSupermarketsNamesDistances();
        //now display them pls!
    }

    public List<String> getSupermarketsNames() {
        return supermarketsNames;
    }

    public List<Float> getSupermarketsDistances() {
        return supermarketsDistances;
    }

    public List<String> getSupermarketAddresses() {
        return supermarketAddresses;
    }
}
