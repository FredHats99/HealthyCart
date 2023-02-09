package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.shopBean;

public class shopBeanClass implements shopBean {
    private String supermarketName;
    private String supermarketAddress;
    private int indexOfItemToAdd;
    private int indexOfItemToRemove;
    private int cartHealthScore;

    @Override
    public String getSupermarketName() {
        return supermarketName;
    }

    @Override
    public void setSupermarketName(String supermarket) {
        supermarketName = supermarket;
    }

    @Override
    public String getSupermarketAddress() {
        return supermarketAddress;
    }

    @Override
    public void setSupermarketAddress(String address) {
        supermarketAddress = address;
    }

    @Override
    public int getItemToAdd() {
        return indexOfItemToAdd;
    }

    @Override
    public void setItemToAdd(int item) {
        indexOfItemToAdd = item;
    }

    @Override
    public int getItemToRemove() {
        return indexOfItemToRemove;
    }

    @Override
    public void setItemToRemove(int item) {
        indexOfItemToRemove = item;
    }

    @Override
    public int getCartHealthScore() {
        return cartHealthScore;
    }

    @Override
    public void setCartHealthScore(int score) {
        cartHealthScore = score;
    }
}
