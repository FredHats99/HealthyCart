package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.updatePremiumController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.updatePremiumBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.updatePremiumBeanClass;

import java.sql.SQLException;

public class UpdatePremiumCustomerView {

    public void updateUserToPremium() throws SQLException {
        updatePremiumBean bean = new updatePremiumBeanClass();
        updatePremiumController controller = new updatePremiumController();
        controller.updateCurrentUserToPremium(bean);
        if(bean.getUpdatePremiumProcedureOutcome()){
            //User has been updated to Premium. Hooray!!
        } else {
            //Nooo. Something went wrong, maybe user is already premium...
        }
    }
}
