package com.cappellinispirito.ispw_project_202223_jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    //TODO check for better solution then create those Main attributes used to communicate with controller (maybe all the logic in controller?)


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
        Item food1 = new Item(true,191, 8.3f, 100, 0.05f,0.01f,0.5f,0.5f,0,0,0,0, true,1.5f,"Succo d'arancia Carrefour");
        Item food2 = new Item(false,2010, 38f, 0, 5.2f,0.74f,2.7f,5.4f,0,0,1,0, false,2.5f,"Oreo");
        Item food3 = new Item(false,2565, 4f, 100, 6.8f,0.01f,8.5f,26f,0,0,0,0, true,5,"Crema di arachidi");
        Item food4 = new Item(true,1, 0, 0, 0,0.02f,0,0,0,0,1,0, false,1.5f,"CocaCola Zero");
        myCart.addItem(food1);
        myCart.addItem(food2);
        myCart.addItem(food3);
        myCart.addItem(food4);

        myCart.setTotalPrice();
        myCart.setAverageFinalScore();

        for (Item i: myCart.getItemsList()) {
            System.out.format("Product Name: %s\nFinal Score: %d\nPrice: %f\nNutriscore: %d\n\n", i.getName(), i.getFinalScore().getFinalScoreValue(), i.getPrice(), i.getNutriscore().getNutriscoreValue());
        }

        System.out.format("Total Products: %d\nTotal Price: %f\nAverage Final Score: %d\n\n",myCart.getItemsList().size(), myCart.getTotalPrice(), myCart.getAverageFinalScore());


        //launch();
    }
}
