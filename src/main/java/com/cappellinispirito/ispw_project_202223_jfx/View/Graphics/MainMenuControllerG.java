package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.DeniedPermissionsException;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.View.LogInCustomerView;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Objects;

public class MainMenuControllerG {

    private Stage applicationStage;
    @FXML
    public StackPane root;
    public TextField search_text;
    public RadioButton newcart_button;
    public ImageView Label_View;
    public RadioButton findstore_button;
    public Label userName_label;
    public ImageView userlogo;
    SearchProductControllerG controllerG;
    LogInCustomerView logInCustomerView;
    public String username;


    public MainMenuControllerG() throws FailedQueryToOpenFoodFacts {
        logInCustomerView = new LogInCustomerView();
        username = NameBean.getInstance().getName();
        System.out.format("User is %s", username);
    }

    public void onCartHistoryClicked() throws IOException, DeniedPermissionsException {
        if(username == null){
            //Some label will tell the user that he has to log in...
            throw new DeniedPermissionsException("You have to log in to use this functionality");
        }
        //initialize new scene

        //Bean.value = textfield;
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/history.fxml")));
        Parent rootNode = loader.load();
        CartHistoryControllerG controller = loader.getController();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);


    }

    public void onLogin() throws IOException {
        if(username != null){
            userName_label.setText(username);
        } else {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/login.fxml")));
            LogInControllerG controller = loader.getController();
            Parent rootNode = loader.load();
            Scene myScene = new Scene(rootNode);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(myScene);
        }
    }

    public void onSearchProductButtonClicked() throws DeniedPermissionsException, FailedQueryToOpenFoodFacts, IOException, ParseException {
        String searchText = search_text.getText();
        /*if(!logInCustomerView.getIsPremium()){
            //TODO: some label will tell the user he has to be premium...
            throw new DeniedPermissionsException("You have to be a premium user to use this functionality!");
        }*/
        if(searchText == null){
            throw new DeniedPermissionsException("Input non valid!");
        }
        //initialize new scene
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/search.fxml")));
        SearchProductControllerG controller = loader.getController();
        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
        try{
            controller.searchProduct(searchText);
        } catch (NullPointerException e){

        }
    }
    public void onShowNearestSupermarketClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/nearestSupermarket.fxml")));
        ShowNearestSupermarketControllerG controller = loader.getController();
        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    public void setUsername(String username) {
        userName_label.setText(username);
    }

    public void setLabel(String username) {
        userName_label.setText(username);
    }
}