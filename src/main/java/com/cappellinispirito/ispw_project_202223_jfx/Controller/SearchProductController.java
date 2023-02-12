package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.ResultsFromSearchBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.SearchProductOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameImageBarcodeFromSearchBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SearchProductController{
    //attributes
    private static SearchProductController instance;
    private final HashMap<String, String> nameToBarcodeMap = new HashMap<>();
    private final HashMap<String, String> nameToImageUrlMap = new HashMap<>();


    //methods
    private SearchProductController(){}

    public static SearchProductController getInstance(){ //Singleton
        if (instance == null){
            instance = new SearchProductController();
        }
        return instance;
    }

    public void SearchProduct(ResultsFromSearchBean bean) throws IOException, ParseException, FailedQueryToOpenFoodFacts {
        findProductByName(bean);
        //else error
    }

    public void findProductByName(ResultsFromSearchBean bean) throws IOException, ParseException, FailedQueryToOpenFoodFacts {

        String searchName = bean.getNameToSearch();
        NameImageBarcodeFromSearchBeanClass bean2 = new NameImageBarcodeFromSearchBeanClass();
        bean2.setNameToSearch(searchName);
        SearchProductOpenFoodFactsAPIBoundary apiBoundary = SearchProductOpenFoodFactsAPIBoundary.getInstance();
        apiBoundary.findProductByName(bean2);
        List<String> resultNames = bean2.getResultsNames();
        List<String> resultImages = bean2.getResultsImages();
        List<String> resultBarcodes = bean2.getResultsBarcodes();
        int i;
        for(i=0; i< resultNames.size(); i++){
            nameToBarcodeMap.put(resultNames.get(i), resultBarcodes.get(i));
            System.out.format("<Name, Barcode> is <%s, %s>\n", resultNames.get(i), resultBarcodes.get(i));
            nameToImageUrlMap.put(resultNames.get(i), resultImages.get(i));
        }
        bean.setResultsNames(resultNames);
        bean.setResultsImages(resultImages);
    }

    public String getBarcodeByName(String name) {
        return nameToBarcodeMap.get(name);
    }

    public String getImageUrlByName(String name) {
        return nameToImageUrlMap.get(name);
    }
}
