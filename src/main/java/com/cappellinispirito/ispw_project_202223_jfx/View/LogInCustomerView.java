package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.LogInController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.LogInBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.LogInBeanClass;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LogInCustomerView {
    private String username;
    private String password;
    private boolean isPremium = false;

    private boolean isCredentialsCorrect;
    private boolean isLoginDone = false;

    public void attemptLogin(String username, String password) throws SQLException, FailedLoginException {
        //Suppose Username and Password field have been updated from user input
        try{
            LogInBean bean = new LogInBeanClass();
            bean.setUsername(username);
            bean.setPassword(password);
            LogInController logInController = LogInController.getInstance();
            logInController.checkCredentials(bean);
            isCredentialsCorrect = bean.getIsCredentialsCorrect();
            if(isCredentialsCorrect){
                //logic for login ok
                System.out.println("Credentials are ok!");
                isLoginDone = true;
                isPremium = bean.getIsPremium();
                username = bean.getUsername();
                System.out.format("User is %s", username);
                password = bean.getPassword();
            } else {
                //display error message
            }
        } catch (NullPointerException e){

        }
    }
    public boolean getIsLoginDone(){
        return isLoginDone;
    }

    public boolean getIsPremium(){
        return isPremium;
    }

    public String getUsername() {
        return username;
    }
}
