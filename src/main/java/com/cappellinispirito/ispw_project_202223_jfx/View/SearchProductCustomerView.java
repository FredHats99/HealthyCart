package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.SearchProductController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.ResultsFromSearchBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameImageFromSearchBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class SearchProductCustomerView {

    private void searchProduct() throws IOException, ParseException, FailedQueryToOpenFoodFacts {
        String productNameFromUser = "";
        //This one should be obtained from user input
        SearchProductController searchProductControllerInstance = SearchProductController.getInstance();
        ResultsFromSearchBean bean = new NameImageFromSearchBeanClass();
        bean.setNameToSearch(productNameFromUser);
        searchProductControllerInstance.SearchProduct(bean);
        List<String> resultsNames = bean.getResultsNames();
        List<String> resultsImages = bean.getResultsImages();
        //Now the results should be displayed
    }
}
