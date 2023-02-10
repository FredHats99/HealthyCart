package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.LogInController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.LogInBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.LogInBeanClass;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LogInCustomerView {
    private String username = "";
    private String password = "";

    private boolean isCredentialsCorrect;

    public void attemptLogin() throws SQLException, FailedLoginException {
        //Suppose Username and Password field have been updated from user input
        LogInBean bean = new LogInBeanClass();
        bean.setUsername(username);
        bean.setPassword(password);
        LogInController logInController = LogInController.getInstance();
        logInController.checkCredentials(bean);
        isCredentialsCorrect = bean.getIsCredentialsCorrect();
        if(isCredentialsCorrect){
            //logic for login ok
        } else {
            //display error message
        }
    }
}