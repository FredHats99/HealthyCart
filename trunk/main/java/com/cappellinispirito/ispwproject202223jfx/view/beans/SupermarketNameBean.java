package com.cappellinispirito.ispwproject202223jfx.view.beans;

public class SupermarketNameBean {
    private static SupermarketNameBean instance;

    private SupermarketNameBean(){}

    public static SupermarketNameBean getInstance(){
        if(instance == null){
            instance = new SupermarketNameBean();
        }
        return instance;
    }

    private String supermarketName;

    public String getSupermarketName() {
        return supermarketName;
    }
    public void setSupermarketName(String supermarketName){
        this.supermarketName = supermarketName;
    }
}
