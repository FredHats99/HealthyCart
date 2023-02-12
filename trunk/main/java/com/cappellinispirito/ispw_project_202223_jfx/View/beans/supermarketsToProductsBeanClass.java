package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Supermarket;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.supermarketsToProductsBean;

import java.util.List;

public class supermarketsToProductsBeanClass implements supermarketsToProductsBean {
    private Supermarket supermarket;
    private List<String> sellableProductsNames;
    private List<String> sellableProductsImages;
    private List<String> sellableProductsBarcodes;

    @Override
    public Supermarket getSupermarket() {
        return supermarket;
    }

    @Override
    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    @Override
    public List<String> getSellableProductsName() {
        return sellableProductsNames;
    }

    @Override
    public void setSellableProductsNames(List<String> sellableProductsName) {
        this.sellableProductsNames = sellableProductsName;
    }

    @Override
    public List<String> getSellableProductsImage() {
        return sellableProductsImages;
    }

    @Override
    public void setSellableProductsImage(List<String> sellableProductsImage) {
        this.sellableProductsImages = sellableProductsImage;
    }

    @Override
    public List<String> getSellableProductsBarcode() {
        return sellableProductsBarcodes;
    }

    @Override
    public void setSellableProductsBarcode(List<String> sellableProductsBarcodes) {
        this.sellableProductsBarcodes = sellableProductsBarcodes;
    }


}
