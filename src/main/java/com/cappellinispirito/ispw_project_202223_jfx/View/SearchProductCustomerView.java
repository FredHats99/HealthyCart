package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.SearchProductController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nameAndHashmapBeanInterface;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.nameAndHashmapBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class SearchProductCustomerView {

    private void searchProduct() throws IOException, ParseException {
        String productNameFromUser = "";
        //This one should be obtained from user input
        SearchProductController searchProductControllerInstance =SearchProductController.getInstance();
        nameAndHashmapBeanInterface bean = new nameAndHashmapBeanClass();
        bean.setNameToSearch(productNameFromUser);
        searchProductControllerInstance.SearchProduct(bean);
        HashMap<String, String> hmNameAndBarcode;
        hmNameAndBarcode = bean.getHmNameAndBarcode();
        //Now the results should be displayed
    }
}
