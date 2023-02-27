package com.cappellinispirito.ispwproject202223jfx.view.graphics;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.view.DoShoppingCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.SupermarketNameBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class DoShoppingControllerG implements Initializable {
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
    private int pageNumber;
    private final List<Label> NutellaNames = new ArrayList<>();

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
        System.out.format("Page:%d%n", pageNumber);
    }

    public void onLeftArrowClicked(){
        Image tempImage;
        if(pageNumber>0){
            pageNumber--;
            int i;
            for(i=9*pageNumber; i<9*(pageNumber+1); i++){
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
}
