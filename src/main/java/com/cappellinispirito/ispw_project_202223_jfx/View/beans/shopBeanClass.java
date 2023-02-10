package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.shopBean;

import java.util.List;

public class shopBeanClass implements shopBean {
    private List<String> sellableProductsNames;
    private List<String> sellableProductsImages;
    private String itemNameToAdd;
    private String itemNameToRemove;
    private int cartHealthScore;


    @Override
    public List<String> getSellableProductName() {
        return sellableProductsNames;
    }

    @Override
    public void setSellableProductName(List<String> sellableProductName) {
        this.sellableProductsNames = sellableProductName;
    }

    @Override
    public List<String> getSellableProductImage() {
        return sellableProductsImages;
    }

    @Override
    public void setSellableProductImage(List<String> sellableProductImage) {
        this.sellableProductsImages = sellableProductImage;
    }

    @Override
    public String getItemToAdd() {
        return itemNameToAdd;
    }

    @Override
    public void setItemToAdd(String name) {
        itemNameToAdd = name;
    }

    @Override
    public String getItemToRemove() {
        return itemNameToRemove;
    }

    @Override
    public void setItemToRemove(String name) {
        itemNameToRemove = name;
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
