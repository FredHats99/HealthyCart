package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.View.LogInCustomerView;
import com.cappellinispirito.ispw_project_202223_jfx.View.RegisterCustomerView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LogInControllerG {

    public TextField usernametext;
    public TextField passwordtext;
    public Label loginbutton;
    public Label registerbutton;

    public LogInControllerG(){

    }

    public void onLoginClick() throws FailedLoginException, SQLException {
        String username = usernametext.getText();
        String password = passwordtext.getText();
        if(username != null || password != null){
            LogInCustomerView view = new LogInCustomerView();
            view.attemptLogin(usernametext.getText(), passwordtext.getText());
        }

    }

    public void onRegisterClick() throws Exception {
        String username = usernametext.getText();
        String password = passwordtext.getText();
        if(username != null || password != null){
            RegisterCustomerView view = new RegisterCustomerView();
            view.attemptRegister(username, password);
        }
    }
}
