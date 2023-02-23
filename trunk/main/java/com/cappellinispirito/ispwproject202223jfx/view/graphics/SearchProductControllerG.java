package com.cappellinispirito.ispwproject202223jfx.view.graphics;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.view.SearchProductCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.ProductNameBean;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SearchProductControllerG implements Initializable {
    @FXML
    private StackPane root;
    @FXML
    private StackPane stack1;
    @FXML
    private StackPane stack2;
    @FXML
    private StackPane stack3;
    @FXML
    private StackPane stack4;
    @FXML
    private StackPane stack5;
    @FXML
    private StackPane stack6;
    private List<StackPane> sl = new ArrayList<>();

    @FXML
    private Label productName1;
    @FXML
    private Label productName2;
    @FXML
    private Label productName3;
    @FXML
    private Label productName4;
    @FXML
    private Label productName5;
    @FXML
    private Label productName6;
    private List<Label> productsNames = new ArrayList<>();

    @FXML
    private ImageView productImage1;
    @FXML
    private ImageView productImage2;
    @FXML
    private ImageView productImage3;
    @FXML
    private ImageView productImage4;
    @FXML
    private ImageView productImage5;
    @FXML
    private ImageView productImage6;

    private List<ImageView> productImages = new ArrayList<>();

    @FXML
    private RadioButton item1;
    @FXML
    private RadioButton item2;
    @FXML
    private RadioButton item3;
    @FXML
    private RadioButton item4;
    @FXML
    private RadioButton item5;
    @FXML
    private RadioButton item6;
    private final List<RadioButton> itemsButtons = new ArrayList<>();

    @FXML
    private RadioButton searchButton;
    @FXML
    private TextField searchText;
    @FXML
    private Label userNameLabel;

    String username;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null) {
            userNameLabel.setText(username);
        }
    }



    public void searchProduct(String searchText) throws IOException, FailedQueryToOpenFoodFacts, ParseException {
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
                Logger logger = Logger.getLogger(SearchProductControllerG.class.getName());
                logger.log(Level.INFO, e.getMessage());
            }

        }
    }

    public void onEnterPressed() throws FailedQueryToOpenFoodFacts, IOException, ParseException {
        String searchTextL = this.searchText.getText();
        searchProduct(searchTextL);

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

    public void onSearchProductButtonClicked() throws FailedQueryToOpenFoodFacts, IOException, ParseException {
        String searchTextL = this.searchText.getText();
        searchProduct(searchTextL);

        itemsButtons.add(item1);
        itemsButtons.add(item2);
        itemsButtons.add(item3);
        itemsButtons.add(item4);
        itemsButtons.add(item5);
        itemsButtons.add(item6);
    }

    public void onItemClicked(MouseEvent mouseEvent) throws IOException {


        RadioButton clickedButton = (RadioButton) mouseEvent.getSource();
        //get the index
        int i;
        for(i=0;i<6;i++){
            if(clickedButton == itemsButtons.get(i)){
                ProductNameBean bean = ProductNameBean.getInstance();
                bean.setName(String.valueOf(productsNames.get(i).getText()));
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
        this.username = username;
    }
}
