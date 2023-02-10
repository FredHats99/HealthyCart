package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ShowProductInfoController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.NameToItemSearchBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameToItemSearchBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;

public class ShowProductInfoCustomerView {


    private void ShowProductInfo() throws IOException, ParseException, SQLException {
        String name = "";
        //This one should be obtained from user input, parsed via index
        ShowProductInfoController showProductInfoController = ShowProductInfoController.getInstance();
        NameToItemSearchBean bean = new NameToItemSearchBeanClass();
        bean.setName(name);
        showProductInfoController.findProductInfo(bean);
        Item itemInformation = bean.getResultsItem();

        //display item info
    }
}
