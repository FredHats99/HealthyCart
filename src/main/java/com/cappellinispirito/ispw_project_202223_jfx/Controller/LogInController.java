package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.UserAccount;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.LogInBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.UserAccountDAO;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LogInController {
    public static LogInController instance;
    private UserAccount logInUser;

    private LogInController(){
        createUserAccount("", "", false);
    }

    public static LogInController getInstance() {
        if(instance == null){
            instance = new LogInController();
        }
        return instance;
    }

    public void checkCredentials(LogInBean bean) throws FailedLoginException, SQLException {
        String username = bean.getUsername();
        String password = bean.getPassword();
        UserAccountDAO accountDAO = new UserAccountDAO();
        if(accountDAO.checkCredentials(username, password)){
            boolean isPremium = accountDAO.getPremium(username);
            createUserAccount(username, password, isPremium);
            bean.setIsCredentialsCorrect(true);
            bean.setIsPremium(isPremium);

            System.out.println("Correctly logged in as..");
            System.out.format("Username: '%s', Password: '%s', isPremium: '%s'", username, password, isPremium);
        } else {
            bean.setIsCredentialsCorrect(false);
        }
    }

    private void createUserAccount(String username, String password, boolean isPremium) {
        logInUser = new UserAccount(username, password, isPremium);
    }

    public UserAccount getUserAccountInstance(){
        return logInUser;
    }
}

