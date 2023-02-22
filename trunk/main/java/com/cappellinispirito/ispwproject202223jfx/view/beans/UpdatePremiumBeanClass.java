package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.UpdatePremiumBean;

public class UpdatePremiumBeanClass implements UpdatePremiumBean {
    private boolean procedureOutcome;

    @Override
    public boolean getUpdatePremiumProcedureOutcome() {
        return procedureOutcome;
    }

    @Override
    public void setUpdatePremiumProcedureOutcome(boolean bool) {
        procedureOutcome = bool;
    }
}
