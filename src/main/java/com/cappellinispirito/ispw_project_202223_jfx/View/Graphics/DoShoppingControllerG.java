package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NamePremiumBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.supermarketNameBean;
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

    public String chosenSupermarket;
    private String username;

    public StackPane root;
    public Label userName_label;

    public DoShoppingControllerG(){}

    public void onBackButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/Main_menu2.fxml")));

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        supermarketNameBean bean = supermarketNameBean.getInstance();
        chosenSupermarket = bean.getSupermarketName();
        username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null) {
            userName_label.setText(username);
        }
    }
}
