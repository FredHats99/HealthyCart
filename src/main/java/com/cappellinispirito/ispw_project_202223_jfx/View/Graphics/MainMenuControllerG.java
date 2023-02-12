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
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Objects;

public class MainMenuControllerG {

    @FXML
    public StackPane root;
    public Label usernameLabel;

    LogInCustomerView logInCustomerView;
    public String username;


    public MainMenuControllerG(){
        logInCustomerView = new LogInCustomerView(); // used for?
        username = NameBean.getInstance().getName(); // getName() returns null
        if(username != null) {
            usernameLabel.setText(username);
        }
        System.out.format("User is %s", username);
    }

    public void onCartHistoryClick() throws IOException, DeniedPermissionsException {

        /*try{
            if(username == null){
                //Some label will tell the user that he has to log in...
                throw new DeniedPermissionsException("You have to log in to use this functionality");
            }
        } catch (DeniedPermissionsException e){
            throw new DeniedPermissionsException("You have to log in to use this functionality");
        }*/

        //initialize new scene

        //Bean.value = textfield;

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/history.fxml")));

        //already specified by fxml
        //CartHistoryControllerG controllerG = new CartHistoryControllerG();
        //loader.setController(controllerG);

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);


    }

    public void onLogin() throws IOException {

        /*if(username != null){
            usernameLabel.setText(username);
        } else {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/login.fxml")));

            LogInControllerG controllerG = new LogInControllerG();
            loader.setController(controllerG);

            Parent rootNode = loader.load();
            Scene myScene = new Scene(rootNode);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(myScene);
        }*/

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/login.fxml")));

        //Already specified inside the fxml
        //LogInControllerG controllerG = new LogInControllerG();
        //loader.setController(controllerG);

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    public void onFindItemClick() throws DeniedPermissionsException, FailedQueryToOpenFoodFacts, IOException, ParseException {

        /*if(!logInCustomerView.getIsPremium()){
            //TODO: some label will tell the user he has to be premium...
            throw new DeniedPermissionsException("You have to be a premium user to use this functionality!");
        }*/

        //initialize new scene
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/search.fxml")));

        //already specified inside fxml
        //SearchProductControllerG controller = loader.getController(); // ???
        //SearchProductControllerG controllerG = new SearchProductControllerG();
        //loader.setController(controllerG);

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);

    }

    public void onFindStoreClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/nearestSupermarket.fxml")));

        //already specified inside fxml
        //ShowNearestSupermarketControllerG controllerG = new ShowNearestSupermarketControllerG();
        //loader.setController(controllerG);

        //controllerG.setIsStartingAShop(false); // why not?

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    public void onNewCartClick() throws  IOException{ //why research is starting on click?
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/doShopping.fxml")));

        //already specified by fxml
        //DoShoppingControllerG controllerG = new DoShoppingControllerG();
        //loader.setController(controllerG);

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    public void setUsernameLabel(String username) {
        usernameLabel.setText(username);
    }
}