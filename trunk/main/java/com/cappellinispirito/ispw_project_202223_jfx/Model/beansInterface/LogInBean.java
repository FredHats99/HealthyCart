package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

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
