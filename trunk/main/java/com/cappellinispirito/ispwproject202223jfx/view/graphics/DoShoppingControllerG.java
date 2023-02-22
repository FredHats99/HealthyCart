package com.cappellinispirito.ispwproject202223jfx.view.graphics;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.SupermarketNameBean;
import javafx.fxml.FXML;
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


public class DoShoppingControllerG implements Initializable {
    @FXML
    private StackPane root;
    @FXML
    private Label userNameLabel;

    public void onBackButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/Main_menu2.fxml")));

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        SupermarketNameBean bean = SupermarketNameBean.getInstance();
        String chosenSupermarket = bean.getSupermarketName();
        String username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null && chosenSupermarket != null) {
            userNameLabel.setText(username);
        }
    }
}
