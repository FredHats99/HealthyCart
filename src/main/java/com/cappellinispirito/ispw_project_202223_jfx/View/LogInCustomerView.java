package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.LogInController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.LogInBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.LogInBeanClass;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LogInCustomerView {
    private String username;

    private boolean isLoginDone = false;
    private boolean isPremium;

    public LogInCustomerView() {
    }

    public void attemptLogin(String username, String password) throws SQLException, FailedLoginException {
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
                System.out.println("Credentials are ok!");
                isLoginDone = true;
                setUsername(bean.getUsername());
                System.out.format("User is %s", username);
                isPremium=bean.getIsPremium();
            }
        } catch (Exception e){
            //e.printStackTrace();
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
