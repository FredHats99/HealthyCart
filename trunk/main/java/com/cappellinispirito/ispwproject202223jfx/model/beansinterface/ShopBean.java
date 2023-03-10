package com.cappellinispirito.ispwproject202223jfx.model.beansinterface;



import java.util.List;

public interface ShopBean {
    //output data
    List<String> getSellableProductName();
    void setSellableProductName(List<String> sellableProductName);

    List<String> getSellableProductImage();
    void setSellableProductImage(List<String> sellableProductImage);

    //Query data
    String getItemToAdd();
    void setItemToAdd(String name);

    int getItemToRemove();
    void setItemToRemove(int indexOfName);

    int getCartHealthScore();
    void setCartHealthScore(int score);
}
