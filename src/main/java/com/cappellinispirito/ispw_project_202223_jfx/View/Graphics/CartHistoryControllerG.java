package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class CartHistoryControllerG {
    public HBox topBarHbox;
    public AnchorPane rootAnchorPane;

    public CartHistoryControllerG() throws SQLException {
        //TODO: How to connect graphic controllers and Views?
        //ConsultCartHistoryCustomerView consultCartHistoryCustomerView = new ConsultCartHistoryCustomerView();
        //consultCartHistoryCustomerView.showOldCarts();
    }

    public void onBackButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/Main_menu.fxml")));
        Parent rootNode = loader.load();
        MainMenuControllerG controller = loader.getController();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) rootAnchorPane.getScene().getWindow();
        stage.setScene(myScene);
    }
}
