package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SearchProductControllerG {
    @FXML
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

    public RadioButton searchButton;
    public TextField search_text;

    String searchText;

    public SearchProductControllerG() throws FailedQueryToOpenFoodFacts, IOException, ParseException {
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
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }
    public void onSearchProductButtonClicked() throws FailedQueryToOpenFoodFacts, IOException, ParseException {
        String searchText = search_text.getText();
        searchProduct(searchText);
    }
}
