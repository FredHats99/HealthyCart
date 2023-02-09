package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.LogInBean;

public class LogInBeanClass implements LogInBean {
    private String username;
    private String password;
    private Boolean isCredentialsCorrect;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String user) {
        username = user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String pwd) {
        password = pwd;
    }

    @Override
    public Boolean getIsCredentialsCorrect() {
        return isCredentialsCorrect;
    }

    @Override
    public void setIsCredentialsCorrect(Boolean bool) {
        isCredentialsCorrect = bool;
    }
}
