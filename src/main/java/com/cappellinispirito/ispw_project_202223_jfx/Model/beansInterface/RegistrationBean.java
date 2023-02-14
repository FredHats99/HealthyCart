package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

public interface RegistrationBean {
    String getUsername();
    void setUsername(String user);

    String getPassword();
    void setPassword(String pwd);

    void setIsRegistrationDone(boolean bool);
    boolean getIsRegistrationDone();
}
