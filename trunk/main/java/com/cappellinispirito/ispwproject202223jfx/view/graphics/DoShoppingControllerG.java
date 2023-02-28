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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class DoShoppingControllerG implements Initializable, Observer {
    @FXML
    private StackPane root;
    @FXML
    private Label userNameLabel;
    @FXML
    private ImageView NutellaView1;
    @FXML
    private ImageView NutellaView2;
    @FXML
    private ImageView NutellaView3;
    @FXML
    private ImageView NutellaView4;
    @FXML
    private ImageView NutellaView5;
    @FXML
    private ImageView NutellaView6;
    @FXML
    private ImageView NutellaView7;
    @FXML
    private ImageView NutellaView8;
    @FXML
    private ImageView NutellaView9;
    private final List<ImageView> NutellaViews = new ArrayList<>();
    @FXML
    private Label NutellaName1;
    @FXML
    private Label NutellaName2;
    @FXML
    private Label NutellaName3;
    @FXML
    private Label NutellaName4;
    @FXML
    private Label NutellaName5;
    @FXML
    private Label NutellaName6;
    @FXML
    private Label NutellaName7;
    @FXML
    private Label NutellaName8;
    @FXML
    private Label NutellaName9;
    @FXML
    private Polygon leftArrowButton;
    @FXML
    private VBox cartVBox;
    private int pageNumber;
    private final List<Label> NutellaNames = new ArrayList<>();
    private List<Integer> HealthScoreList = new ArrayList<>();
    private ShoppingCart cart;
    private int averageHealthScore;

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
        NutellaViews.add(NutellaView1);
        NutellaViews.add(NutellaView2);
        NutellaViews.add(NutellaView3);
        NutellaViews.add(NutellaView4);
        NutellaViews.add(NutellaView5);
        NutellaViews.add(NutellaView6);
        NutellaViews.add(NutellaView7);
        NutellaViews.add(NutellaView8);
        NutellaViews.add(NutellaView9);

        NutellaNames.add(NutellaName1);
        NutellaNames.add(NutellaName2);
        NutellaNames.add(NutellaName3);
        NutellaNames.add(NutellaName4);
        NutellaNames.add(NutellaName5);
        NutellaNames.add(NutellaName6);
        NutellaNames.add(NutellaName7);
        NutellaNames.add(NutellaName8);
        NutellaNames.add(NutellaName9);


        int j;
        for(j=0;j<9;j++){
            NutellaViews.get(j).setOnMouseClicked(
                    mouseEvent -> {
                        int i;
                        for(i=0;i<9;i++){
                            if(mouseEvent.getSource().equals(NutellaViews.get(i))){
                                try {
                                    view.addItemToCart(i+9*(pageNumber-1));
                                } catch (FailedQueryToOpenFoodFacts | SQLException | IOException | ParseException e) {
                                    e.printStackTrace();
                                }
                                update(cart);
                                //TODO: Observers to display the shopping cart list in real time
                                displayUpdatedCart(i+9*(pageNumber-1));
                                break;
                            }
                        }
                    }
            );
        }

        leftArrowButton.setFill(Color.GRAY);
        SupermarketNameBean bean = SupermarketNameBean.getInstance();
        String chosenSupermarket = bean.getSupermarketName();
        String username = NamePremiumBean.getInstance().getName(); // getName() returns null
        if(username != null && chosenSupermarket != null) {
            userNameLabel.setText(username);
        }
        try {
            view = new DoShoppingCustomerView();
            view.displayShop();
        } catch (FailedQueryToOpenFoodFacts | IOException e) {
            e.printStackTrace();
        }
        cart = (ShoppingCart) view.registerObserver(this);
        int i;
        for(i=0; i<9; i++){
            assert view != null;
            Image tempImage = new Image(String.valueOf(view.getSellableProductImage().get(i)));
            NutellaViews.get(i).setImage(tempImage);
            NutellaNames.get(i).setText(view.getSellableProductName().get(i));
        }
        pageNumber = 1;
        System.out.format("Page:%d%n", pageNumber);
        System.out.printf("%d products found!%n", view.getSellableProductName().size());
    }

    private void displayUpdatedCart(int index) {

        String labelText = view.getSellableProductName().get(index);
        Image image;
        try{
            image = new Image(view.getSellableProductImage().get(index));
        } catch (NullPointerException e){
            image = new Image(String.valueOf(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/icons/question_mark.png")));
        }
        String circleLabelText;
        if(HealthScoreList.get(HealthScoreList.size()-1) == -1){
            circleLabelText = "?";
        } else {
            circleLabelText = HealthScoreList.get(HealthScoreList.size()-1).toString();
        }


        CustomRectangle customRectangle = new CustomRectangle(labelText, image, circleLabelText);
        cartVBox.getChildren().add(customRectangle);
    }

    public void onRightArrowClicked() throws IOException {
        int i;
        System.out.format("Items are %d%n", view.getSellableProductName().size());
        Image tempImage;
        for(i=9*pageNumber; i<9*(pageNumber+1); i++){
            assert view != null;
            try{
                System.out.println(view.getSellableProductName().get(i));
                tempImage = validateImage(view, i);
                NutellaViews.get(i%9).setImage(tempImage);
                NutellaNames.get(i%9).setText(view.getSellableProductName().get(i));
            } catch(IndexOutOfBoundsException e){
                if((pageNumber+1)*9 > view.getSellableProductName().size()){
                    int prevValue;
                    view.loadNewPage();
                    System.out.format("Items are %d because I switched page%n", view.getSellableProductName().size());
                    for(prevValue = i; prevValue<9*(pageNumber+1);prevValue++){
                        System.out.println(view.getSellableProductName().get(prevValue));
                        tempImage = validateImage(view, prevValue);
                        NutellaViews.get(prevValue%9).setImage(tempImage);
                        NutellaNames.get(prevValue%9).setText(view.getSellableProductName().get(prevValue));
                    }
                }
            }
        }
        pageNumber++;
        leftArrowButton.setFill(Paint.valueOf("#1fff8e"));
        System.out.format("Page:%d%n", pageNumber);
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
                    NutellaViews.get(i%9).setImage(tempImage);
                    NutellaNames.get(i%9).setText(view.getSellableProductName().get(i));
                } catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                } catch(IllegalArgumentException e){
                    tempImage = new Image(String.valueOf(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/icons/question_mark.png")));
                    NutellaViews.get(i%9).setImage(tempImage);
                }
            }
            System.out.format("Page:%d%n", pageNumber);
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
            tempImage = new Image(String.valueOf(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/icons/question_mark.png")));
        }
        return tempImage;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof ShoppingCart concreteSubject) {
            int i;
            HealthScoreList = new ArrayList<>();
            for(i=0;i<concreteSubject.getItemsList().size();i++){
                HealthScoreList.add(concreteSubject.getItemsList().get(i).getHealthScore());
            }
            averageHealthScore = concreteSubject.getAverageScore();
            System.out.println(HealthScoreList);
            System.out.println("Average health score is" + averageHealthScore);
        }
    }
}
