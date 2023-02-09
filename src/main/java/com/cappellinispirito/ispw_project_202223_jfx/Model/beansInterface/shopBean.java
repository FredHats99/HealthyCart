package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;

public interface shopBean {
    String getSupermarketName();
    void setSupermarketName(String supermarket);

    String getSupermarketAddress();
    void setSupermarketAddress(String address);
    //Not quite sure if String or something else
    int getItemToAdd();
    void setItemToAdd(int item);

    int getItemToRemove();
    void setItemToRemove(int item);

    int getCartHealthScore();
    void setCartHealthScore(int score);
}
