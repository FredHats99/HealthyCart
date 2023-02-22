package com.cappellinispirito.ispwproject202223jfx.view.graphics;

import com.cappellinispirito.ispwproject202223jfx.model.dao.UserAccountDAO;
import com.cappellinispirito.ispwproject202223jfx.view.LogInCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.RegisterCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class LogInControllerG implements Initializable {
    @FXML
    private StackPane root;
    @FXML
    private TextField usernametext;
    @FXML
    private TextField passwordtext;
    @FXML
    private Label loginbutton;
    @FXML
    private Label registerbutton;
    @FXML
    private Label toggle;
    @FXML
    private StackPane vis;
    @FXML
    private Label un;


    String username;

    LogInCustomerView view = new LogInCustomerView();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        if (NamePremiumBean.getInstance().getPremium()){
            toggle.setStyle("-fx-background-color: green");
        }
        else if (!NamePremiumBean.getInstance().getPremium()){
            toggle.setStyle("-fx-background-color: red");
        }

        vis.setVisible(NamePremiumBean.getInstance().getName() != null);
        if (NamePremiumBean.getInstance().getName()!=null){un.setText(NamePremiumBean.getInstance().getName());}
    }

    public void onLoginClick() throws IOException{
        username = usernametext.getText();
        String password = passwordtext.getText();
        if(username != null || password != null){
            view.attemptLogin(usernametext.getText(), passwordtext.getText());
            if(view.getIsLoginDone()){
                NamePremiumBean.getInstance().setPremium(view.getIsPremium());
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
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/main_menu2.fxml")));

        if(view.getIsLoginDone()){
            NamePremiumBean bean = NamePremiumBean.getInstance();
            bean.setName(username);
        }

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    public void onToggle() throws SQLException {
        if(NamePremiumBean.getInstance().getPremium()){
            NamePremiumBean.getInstance().setPremium(false);
            toggle.setStyle("-fx-background-color: red");
            UserAccountDAO t = new UserAccountDAO();
            t.updateToPremium(NamePremiumBean.getInstance().getName(), NamePremiumBean.getInstance().getPremium());

        }
        else {
            NamePremiumBean.getInstance().setPremium(true);
            toggle.setStyle("-fx-background-color: green");
            UserAccountDAO t = new UserAccountDAO();
            t.updateToPremium(NamePremiumBean.getInstance().getName(), NamePremiumBean.getInstance().getPremium());
        }
    }

    public void onRegisterClick() throws Exception {
        String usernamee = usernametext.getText();
        String password = passwordtext.getText();
        if(usernamee != null || password != null){
            RegisterCustomerView vieww = new RegisterCustomerView();
            vieww.attemptRegister(usernamee, password);
        }
    }
}
