package com.cappellinispirito.ispwproject202223jfx.model;

public class UserAccount {
    private String username;
    private Boolean isPremium;

    public UserAccount(String username, boolean isPremium) {
        this.username = username;
        this.isPremium = isPremium;
    }

    public String getUsername(){
        return this.username;
    }

    public Boolean getIsPremium(){
        return this.isPremium;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void switchPremium(){
        this.isPremium = true;
    }
}
