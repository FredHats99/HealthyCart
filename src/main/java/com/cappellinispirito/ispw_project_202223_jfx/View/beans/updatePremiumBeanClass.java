package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.updatePremiumBean;

public class updatePremiumBeanClass implements updatePremiumBean {
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
