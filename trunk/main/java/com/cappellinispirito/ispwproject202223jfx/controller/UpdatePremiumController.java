package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.UserAccount;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.updatePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.model.dao.UserAccountDAO;

import java.sql.SQLException;

public class UpdatePremiumController {

    private final UserAccount account = LogInController.getInstance().getUserAccountInstance();

    public void updateCurrentUserToPremium(updatePremiumBean bean) throws SQLException {
        UserAccountDAO accountDao = new UserAccountDAO();
        boolean outcome;
        String username = getUsernameFromModel();
        if(!accountDao.getPremium(username)){
            //USER IS NOT PREMIUM. BUT IT WILL...
            accountDao.updateToPremium(username, !account.getIsPremium());
            updateModelUser();
            outcome = true;
        } else {
            //USER IS ALREADY PREMIUM!
            outcome = false;
        }
        bean.setUpdatePremiumProcedureOutcome(outcome);
    }

    private String getUsernameFromModel() {
        return account.getUsername();
    }

    private void updateModelUser(){
        //assert: the check has already been made
        account.switchPremium();
    }
}
