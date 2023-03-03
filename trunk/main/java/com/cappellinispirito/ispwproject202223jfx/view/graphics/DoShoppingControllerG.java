package com.cappellinispirito.ispwproject202223jfx.view.graphics;
import com.cappellinispirito.ispwproject202223jfx.model.ShoppingCart;
import com.cappellinispirito.ispwproject202223jfx.model.Subject;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.view.DoShoppingCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.Observer;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.SupermarketNameBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;



public class DoShoppingControllerG implements Initializable, Observer {
    private static final String QUESTION_MARK = "/com/cappellinispirito/ispwproject202223jfx/icons/question_mark.png";
    @FXML
    private StackPane root;
    @FXML
    private Label userNameLabel;
    @FXML
    private ImageView nutellaView1;
    @FXML
    private ImageView nutellaView2;
    @FXML
    private ImageView nutellaView3;
    @FXML
    private ImageView nutellaView4;
    @FXML
    private ImageView nutellaView5;
    @FXML
    private ImageView nutellaView6;
    @FXML
    private ImageView nutellaView7;
    @FXML
    private ImageView nutellaView8;
    @FXML
    private ImageView nutellaView9;
    private final List<ImageView> nutellaViews = new ArrayList<>();
    @FXML
    private Label nutellaName1;
    @FXML
    private Label nutellaName2;
    @FXML
    private Label nutellaName3;
    @FXML
    private Label nutellaName4;
    @FXML
    private Label nutellaName5;
    @FXML
    private Label nutellaName6;
    @FXML
    private Label nutellaName7;
    @FXML
    private Label nutellaName8;
    @FXML
    private Label nutellaName9;
    @FXML
    private Polygon leftArrowButton;
    @FXML
    private VBox cartVBox;
    @FXML
    private Circle avgScoreCircle;
    @FXML
    private Label avgScoreLabel;
    @FXML
    private StackPane saveCartStackPane;
    @FXML
    private ScrollPane shopScrollPane;
    private int pageNumber=1;
    private final List<Label> nutellaNames = new ArrayList<>();
    private List<Integer> healthScoreList = new ArrayList<>();
    private int averageHealthScore;
    private final List<CustomRectangle> shopList = new ArrayList<>();

    private DoShoppingCustomerView view = null;
    public void onBackButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/Main_menu2.fxml")));

        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        nutellaViews.add(nutellaView1);
        nutellaViews.add(nutellaView2);
        nutellaViews.add(nutellaView3);
        nutellaViews.add(nutellaView4);
        nutellaViews.add(nutellaView5);
        nutellaViews.add(nutellaView6);
        nutellaViews.add(nutellaView7);
        nutellaViews.add(nutellaView8);
        nutellaViews.add(nutellaView9);

        nutellaNames.add(nutellaName1);
        nutellaNames.add(nutellaName2);
        nutellaNames.add(nutellaName3);
        nutellaNames.add(nutellaName4);
        nutellaNames.add(nutellaName5);
        nutellaNames.add(nutellaName6);
        nutellaNames.add(nutellaName7);
        nutellaNames.add(nutellaName8);
        nutellaNames.add(nutellaName9);

        avgScoreCircle.setFill(Color.GRAY);
        avgScoreLabel.setText("0");

        int j;
        for(j=0;j<9;j++){
            nutellaViews.get(j).setOnMouseClicked(this::onAddItem);
        }

        leftArrowButton.setFill(Color.GRAY);
        SupermarketNameBean bean = SupermarketNameBean.getInstance();
        String chosenSupermarket = bean.getSupermarketName();
        String username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null && chosenSupermarket != null) {
            userNameLabel.setText(username);
        }

        if(username != null){
            saveCartStackPane.setOnMouseClicked(mouseEvent -> onSaveCart());
        }

        try {
            view = new DoShoppingCustomerView();
            view.displayShop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i;
        for(i=0; i<9; i++){
            assert view != null;
            Image tempImage = validateImage(view, i);
            nutellaViews.get(i).setImage(tempImage);
            nutellaNames.get(i).setText(view.getSellableProductName().get(i));
        }
        pageNumber = 1;
    }

    private void onAddItem(MouseEvent mouseEvent) {
        int i;
        for(i=0;i<9;i++){
            if(mouseEvent.getSource().equals(nutellaViews.get(i))){
                try {
                    view.addItemToCart(i+9*(pageNumber-1));
                } catch (FailedQueryToOpenFoodFacts | SQLException | IOException | ParseException e) {
                    e.printStackTrace();
                }
                displayUpdatedCart(i+9*(pageNumber-1));
                updateAverageScore();
                break;
            }
        }
    }

    private void updateAverageScore() {
        avgScoreLabel.setText(String.valueOf(averageHealthScore));
        if(averageHealthScore==0 && healthScoreList.isEmpty()){
            avgScoreCircle.setFill(Color.GRAY);
        } else if(averageHealthScore<30){
            avgScoreCircle.setFill(Color.RED);
        } else if(averageHealthScore<60){
            avgScoreCircle.setFill(Color.YELLOW);
        } else {
            avgScoreCircle.setFill(Color.LIGHTGREEN);
        }
    }

    private void displayUpdatedCart(int index) {

        String labelText = view.getSellableProductName().get(index);
        Image image;
        try{
            image = new Image(view.getSellableProductImage().get(index));
        } catch (NullPointerException e){
            image = new Image(String.valueOf(getClass().getResource(QUESTION_MARK)));
        }
        String circleLabelText;
        if(healthScoreList.get(healthScoreList.size()-1) == -1){
            circleLabelText = "?";
        } else {
            circleLabelText = healthScoreList.get(healthScoreList.size()-1).toString();
        }


        CustomRectangle customRectangle = new CustomRectangle(labelText, image, circleLabelText);
        shopList.add(customRectangle);
        customRectangle.setOnMouseClicked(mouseEvent -> onRemoveItem(customRectangle));
        cartVBox.getChildren().add(customRectangle);
        cartVBox.setPrefHeight(cartVBox.getHeight() + customRectangle.getHeight());
        shopScrollPane.setHmin(cartVBox.getHeight());
    }

    private void onRemoveItem(CustomRectangle customRectangle) {
        int indexOf = shopList.indexOf(customRectangle);
        cartVBox.getChildren().remove(cartVBox.getChildren().get(indexOf));
        shopList.remove(indexOf);
        //Reindexing of other values...
        for(CustomRectangle customRectangle1: shopList){
            customRectangle1.setOnMouseClicked(mouseEvent -> onRemoveItem(customRectangle1));
        }
        view.removeItemFromCart(indexOf);
        updateAverageScore();
    }

    public void onRightArrowClicked() throws IOException, FailedQueryToOpenFoodFacts {
        int i;
        Image tempImage;
        for(i=9*pageNumber; i<9*(pageNumber+1); i++){
            assert view != null;
            try{
                tempImage = validateImage(view, i);
                nutellaViews.get(i%9).setImage(tempImage);
                nutellaNames.get(i%9).setText(view.getSellableProductName().get(i));
            } catch(IndexOutOfBoundsException e){
                if((pageNumber+1)*9 > view.getSellableProductName().size()){
                    int prevValue;
                    view.loadNewPage();
                    for(prevValue = i; prevValue<9*(pageNumber+1);prevValue++){
                        tempImage = validateImage(view, prevValue);
                        nutellaViews.get(prevValue%9).setImage(tempImage);
                        nutellaNames.get(prevValue%9).setText(view.getSellableProductName().get(prevValue));
                    }
                }
            }
        }
        pageNumber++;
        leftArrowButton.setFill(Paint.valueOf("#1fff8e"));
    }

    public void onLeftArrowClicked(){
        Image tempImage;
        if(pageNumber>1){
            pageNumber--;
            int i;
            for(i=9*(pageNumber-1); i<9*pageNumber; i++){
                assert view != null;
                try{
                    tempImage = new Image(String.valueOf(view.getSellableProductImage().get(i)));
                    nutellaViews.get(i%9).setImage(tempImage);
                    nutellaNames.get(i%9).setText(view.getSellableProductName().get(i));
                } catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                } catch(IllegalArgumentException e){
                    tempImage = new Image(String.valueOf(getClass().getResource(QUESTION_MARK)));
                    nutellaViews.get(i%9).setImage(tempImage);
                }
            }
            if(pageNumber==1){
                leftArrowButton.setFill(Color.GRAY);
            }

        }
    }

    private Image validateImage(DoShoppingCustomerView view, int prevValue){
        Image tempImage;
        try{
            tempImage = new Image(String.valueOf(view.getSellableProductImage().get(prevValue)));
        } catch(IllegalArgumentException e){
            tempImage = new Image(String.valueOf(getClass().getResource(QUESTION_MARK)));
        }
        return tempImage;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof ShoppingCart concreteSubject) {
            int i;
            healthScoreList = new ArrayList<>();
            for(i=0;i<concreteSubject.getItemsList().size();i++){
                healthScoreList.add(concreteSubject.getItemsList().get(i).getHealthScore());
            }
            averageHealthScore = concreteSubject.getAverageScore();
        }
    }

    public void onSaveCart(){
        try{
            view.saveCart();
            onBackButton();
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }
}
