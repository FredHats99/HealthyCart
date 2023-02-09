package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Supermarket;

import java.util.List;

public interface supermarketsToProductsBean {
    Supermarket getSupermarket();
    void setSupermarket(Supermarket supermarket);

    List<Item> getSoldProducts();
    void setSoldProducts(List<Item> list);
}
