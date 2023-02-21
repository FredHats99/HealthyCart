package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.RegistrationBean;

public class RegistrationBeanClass implements RegistrationBean {
    private String username;
    private String password;
    private Boolean isRegistrationDone = false;

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
