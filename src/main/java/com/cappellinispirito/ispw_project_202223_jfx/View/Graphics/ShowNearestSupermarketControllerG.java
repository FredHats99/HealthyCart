package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.View.ShowNearestSupermarketsCustomerView;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NamePremiumBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.supermarketNameBean;
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

    public TextField search_text;
    public Label userName_label;

    private boolean isStartingAShop;
    String username;

    ShowNearestSupermarketsCustomerView view;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null) {
            userName_label.setText(username);
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
        String search = search_text.getText();
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
        if(isStartingAShop){
            Label label = (Label) mouseEvent.getSource();
            if(!Objects.equals(label.getText(), "")){
                supermarketNameBean bean = supermarketNameBean.getInstance();
                bean.setSupermarketName(label.getText());
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/doShopping.fxml")));
                Parent rootNode = loader.load();
                Scene myScene = new Scene(rootNode);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(myScene);
            }
        }
    }

    public void onBackButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/Main_menu2.fxml")));

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    public void setIsStartingAShop(boolean b) {
        this.isStartingAShop = b;
    }
}
