package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.ShowProductInfoController;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.Item;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.NameToItemSearchBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NameToItemSearchBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;

public class ShowProductInfoCustomerView {


    public Item showProductInfo(String name) throws IOException, ParseException, SQLException {
        //This one should be obtained from user input, parsed via index
        ShowProductInfoController showProductInfoController = ShowProductInfoController.getInstance();
        NameToItemSearchBean bean = new NameToItemSearchBeanClass();
        bean.setName(name);
        showProductInfoController.findProductInfo(bean);
        return bean.getResultsItem();
        //display item info
    }
}
