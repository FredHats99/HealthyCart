package com.cappellinispirito.ispw_project_202223_jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    //TODO check for better solution then create those Main attributes used to communicate with controller (maybe all the logic in controller?)
    public static int additiveAtRun;
    public static int nutriscoreAtRun;
    public static int nutriscore60AtRun;
    public static int finalScoreAtRun;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 320);
        stage.setTitle("BetterThenYuka");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Item pringles = new Item(false,2180, 25f, 41, 9f,0.35f,6.6f,12f,0,0,0,1, false);
        additiveAtRun = pringles.getFinalScore().getAdditivesScore();
        nutriscoreAtRun = pringles.getNutriscore().getNutriscoreValue();
        nutriscore60AtRun = pringles.getFinalScore().getNutriscore60();
        finalScoreAtRun = pringles.getFinalScore().getFinalScoreValue();

        launch();
    }
}
