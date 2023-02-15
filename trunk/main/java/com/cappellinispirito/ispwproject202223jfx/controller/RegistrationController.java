package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.RegistrationBean;
import com.cappellinispirito.ispwproject202223jfx.model.dao.UserAccountDAO;

import java.sql.SQLException;

public class RegistrationController {

    public void register(RegistrationBean bean) throws SQLException {
        String registerUsername = bean.getUsername();
        String registerPassword = bean.getPassword();
        UserAccountDAO accountDAO = new UserAccountDAO();
        if(!accountDAO.checkIfUserExists(registerUsername)){
            accountDAO.createAccount(registerUsername, registerPassword);
        }
        bean.setIsRegistrationDone(true);
    }
}
