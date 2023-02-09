package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ShowProductInfoController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nameBarcodeAndItemInfoBeanInterface;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameBarcodeAndItemInfoBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;

public class ShowProductInfoCustomerView {


    private void ShowProductInfo() throws IOException, ParseException, SQLException {
        String name = "";
        //This one should be obtained from user input, parsed via index
        ShowProductInfoController showProductInfoController = ShowProductInfoController.getInstance();
        nameBarcodeAndItemInfoBeanInterface bean = new NameBarcodeAndItemInfoBeanClass();
        bean.setName(name);
        showProductInfoController.findProductInfo(bean);

        bean.getScore();
        bean.getBarcode();
        bean.getName();
        bean.getImageUrl();
        bean.getIngredients();
        bean.getEnergy();
        bean.getSugars();
        bean.getSaturatedFats();
        bean.getSalt();
        bean.getFruitPercentage();
        bean.getFibers();
        bean.getProteins();
        bean.getAdditives();
        bean.getIsBeverage();
        bean.getIsBio();

        //display item info
    }
}
