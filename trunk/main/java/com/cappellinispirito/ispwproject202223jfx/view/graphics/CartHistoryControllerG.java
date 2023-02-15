package com.cappellinispirito.ispwproject202223jfx.view.graphics;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CartHistoryControllerG implements Initializable {

    public StackPane root;
    public Label userNameLabel;

    String username;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null) {
            userNameLabel.setText(username);
        }
    }

    public void onBackButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/Main_menu2.fxml")));

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }
}
