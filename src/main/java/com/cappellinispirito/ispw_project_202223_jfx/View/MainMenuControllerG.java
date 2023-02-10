package com.cappellinispirito.ispw_project_202223_jfx.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainMenuControllerG {

    private Stage applicationStage;
    @FXML
    public StackPane root;
    public Label appTitle;
    public HBox topBar;
    public ImageView logo;
    public Pane bgPane;
    public VBox rootVbox;
    public HBox topbarHbox;
    public Pane centerbgPane;
    public SVGPath gearSvg;
    public Pane NewGroceryButton;

    public MainMenuControllerG(){
    }

    public void onCartHistoryClicked() throws IOException {
        //initialize new scene
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/history.fxml")));
        Parent rootNode = loader.load();
        CartHistoryControllerG controller = loader.getController();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }
}