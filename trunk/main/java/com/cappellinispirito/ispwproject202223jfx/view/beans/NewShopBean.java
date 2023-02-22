package com.cappellinispirito.ispwproject202223jfx.view.beans;

public class NewShopBean {
    private static NewShopBean instance;
    private Boolean newShop;

    private NewShopBean(){}

    public boolean getNewShop(){
        return newShop;
    }
    public void setNewShop(boolean t){
        this.newShop = t;
    }

    public static NewShopBean getInstance(){
        if(instance == null){
            instance = new NewShopBean();
        }
        return instance;
    }

}
