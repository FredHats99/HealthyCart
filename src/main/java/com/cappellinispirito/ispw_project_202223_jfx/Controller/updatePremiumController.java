package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.UserAccount;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.updatePremiumBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.UserAccountDAO;

import java.sql.SQLException;

public class updatePremiumController {

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
