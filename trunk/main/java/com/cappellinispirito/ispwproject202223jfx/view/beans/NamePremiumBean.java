package com.cappellinispirito.ispwproject202223jfx.view.beans;

public class NamePremiumBean {
    private static NamePremiumBean instance;
    private String name;
    private boolean premium;

    private NamePremiumBean(){}

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

    public static NamePremiumBean getInstance(){
        if(instance == null){
            instance = new NamePremiumBean();
        }
        return instance;
    }

}
