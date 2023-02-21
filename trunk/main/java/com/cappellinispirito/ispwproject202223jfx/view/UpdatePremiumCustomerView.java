package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.UpdatePremiumController;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.updatePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.UpdatePremiumBeanClass;

import java.sql.SQLException;

public class UpdatePremiumCustomerView {

    public void updateUserToPremium() throws SQLException {
        updatePremiumBean bean = new UpdatePremiumBeanClass();
        UpdatePremiumController controller = new UpdatePremiumController();
        controller.updateCurrentUserToPremium(bean);
    }
}
