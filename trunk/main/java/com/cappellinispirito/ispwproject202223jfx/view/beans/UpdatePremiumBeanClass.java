package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.updatePremiumBean;

public class UpdatePremiumBeanClass implements updatePremiumBean {
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
