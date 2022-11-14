package com.cappellinispirito.ispw_project_202223_jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 320);
        stage.setTitle("BetterThenYuka");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        ShoppingCart myCart = new ShoppingCart();
        System.exit(0);

        /*
        for (Item i: myCart.getItemsList()) {
            System.out.format("Product Name: %s\nFinal Score: %d\nPrice: %f\nNutriscore: %d\n\n", i.getName(), i.getFinalScore().getFinalScoreValue(), i.getPrice(), i.getNutriscore().getNutriscoreValue());
        }
        System.out.format("Total Products: %d\nTotal Price: %f\nAverage Final Score: %d\n\n",myCart.getItemsList().size(), myCart.getTotalPrice(), myCart.getAverageFinalScore());
        */

        //launch();
    }
}
