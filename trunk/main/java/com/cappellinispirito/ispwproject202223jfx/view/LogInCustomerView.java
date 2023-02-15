package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.LogInController;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.LogInBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.LogInBeanClass;


public class LogInCustomerView {
    private String username;

    private boolean isLoginDone = false;
    private boolean isPremium;

    public void attemptLogin(String username, String password){
        //Suppose Username and Password field have been updated from user input
        try{
            LogInBean bean = new LogInBeanClass();
            bean.setUsername(username);
            bean.setPassword(password);
            LogInController logInController = LogInController.getInstance();
            logInController.checkCredentials(bean);
            boolean isCredentialsCorrect = bean.getIsCredentialsCorrect();
            if(isCredentialsCorrect){
                //logic for login ok
                isLoginDone = true;
                setUsername(bean.getUsername());
                isPremium=bean.getIsPremium();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean getIsLoginDone(){
        return isLoginDone;
    }

    public String getUsername() {
        return username;
    }
    private void setUsername(String user){
        this.username = user;
    }
    public boolean getIsPremium(){
        return this.isPremium;
    }
}
