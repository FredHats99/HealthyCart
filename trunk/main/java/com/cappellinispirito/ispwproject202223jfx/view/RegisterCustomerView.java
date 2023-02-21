package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.RegistrationController;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.RegistrationBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.RegistrationBeanClass;

public class RegisterCustomerView {


    public void attemptRegister(String registrationUsername, String registrationPassword) throws Exception {
        RegistrationBean bean = new RegistrationBeanClass();
        bean.setUsername(registrationUsername);
        bean.setPassword(registrationPassword);
        RegistrationController registrationController = new RegistrationController();
        registrationController.register(bean);
    }
}
