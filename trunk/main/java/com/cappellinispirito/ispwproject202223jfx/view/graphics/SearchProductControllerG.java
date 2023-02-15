package com.cappellinispirito.ispwproject202223jfx.view.graphics;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.view.SearchProductCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.ProductNameBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class SearchProductControllerG implements Initializable {
    @FXML
    public StackPane root;

    public StackPane stack1;
    public StackPane stack2;
    public StackPane stack3;
    public StackPane stack4;
    public StackPane stack5;
    public StackPane stack6;
    public List<StackPane> sl = new ArrayList<>();

    public Label productName1;
    public Label productName2;
    public Label productName3;
    public Label productName4;
    public Label productName5;
    public Label productName6;
    public List<Label> productsNames = new ArrayList<>();

    public ImageView productImage1;
    public ImageView productImage2;
    public ImageView productImage3;
    public ImageView productImage4;
    public ImageView productImage5;
    public ImageView productImage6;
    public List<ImageView> productImages = new ArrayList<>();

    public RadioButton item1;
    public RadioButton item2;
    public RadioButton item3;
    public RadioButton item4;
    public RadioButton item5;
    public RadioButton item6;
    public List<RadioButton> itemsButtons = new ArrayList<>();

    public RadioButton searchButton;
    public TextField searchText;

    public Label userNameLabel;

    String username;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null) {
            userNameLabel.setText(username);
        }
    }



    public void searchProduct(String searchText) throws IOException, FailedQueryToOpenFoodFacts, SQLException, ParseException {
        productsNames.add(productName1);
        productsNames.add(productName2);
        productsNames.add(productName3);
        productsNames.add(productName4);
        productsNames.add(productName5);
        productsNames.add(productName6);

        productImages.add(productImage1);
        productImages.add(productImage2);
        productImages.add(productImage3);
        productImages.add(productImage4);
        productImages.add(productImage5);
        productImages.add(productImage6);

        sl.add(stack1);
        sl.add(stack2);
        sl.add(stack3);
        sl.add(stack4);
        sl.add(stack5);
        sl.add(stack6);



        SearchProductCustomerView searchProductCustomerView = new SearchProductCustomerView();
        searchProductCustomerView.searchProduct(searchText);
        List<String> resultsNames = searchProductCustomerView.getResultsNames();
        List<String> resultsImages = searchProductCustomerView.getResultsImages();

        int i;
        for(i=0;i<resultsNames.size();i++){
            try{
                productsNames.get(i).setText(resultsNames.get(i));
                Image tempImage = new Image(String.valueOf(resultsImages.get(i)));
                productImages.get(i).setImage(tempImage);
                sl.get(i).setVisible(true);
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public void onEnterPressed(ActionEvent e) throws FailedQueryToOpenFoodFacts, IOException, ParseException, SQLException {
        String searchText = this.searchText.getText();
        searchProduct(searchText);

        itemsButtons.add(item1);
        itemsButtons.add(item2);
        itemsButtons.add(item3);
        itemsButtons.add(item4);
        itemsButtons.add(item5);
        itemsButtons.add(item6);

        sl.add(stack1);
        sl.add(stack2);
        sl.add(stack3);
        sl.add(stack4);
        sl.add(stack5);
        sl.add(stack6);
    }

    public void onSearchProductButtonClicked() throws FailedQueryToOpenFoodFacts, IOException, ParseException, SQLException {
        String searchText = this.searchText.getText();
        searchProduct(searchText);

        itemsButtons.add(item1);
        itemsButtons.add(item2);
        itemsButtons.add(item3);
        itemsButtons.add(item4);
        itemsButtons.add(item5);
        itemsButtons.add(item6);
    }

    public void onItemClicked(MouseEvent mouseEvent) throws IOException, FailedQueryToOpenFoodFacts, SQLException, ParseException {


        RadioButton clickedButton = (RadioButton) mouseEvent.getSource();
        //get the index
        int i;
        for(i=0;i<6;i++){
            if(clickedButton == itemsButtons.get(i)){
                ProductNameBean bean = ProductNameBean.getInstance();
                bean.setName(String.valueOf(productsNames.get(i).getText()));
                System.out.format("SPC will send %s..\n", String.valueOf(productsNames.get(i).getText()));
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/itemInfo.fxml")));
                Parent rootNode = loader.load();
                SearchProductInfoControllerG controller = loader.getController();
                Scene myScene = new Scene(rootNode);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(myScene);
                controller.displayProductInfo();
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

    public void setUsername(String username) {

    }
}
