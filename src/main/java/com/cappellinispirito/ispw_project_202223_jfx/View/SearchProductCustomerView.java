package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.SearchProductController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.productNameBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.productNameBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class SearchProductCustomerView {

    private void searchProduct() throws IOException, ParseException {
        String search = "";
        //This one should be obtained from user input
        SearchProductController searchProductController = new SearchProductController();
        productNameBean bean = new productNameBeanClass();
        bean.setName(search);
        searchProductController.SearchProduct(bean);
        HashMap<String, String> nameToBarcode;
        nameToBarcode = bean.getNameToBarcode();
        //Now the results should be displayed
    }
}
