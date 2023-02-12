package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.RegistrationController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.RegistrationBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.RegistrationBeanClass;

public class RegisterCustomerView {


    public void attemptRegister(String registrationUsername, String registrationPassword) throws Exception {
        RegistrationBean bean = new RegistrationBeanClass();
        bean.setUsername(registrationUsername);
        bean.setPassword(registrationPassword);
        RegistrationController registrationController = new RegistrationController();
        registrationController.register(bean);
    }
}
