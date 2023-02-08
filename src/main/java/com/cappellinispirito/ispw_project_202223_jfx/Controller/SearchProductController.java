package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.UserAccount;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.productJSONBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.productNameBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.SearchProductOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.productJSONBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;


public class SearchProductController implements SingletonInstance{
    //attributes
    private SingletonInstance instance;
    private UserAccount account;
    private HashMap<String, String> barcodeMap;


    //methods
    public SearchProductController(){
        //from db, retrieve info about account...
        account = new UserAccount();
    }

    @Override
    public SingletonInstance getInstance() {
        if(instance==null){
            instance = new SearchProductController();
        }
        return instance;
    }
    public void SearchProduct(productNameBean bean) throws IOException, ParseException {
        if(checkPremium(this.account)){
            findProductByName(bean);
        }
        //else error
    }

    private boolean checkPremium(UserAccount account){
        return account.getIsPremium();
    }

    public void findProductByName(productNameBean bean) throws IOException, ParseException {
        String searchName = bean.getName();
        productJSONBean bean2 = new productJSONBeanClass();
        bean2.setName(searchName);
        SearchProductOpenFoodFactsAPIBoundary apiBoundary = new SearchProductOpenFoodFactsAPIBoundary();
        apiBoundary.findProductByName(bean2);
        this.barcodeMap = bean2.getMap();
        bean.setNameToBarcode(barcodeMap);
    }

    public String getBarcodeByIndex(String name) {
        return barcodeMap.get(name);
    }
}
