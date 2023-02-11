package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.View.LogInCustomerView;
import com.cappellinispirito.ispw_project_202223_jfx.View.RegisterCustomerView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.security.auth.login.FailedLoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LogInControllerG {

    public StackPane root;
    public TextField usernametext;
    public TextField passwordtext;
    public Label loginbutton;
    public Label registerbutton;

    LogInCustomerView view;

    public LogInControllerG(){

    }

    public void onLoginClick() throws FailedLoginException, SQLException, IOException {
        String username = usernametext.getText();
        String password = passwordtext.getText();
        if(username != null || password != null){
            view = new LogInCustomerView();
            view.attemptLogin(usernametext.getText(), passwordtext.getText());
            if(view.getIsLoginDone()){
                onBackPressed();
            }
        }

    }

    public void onBackPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/main_menu2.fxml")));
        MainMenuControllerG controller = loader.getController();
        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
        if(view.getIsLoginDone()){
            controller.setUsername(view.getUsername());
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
