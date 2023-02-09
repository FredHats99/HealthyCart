package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ShowProductInfoController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nameBarcodeAndItemInfoBeanInterface;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameBarcodeAndItemInfoBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ShowProductInfoCustomerView {


    private void ShowProductInfo() throws IOException, ParseException {
        String name = "";
        //This one should be obtained from user input, parsed via index
        ShowProductInfoController showProductInfoController = ShowProductInfoController.getInstance();
        nameBarcodeAndItemInfoBeanInterface bean = new NameBarcodeAndItemInfoBeanClass();
        bean.setName(name);
        showProductInfoController.findProductInfo(bean);
        //display item info
    }
}
