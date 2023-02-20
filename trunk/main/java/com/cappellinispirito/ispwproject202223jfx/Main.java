package com.cappellinispirito.ispwproject202223jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;



public class Main extends Application{
    Stage applicationStage;

    @Override
    public void start(Stage myStage) throws IOException {

        //nodeee
        Parent rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/Main_menu2.fxml")));
        //scene
        Scene myScene = new Scene(rootNode);
        //stylesheet
        myScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/MyStylesheet2.css")).toExternalForm());
        //windows icon
        Image myIcon = new Image(String.valueOf(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/icons/logo4.png")));
        myStage.getIcons().add(myIcon);
        //name
        myStage.setTitle("HealthyCart");
        //miscellaneous
        myStage.setResizable(false);
        //initializing variables
        this.applicationStage = myStage;
        //setting up the Node -> Scene -> Stage
        myStage.setScene(myScene);
        myStage.show();
    }

    public static void main(String[] args){
        //testing
        //Coca-cola 1 lt: 5449000133328
        //Nutella: 3017620422003
        //String barcode = "3017620422003"; //it's the barcode of nutella
        launch(args);

    }
}
