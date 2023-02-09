package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

public interface shopBean {
    String getSupermarketName();
    void setSupermarketName(String supermarket);

    String getSupermarketAddress();
    void setSupermarketAddress(String address);
    //Not quite sure if String or something else
    String getItemToAdd();
    void setItemToAdd(String item);

    String getItemToRemove();
    void setItemToRemove(String item);

    int getCartHealthScore();
    void setCartHealthScore(int score);
}
