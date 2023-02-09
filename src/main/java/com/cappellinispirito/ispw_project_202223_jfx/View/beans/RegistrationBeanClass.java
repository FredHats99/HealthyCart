package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.RegistrationBean;

public class RegistrationBeanClass implements RegistrationBean {
    private String username;
    private String password;
    private Boolean isRegistrationDone;

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
    public void setIsRegistrationDone(boolean bool) {
        isRegistrationDone = bool;
    }

    @Override
    public boolean getIsRegistrationDone() {
        return isRegistrationDone;
    }


}
