package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;

import java.util.List;

public interface shopBean {
    //outoput data
    List<String> getSellableProductName();
    void setSellableProductName(List<String> sellableProductName);

    List<String> getSellableProductImage();
    void setSellableProductImage(List<String> sellableProductImage);

    //Query data
    String getItemToAdd();
    void setItemToAdd(String name);

    String getItemToRemove();
    void setItemToRemove(String name);

    int getCartHealthScore();
    void setCartHealthScore(int score);
}
