package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.View.SearchProductCustomerView;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SearchProductControllerG {
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
    public TextField search_text;

    String searchText;

    public SearchProductControllerG() throws FailedQueryToOpenFoodFacts, IOException, ParseException {
        // if bean.value != NULL -> searchProduct(bean.value)
    }


    public void searchProduct(String searchText) throws FailedQueryToOpenFoodFacts, IOException, ParseException {
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
    public void onSearchProductButtonClicked() throws FailedQueryToOpenFoodFacts, IOException, ParseException {
        String searchText = search_text.getText();
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

    public void onItemClicked(MouseEvent mouseEvent) throws IOException, FailedQueryToOpenFoodFacts, SQLException, ParseException {


        RadioButton clickedButton = (RadioButton) mouseEvent.getSource();
        //get the index
        int i;
        for(i=0;i<6;i++){
            if(clickedButton == itemsButtons.get(i)){
                NameBean bean = NameBean.getInstance();
                bean.setName(String.valueOf(productsNames.get(i).getText()));
                System.out.format("SPC will send %s..\n", String.valueOf(productsNames.get(i).getText()));
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/itemInfo.fxml")));
                Parent rootNode = loader.load();
                SearchProductInfoControllerG controller = loader.getController();
                Scene myScene = new Scene(rootNode);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(myScene);
                controller.displayProductInfo();
            }
        }
    }

    public void onBackButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/Main_Menu2.fxml")));
        MainMenuControllerG controller = loader.getController();
        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }
}
