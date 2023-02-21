package com.cappellinispirito.ispwproject202223jfx.model.beansinterface;

import com.cappellinispirito.ispwproject202223jfx.model.Supermarket;

import java.util.List;

public interface supermarketsToProductsBean {
    Supermarket getSupermarket();
    void setSupermarket(Supermarket supermarket);

    List<String> getSellableProductsName();
    void setSellableProductsNames(List<String> sellableProductsName);

    List<String> getSellableProductsImage();
    void setSellableProductsImage(List<String> sellableProductsImage);

    List<String> getSellableProductsBarcode();
    void setSellableProductsBarcode(List<String> sellableProductsBarcode);
}
