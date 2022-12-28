package com.cappellinispirito.ispw_project_202223_jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.net.HttpURLConnection;



public class Main extends Application{

    @Override
    public void start(Stage myStage) throws IOException {

        //node
        Parent rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/gui.fxml")));
        //scene
        Scene myScene = new Scene(rootNode);
        //stylesheet
        myScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/MyStylesheet.css")).toExternalForm());
        //windows icon
        Image myIcon = new Image(String.valueOf(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/icons/apple.png")));
        myStage.getIcons().add(myIcon);
        //name
        myStage.setTitle("B T Y");
        //miscellaneous
        myStage.setResizable(true);

        //setting up the Node -> Scene -> Stage
        myStage.setScene(myScene);
        myStage.show();
    }

    public static void main(String[] args) throws IOException {

        //launch(args);
        //System.exit(0);



        /*
        for (Item i: myCart.getItemsList()) {
            System.out.format("Product Name: %s\nFinal Score: %d\nPrice: %f\nNutriscore: %d\n\n", i.getName(), i.getFinalScore().getFinalScoreValue(), i.getPrice(), i.getNutriscore().getNutriscoreValue());
        }
        System.out.format("Total Products: %d\nTotal Price: %f\nAverage Final Score: %d\n\n",myCart.getItemsList().size(), myCart.getTotalPrice(), myCart.getAverageFinalScore());
        */
    }
}
