package com.cappellinispirito.ispw_project_202223_jfx.Model;

public class Supermarket {
    //variables
    private String supermarketName;
    private String address;

    //constructor
    public Supermarket(String name, String address){
        this.supermarketName = name;
        this.address = address;
    }

    public String getSupermarketName() {
        return this.supermarketName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setSupermarketName(String name){
        this.supermarketName = name;
    }

    public void setAddress(String address){
        this.address = address;
    }
}
