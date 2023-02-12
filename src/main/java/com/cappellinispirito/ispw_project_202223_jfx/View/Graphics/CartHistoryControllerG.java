package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class CartHistoryControllerG {

    public AnchorPane rootAnchorPane;

    public CartHistoryControllerG(){
    }

    public void onBackButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/Main_menu.fxml")));
        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) rootAnchorPane.getScene().getWindow();
        stage.setScene(myScene);
    }
}
