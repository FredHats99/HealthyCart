package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.UpdatePremiumController;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.UpdatePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.UpdatePremiumBeanClass;

import java.sql.SQLException;

public class UpdatePremiumCustomerView {

    public void updateUserToPremium() throws SQLException {
        UpdatePremiumBean bean = new UpdatePremiumBeanClass();
        UpdatePremiumController controller = new UpdatePremiumController();
        controller.updateCurrentUserToPremium(bean);
    }
}
