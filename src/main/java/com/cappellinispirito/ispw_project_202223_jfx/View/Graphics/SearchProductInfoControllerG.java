package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.View.ShowProductInfoCustomerView;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameBean;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;

public class SearchProductInfoControllerG {

    public Label productNameLabelINFO;
    public ImageView productImageInfo;
    public Label itemScoretext;
    public Label infotext3;
    public Label infotext2;
    public Label infotext1;
    public Label isBioTextInfo;
    private String itemName;
    private Item itemInfo;

    public SearchProductInfoControllerG() throws FailedQueryToOpenFoodFacts, SQLException, IOException, ParseException {
        NameBean bean = NameBean.getInstance();
        itemName = bean.getName();
        System.out.println(itemName);
        ShowProductInfoCustomerView view = new ShowProductInfoCustomerView();
        itemInfo = view.ShowProductInfo(itemName);
    }

    public void displayProductInfo(){
        Image tempImage = new Image(String.valueOf(itemInfo.getImageUrl()));
        productImageInfo.setImage(tempImage);
        productNameLabelINFO.setText(itemInfo.getName());
    }
}
