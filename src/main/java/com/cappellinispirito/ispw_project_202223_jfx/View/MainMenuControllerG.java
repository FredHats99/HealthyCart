package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ConsultCartHistoryController;
import com.cappellinispirito.ispw_project_202223_jfx.Controller.LogInController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MainMenuControllerG {

    private Stage applicationStage;
    @FXML
    public StackPane root;
    public TextField search_text;
    public RadioButton newcart_button;
    public ImageView Label_View;
    public RadioButton findstore_button;
    //public Text userName_label;

    public MainMenuControllerG() throws FailedQueryToOpenFoodFacts {
        LogInCustomerView logInCustomerView = new LogInCustomerView();
        SearchProductCustomerView searchProductCustomerView = new SearchProductCustomerView();
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