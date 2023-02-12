package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

public class NameBean {
    public static NameBean instance;
    private String name = "Not Logged";

    private NameBean(){}

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public static NameBean getInstance(){
        if(instance == null){
            instance = new NameBean();
        }
        return instance;
    }

}
