package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

public class newShopBean {
    public static newShopBean instance;
    private Boolean newShop;

    private newShopBean(){}

    public boolean getNewShop(){
        return newShop;
    }
    public void setNewShop(boolean t){
        this.newShop = t;
    }

    public static newShopBean getInstance(){
        if(instance == null){
            instance = new newShopBean();
        }
        return instance;
    }

}
