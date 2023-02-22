package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.UserAccount;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.LogInBean;
import com.cappellinispirito.ispwproject202223jfx.model.dao.UserAccountDAO;


import java.sql.SQLException;

public class LogInController {
    private static LogInController instance;
    private UserAccount logInUser;

    private LogInController(){
        createUserAccount("",  false);
    }

    public static LogInController getInstance() {
        if(instance == null){
            instance = new LogInController();
        }
        return instance;
    }

    public void checkCredentials(LogInBean bean) throws SQLException, com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedLoginException {
        String username = bean.getUsername();
        String password = bean.getPassword();
        UserAccountDAO accountDAO = new UserAccountDAO();
        if(accountDAO.checkCredentials(username, password)){
            boolean isPremium = accountDAO.getPremium(username);
            createUserAccount(username, isPremium);
            bean.setIsCredentialsCorrect(true);
            bean.setIsPremium(isPremium);
        } else {
            bean.setIsCredentialsCorrect(false);
        }
    }

    private void createUserAccount(String username, boolean isPremium) {
        logInUser = new UserAccount(username, isPremium);
    }

    public UserAccount getUserAccountInstance(){
        return logInUser;
    }
}

