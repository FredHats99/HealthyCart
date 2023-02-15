package com.cappellinispirito.ispwproject202223jfx.view.graphics;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.Item;
import com.cappellinispirito.ispwproject202223jfx.view.ShowProductInfoCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.ProductNameBean;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchProductInfoControllerG implements Initializable {

    public StackPane root;

    public Label productNameLabelINFO;
    public ImageView productImageInfo;
    public Label itemScoretext;
    public Label infotext3;
    public Label infotext2;
    public Label infotext1;
    public Label isBioTextInfo;
    private Item itemInfo;
    public Rectangle scoreRect;
    public Label userNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductNameBean bean = ProductNameBean.getInstance();
        String itemName = bean.getName();
        ShowProductInfoCustomerView view = new ShowProductInfoCustomerView();
        try {
            itemInfo = view.showProductInfo(itemName);
        } catch (IOException | ParseException | SQLException | FailedQueryToOpenFoodFacts e) {
            e.printStackTrace();
        }
        userNameLabel.setText(NamePremiumBean.getInstance().getName());

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
    }

    public void onBackButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/cappellinispirito/ispwproject202223jfx/fxml/search.fxml")));
        Parent rootNode = loader.load();
        Scene myScene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(myScene);
    }
}
