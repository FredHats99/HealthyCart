package com.cappellinispirito.ispw_project_202223_jfx;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application{
    Stage applicationStage;

    @Override
    public void start(Stage myStage) throws IOException {

        //node
        Parent rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/Main_menu.fxml")));
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
        myStage.setResizable(false);
        //initializing variables
        this.applicationStage = myStage;
        //setting up the Node -> Scene -> Stage
        myStage.setScene(myScene);
        myStage.show();
    }

    public static void main(String[] args) throws IOException, ParseException {
        //testing
        //Coca-cola 1 lt: 5449000133328
        //Nutella: 3017620422003
        String barcode = "3017620422003"; //it's the barcode of nutella
        productDAO communicator = new ProductDAOImpl();
        Item Nutella = communicator.getProductByBarcode(barcode);
        //System.out.println(Nutella.getNutriscore().getNutriscoreValue());
        launch(args);
        //System.exit(0);
        /*
        for (Item i: myCart.getItemsList()) {
            System.out.format("Product Name: %s\nFinal Score: %d\nPrice: %f\nNutriscore: %d\n\n", i.getName(), i.getFinalScore().getFinalScoreValue(), i.getPrice(), i.getNutriscore().getNutriscoreValue());
        }
        System.out.format("Total Products: %d\nTotal Price: %f\nAverage Final Score: %d\n\n",myCart.getItemsList().size(), myCart.getTotalPrice(), myCart.getAverageFinalScore());
        */
    }
}
