package com.cappellinispirito.ispwproject202223jfx.view.beans;

public class ProductNameBean {
    public static ProductNameBean instance;
    private String name;

    private ProductNameBean(){}

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public static ProductNameBean getInstance(){
        if(instance == null){
            instance = new ProductNameBean();
        }
        return instance;
    }

}
