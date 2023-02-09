package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Supermarket;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.supermarketsToProductsBean;

import java.util.List;

public class supermarketsToProductsBeanClass implements supermarketsToProductsBean {
    private Supermarket supermarket;
    private List<Item> soldProducts;


    @Override
    public Supermarket getSupermarket() {
        return supermarket;
    }

    @Override
    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    @Override
    public List<Item> getSoldProducts() {
        return soldProducts;
    }

    @Override
    public void setSoldProducts(List<Item> list) {
        soldProducts = list;
    }
}
