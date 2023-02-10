package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Supermarket;

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
