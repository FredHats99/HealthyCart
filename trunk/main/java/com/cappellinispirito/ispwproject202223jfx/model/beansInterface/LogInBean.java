package com.cappellinispirito.ispwproject202223jfx.model.beansInterface;

public interface LogInBean {
    String getUsername();
    void setUsername(String user);

    String getPassword();
    void setPassword(String pwd);

    //output data
    Boolean getIsCredentialsCorrect();
    void setIsCredentialsCorrect(Boolean bool);

    boolean getIsPremium();
    void setIsPremium(boolean isPremium);
}
