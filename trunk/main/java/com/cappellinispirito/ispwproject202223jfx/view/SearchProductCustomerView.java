package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.SearchProductController;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.ResultsFromSearchBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NameImageFromSearchBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class SearchProductCustomerView {
    List<String> resultsNames;
    private List<String> resultsImages;



    public void searchProduct(String searchText) throws IOException, ParseException, FailedQueryToOpenFoodFacts {
        //This one should be obtained from user input
        SearchProductController searchProductControllerInstance = SearchProductController.getInstance();
        ResultsFromSearchBean bean = new NameImageFromSearchBeanClass();
        bean.setNameToSearch(searchText);
        searchProductControllerInstance.searchProduct(bean);
        resultsNames = bean.getResultsNames();
        resultsImages = bean.getResultsImages();
        //Now the results should be displayed
    }

    public List<String> getResultsImages() {
        return resultsImages;
    }

    public List<String> getResultsNames(){
        return resultsNames;
    }
}
