package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.shopBean;

public class shopBeanClass implements shopBean {
    private String supermarketName;
    private String supermarketAddress;
    private String itemToAdd;
    private String itemToRemove;
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
    public String getItemToAdd() {
        return itemToAdd;
    }

    @Override
    public void setItemToAdd(String item) {
        itemToAdd = item;
    }

    @Override
    public String getItemToRemove() {
        return itemToRemove;
    }

    @Override
    public void setItemToRemove(String item) {
        itemToRemove = item;
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
