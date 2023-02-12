package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.View.LogInCustomerView;
import com.cappellinispirito.ispw_project_202223_jfx.View.RegisterCustomerView;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameBean;
import javafx.application.Platform;
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
import java.util.Timer;
import java.util.TimerTask;

public class LogInControllerG {

    public StackPane root;
    public TextField usernametext;
    public TextField passwordtext;
    public Label loginbutton;
    public Label registerbutton;


    String username;

    LogInCustomerView view = new LogInCustomerView();

    public LogInControllerG(){

    }

    public void onLoginClick() throws FailedLoginException, SQLException, IOException {
        username = usernametext.getText();
        String password = passwordtext.getText();
        if(username != null || password != null){
            view.attemptLogin(usernametext.getText(), passwordtext.getText());
            if(view.getIsLoginDone()){
                onBackButton();
            }
            else{
                Label errorLabel = new Label("Error: Wrong Credentials");
                errorLabel.setStyle("-fx-text-fill: red;");
                errorLabel.setVisible(true);
                errorLabel.setLayoutX(100);
                errorLabel.setLayoutY(100);
                errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 50;");
                // Add the error label to the scene or the parent container
                root.getChildren().add(errorLabel);
                // Set a timeout to hide the label after 4 seconds
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> errorLabel.setVisible(false));
                    }
                }, 4000);
            }
        }

    }

    public void onBackButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/main_menu2.fxml")));

        if(view.getIsLoginDone()){
            NameBean bean = NameBean.getInstance();
            bean.setName(username);
            System.out.format("Sending user %s", username);
        }

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
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
