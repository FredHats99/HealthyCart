package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.UserAccount;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nameAndHashmapBeanInterface;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.SearchProductOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.nameAndHashmapBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;


public class SearchProductController{
    //attributes
    private static SearchProductController instance;
    private UserAccount account;
    private HashMap<String, String> barcodeMap;


    //methods
    private SearchProductController(){
        //retrieve account... (new one as placeholder)
        account = new UserAccount();
    }

    public static SearchProductController getInstance(){ //Singleton
        if (instance == null){
            instance = new SearchProductController();
        }
        return instance;
    }

    public void SearchProduct(nameAndHashmapBeanInterface bean) throws IOException, ParseException {
        if(checkPremium(this.account)){
            findProductByName(bean);
        }
        //else error
    }

    private boolean checkPremium(UserAccount account){
        return account.getIsPremium();
    }

    public void findProductByName(nameAndHashmapBeanInterface bean) throws IOException, ParseException {

        String searchName = bean.getNameToSearch();
        nameAndHashmapBeanInterface bean2 = new nameAndHashmapBeanClass();
        bean2.setNameToSearch(searchName);
        SearchProductOpenFoodFactsAPIBoundary apiBoundary = new SearchProductOpenFoodFactsAPIBoundary();
        apiBoundary.findProductByName(bean2);
        this.barcodeMap = bean2.getHmNameAndBarcode();
        bean.setHmNameAndBarcode(barcodeMap);
    }

    public String getBarcodeByName(String name) {
        return barcodeMap.get(name);
    }
}
