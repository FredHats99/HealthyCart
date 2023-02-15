package com.cappellinispirito.ispwproject202223jfx.model.beansInterface;

public interface RegistrationBean {
    String getUsername();
    void setUsername(String user);

    String getPassword();
    void setPassword(String pwd);

    void setIsRegistrationDone(boolean bool);
    boolean getIsRegistrationDone();
}
