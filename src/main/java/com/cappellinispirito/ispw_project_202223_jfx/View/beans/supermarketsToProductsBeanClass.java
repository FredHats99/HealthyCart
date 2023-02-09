package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.supermarketsToProductsBean;

public class supermarketsToProductsBeanClass implements supermarketsToProductsBean {
    private String supermarket;

    @Override
    public String getSupermarket() {
        return supermarket;
    }

    @Override
    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }
}
