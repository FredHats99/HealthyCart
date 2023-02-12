package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

public class ProductNameBean {
    public static ProductNameBean instance;
    private String name;
    private boolean premium;

    private ProductNameBean(){}

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public boolean getPremium(){
        return premium;
    }
    public void setPremium(boolean t){
        this.premium = t;
    }

    public static ProductNameBean getInstance(){
        if(instance == null){
            instance = new ProductNameBean();
        }
        return instance;
    }

}
