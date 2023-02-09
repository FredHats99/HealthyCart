package com.cappellinispirito.ispw_project_202223_jfx.Model;

public class UserAccount {
    private String username;
    private String password;
    private Boolean isPremium;

    public UserAccount(String username, String password, boolean isPremium) {
        this.username = username;
        this.password = password;
        this.isPremium = isPremium;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public Boolean getIsPremium(){
        return this.isPremium;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public void switchPremium(){
        this.isPremium = true;
    }
}
