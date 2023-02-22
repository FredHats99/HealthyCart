package com.cappellinispirito.ispwproject202223jfx.view.graphics;

import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NewShopBean;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenuControllerG implements Initializable {

    @FXML
    public StackPane root;
    @FXML
    private Label usernameLabel;
    private boolean isPremium;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String username = NamePremiumBean.getInstance().getName(); // getName() returns null
        isPremium = NamePremiumBean.getInstance().getPremium();
        if(username != null) {
            usernameLabel.setText(username);
        }
    }

    public void onCartHistoryClick() throws IOException{
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/history.fxml")));
        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);


    }

    public void onLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/login.fxml")));
        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    public void onFindItemClick() throws IOException{
        //initialize new scene

        if(!isPremium){
            Label errorLabel = new Label("Error: Not Premium");
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
        else {

            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/search.fxml")));
            Parent rootNode = loader.load();
            Scene myScene = new Scene(rootNode);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(myScene);
        }

    }

    public void onFindStoreClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/nearestSupermarket.fxml")));
        NewShopBean.getInstance().setNewShop(false);

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    public void onNewCartClick() throws  IOException{
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/nearestSupermarket.fxml")));
        NewShopBean.getInstance().setNewShop(true);

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }
}