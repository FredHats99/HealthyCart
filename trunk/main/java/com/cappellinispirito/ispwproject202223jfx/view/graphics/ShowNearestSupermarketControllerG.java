package com.cappellinispirito.ispwproject202223jfx.view.graphics;

import com.cappellinispirito.ispwproject202223jfx.view.ShowNearestSupermarketsCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NewShopBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.SupermarketNameBean;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShowNearestSupermarketControllerG implements Initializable {

    public StackPane root;

    public Label market1;
    public Label market2;
    public Label market3;
    public Label market4;
    public Label market5;
    public List<Label> marketAddressesList = new ArrayList<>();

    public Label marketDist1;
    public Label marketDist2;
    public Label marketDist3;
    public Label marketDist4;
    public Label marketDist5;
    public List<Label> marketDistancesList = new ArrayList<>();

    public TextField searchText;
    public Label userNameLabel;

    String username;

    ShowNearestSupermarketsCustomerView view;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null) {
            userNameLabel.setText(username);
        }


    }

    public void onSearchSupermarketsClicked() throws Exception {
        marketAddressesList.add(market1);
        marketAddressesList.add(market2);
        marketAddressesList.add(market3);
        marketAddressesList.add(market4);
        marketAddressesList.add(market5);

        marketDistancesList.add(marketDist1);
        marketDistancesList.add(marketDist2);
        marketDistancesList.add(marketDist3);
        marketDistancesList.add(marketDist4);
        marketDistancesList.add(marketDist5);

        view = new ShowNearestSupermarketsCustomerView();
        String search = searchText.getText();
        view.showNearestFrom(search);
        List<String> supermarketNames = view.getSupermarketsNames();
        List<Float> supermarketDistances = view.getSupermarketsDistances();
        int i;
        for(i=0; i< supermarketNames.size(); i++){
            marketAddressesList.get(i).setText(String.format("%s", supermarketNames.get(i)));
            marketDistancesList.get(i).setText(String.format("%s Km", supermarketDistances.get(i)));
        }
    }



    public void onSupermarketClicked(MouseEvent mouseEvent) throws IOException {
        if(NewShopBean.getInstance().getNewShop()){
            Label label = (Label) mouseEvent.getSource();
            if(!Objects.equals(label.getText(), "")){
                SupermarketNameBean bean = SupermarketNameBean.getInstance();
                bean.setSupermarketName(label.getText());

                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/doShopping.fxml")));
                Parent rootNode = loader.load();
                Scene myScene = new Scene(rootNode);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(myScene);
            }
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
