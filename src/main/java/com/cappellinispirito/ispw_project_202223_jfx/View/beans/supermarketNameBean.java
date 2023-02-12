package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

public class supermarketNameBean {
    public static supermarketNameBean instance;

    private supermarketNameBean(){}

    public static supermarketNameBean getInstance(){
        if(instance == null){
            instance = new supermarketNameBean();
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
