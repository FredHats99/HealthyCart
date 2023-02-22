package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.RegistrationController;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.RegistrationBean;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedRegistrationException;
import com.cappellinispirito.ispwproject202223jfx.view.beans.RegistrationBeanClass;


public class RegisterCustomerView {


    public void attemptRegister(String registrationUsername, String registrationPassword) throws FailedRegistrationException {
        try{
            RegistrationBean bean = new RegistrationBeanClass();
            bean.setUsername(registrationUsername);
            bean.setPassword(registrationPassword);
            RegistrationController registrationController = new RegistrationController();
            registrationController.register(bean);
        } catch (Exception e){
            throw new FailedRegistrationException("Registration Failed!");
        }
    }
}
