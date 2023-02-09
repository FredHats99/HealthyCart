package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.RegistrationController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.RegistrationBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.RegistrationBeanClass;

public class RegisterCustomerView {
    private String registrationUsername;
    private String registrationPassword;

    public void attemptRegister() throws Exception {
        RegistrationBean bean = new RegistrationBeanClass();
        bean.setUsername(registrationUsername);
        bean.setPassword(registrationPassword);
        RegistrationController registrationController = new RegistrationController();
        registrationController.register(bean);
        if(bean.getIsRegistrationDone()){
            //Yay! Registration ended up well... Now back to the login screen...
        } else {
            //Oops! Something went wrong. Display error message!
        }
    }
}
