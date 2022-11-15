package com.cappellinispirito.ispw_project_202223_jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application{

    @Override
    public void start(Stage myStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/gui.fxml")));
        Scene myScene = new Scene(root);
        Image myIcon = new Image(String.valueOf(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/icons/icon.png")));

        myStage.getIcons().add(myIcon);
        myStage.setTitle("B T Y");
        myStage.setScene(myScene);
        myStage.setResizable(true);
        myStage.show();
    }

    public static void main(String[] args) {

        launch(args);
        System.exit(0);
        /*
        for (Item i: myCart.getItemsList()) {
            System.out.format("Product Name: %s\nFinal Score: %d\nPrice: %f\nNutriscore: %d\n\n", i.getName(), i.getFinalScore().getFinalScoreValue(), i.getPrice(), i.getNutriscore().getNutriscoreValue());
        }
        System.out.format("Total Products: %d\nTotal Price: %f\nAverage Final Score: %d\n\n",myCart.getItemsList().size(), myCart.getTotalPrice(), myCart.getAverageFinalScore());
        */
    }
}
