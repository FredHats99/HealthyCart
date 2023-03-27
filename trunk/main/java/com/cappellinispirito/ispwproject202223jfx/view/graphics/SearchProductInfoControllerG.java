package com.cappellinispirito.ispwproject202223jfx.view.graphics;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.Item;
import com.cappellinispirito.ispwproject202223jfx.view.ShowProductInfoCustomerView;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NamePremiumBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.ProductNameBean;
import javafx.fxml.FXML;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchProductInfoControllerG implements Initializable {
    @FXML
    private StackPane root;
    @FXML
    private Label productNameLabelINFO;
    @FXML
    private ImageView productImageInfo;
    @FXML
    private Label itemScoretext;
    @FXML
    private Label infotext3;
    @FXML
    private Label infotext2;
    @FXML
    private Label infotext1;
    @FXML
    private Label isBioTextInfo;
    private Item itemInfo;
    @FXML
    private Rectangle scoreRect;
    @FXML
    private Label userNameLabel;
    @FXML
    private Rectangle bioRect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductNameBean bean = ProductNameBean.getInstance();
        String itemName = bean.getName();
        ShowProductInfoCustomerView view = new ShowProductInfoCustomerView();
        try {
            itemInfo = view.showProductInfo(itemName);
        } catch (IOException | ParseException | SQLException e) {
            Logger logger = Logger.getLogger(SearchProductControllerG.class.getName());
            logger.log(Level.INFO, e.getMessage());
        }
        userNameLabel.setText(NamePremiumBean.getInstance().getName());

    }

    public void displayProductInfo(){
        Image tempImage = new Image(String.valueOf(itemInfo.getImageUrl()));
        productImageInfo.setImage(tempImage);
        productNameLabelINFO.setText(itemInfo.getName());
        infotext1.setText(String.format("Valori per 100g:%nCalorie: %d J%nZuccheri: %d g%nAcidi Grassi: %d g%nSale: %d g%nProteine: %d g%nFibre: %d g%nPercentuale di frutta: %d ", (int) itemInfo.getCalories(),(int) itemInfo.getSugars(),(int) itemInfo.getSaturatedFats(),(int) itemInfo.getSalt(),(int) itemInfo.getProteins(),(int) itemInfo.getFibers(),(int) itemInfo.getFruitPercentage()));
        infotext2.setText(String.format("INGREDIENTI:%n %s", itemInfo.getIngredients()));
        infotext3.setText(String.format("ADDITIVI:%n %s", itemInfo.getAdditives()));
        itemScoretext.setText(String.valueOf(itemInfo.getHealthScore()));
        if(itemInfo.getIsBiological()){
            isBioTextInfo.setText("This product IS bio");
            bioRect.setFill(Paint.valueOf("#89FF98"));
        }
        if(itemInfo.getHealthScore() < 30){
            scoreRect.setFill(Paint.valueOf("#FF8469"));
        } else if (itemInfo.getHealthScore() < 60 && itemInfo.getHealthScore() >= 30) {
            scoreRect.setFill(Paint.valueOf("#FFEF98"));
        } else {
            scoreRect.setFill(Paint.valueOf("#89FF98"));
        }

        if(itemInfo.getCalories() < 0){
            infotext1.setText(String.format("Alcuni valori nutrizionali non sono forniti. %nNon ci fidiamo di questo prodotto."));
            itemScoretext.setText("0");
            scoreRect.setFill(Paint.valueOf("#FF8469"));
        }
        if(Objects.equals(itemInfo.getIngredients(), "")){
            infotext2.setText("Non sono stati trovati gli ingredienti...");
        }
        if(itemInfo.getAdditives().isEmpty()){
            infotext3.setText("Questo prodotto non contiene additivi!");
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
