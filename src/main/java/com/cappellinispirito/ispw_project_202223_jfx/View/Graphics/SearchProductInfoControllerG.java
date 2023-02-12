package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.View.ShowProductInfoCustomerView;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NamePremiumBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SearchProductInfoControllerG {

    public StackPane root;

    public Label productNameLabelINFO;
    public ImageView productImageInfo;
    public Label itemScoretext;
    public Label infotext3;
    public Label infotext2;
    public Label infotext1;
    public Label isBioTextInfo;
    private final Item itemInfo;
    public Rectangle scoreRect;

    public SearchProductInfoControllerG() throws FailedQueryToOpenFoodFacts, SQLException, IOException, ParseException {
        NamePremiumBean bean = NamePremiumBean.getInstance();
        String itemName = bean.getName();
        System.out.println(itemName);
        ShowProductInfoCustomerView view = new ShowProductInfoCustomerView();
        itemInfo = view.ShowProductInfo(itemName);

    }

    public void displayProductInfo(){
        Image tempImage = new Image(String.valueOf(itemInfo.getImageUrl()));
        productImageInfo.setImage(tempImage);
        productNameLabelINFO.setText(itemInfo.getName());

        itemScoretext.setText(String.valueOf(itemInfo.getHealthScore()));
        if(itemInfo.getHealthScore() < 30){
            scoreRect.setFill(Paint.valueOf("#FF8469"));
        } else if (itemInfo.getHealthScore() < 60 && itemInfo.getHealthScore() >= 30) {
            scoreRect.setFill(Paint.valueOf("#FFEF98"));
        } else {
            scoreRect.setFill(Paint.valueOf("#89FF98"));
        }
        //bro the commit...
        infotext1.setText(String.format("SCHEDA NUTRIZIONALE:\nCalorie: %f kJ\nGrassi saturi: %f g\nZuccheri: %f g\nSale: %f g\nFibre: %f g\nProteine: %f g\n Frutta e altro: %f\n", itemInfo.getCalories(), itemInfo.getSaturatedFats(), itemInfo.getSugars(), itemInfo.getSalt(), itemInfo.getFibers(), itemInfo.getProteins(), itemInfo.getFruitPercentage()));
        infotext2.setText(String.format("INGREDIENTI:\n %s", itemInfo.getIngredients()));
        infotext3.setText(String.format("ADDITIVI: \n %s", itemInfo.getAdditives()));
    }

    public void onBackButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispw_project_202223_jfx/fxml/search.fxml")));
        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }
}
