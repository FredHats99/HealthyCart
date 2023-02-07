package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.UserAccount;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.productJSONBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.productNameBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.SearchProductOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.productJSONBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class SearchProductController implements SingletonInstance{
    //attributes
    private SingletonInstance instance;
    private UserAccount account;


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
    }

    private boolean checkPremium(UserAccount account){
        return account.getIsPremium();
    }

    public void findProductByName(productNameBean bean) throws IOException, ParseException {
        String searchName = bean.getName();
        productJSONBean bean2 = new productJSONBeanClass();
        SearchProductOpenFoodFactsAPIBoundary apiBoundary = new SearchProductOpenFoodFactsAPIBoundary();
        apiBoundary.findProductByName(searchName, bean2);
        bean.setNameToBarcode(bean2.getMap());
    }
}
