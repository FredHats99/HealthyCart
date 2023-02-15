package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.Item;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.BarcodeToInformationBean;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.NameToItemSearchBean;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.ShowProductInfoOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.beans.BarcodeToInformationBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowProductInfoController{

    private static ShowProductInfoController instance;
    private String barcode;
    private float fruitPercentage;
    private float energy;
    private float sugars;
    private float proteins;
    private float saturatedFats;
    private float fibers;
    private float salt;
    private boolean isBiological;
    private boolean isBeverage;
    private String name;
    private String imageUrl;
    private String ingredients;
    private List<String> additives;

    private Item newItem; // creiamo un item invece di tenere solo le info ricevute perchè l'item ci permette di calcolare dei valori come gli Score.

    private ShowProductInfoController(){}

    public static ShowProductInfoController getInstance() {
        if(instance == null){
            instance = new ShowProductInfoController();
        }
        return instance;
    }

    private void getBarcodeFromName(String name){
        SearchProductController searchProductController = SearchProductController.getInstance();
        this.barcode = searchProductController.getBarcodeByName(name);
        if(this.barcode == null){
            this.barcode = "8013355999501";
        }
    }

    private void getImageUrlFromName(String name){
        SearchProductController searchProductController = SearchProductController.getInstance();
        this.imageUrl = searchProductController.getImageUrlByName(name);
    }

    private void createItem() throws SQLException {
        newItem = new Item(barcode, imageUrl, ingredients, energy, sugars, saturatedFats, salt, fruitPercentage, fibers, proteins, additives, isBiological, isBeverage,0,name); //must implement price
    }

    public void findProductInfo(NameToItemSearchBean bean) throws IOException, ParseException, SQLException, FailedQueryToOpenFoodFacts {
        String nameL = bean.getName();
        this.name = nameL;
        getBarcodeFromName(nameL);
        getImageUrlFromName(nameL);

        BarcodeToInformationBean bean2 = new BarcodeToInformationBeanClass();
        bean2.setBarcodeSearch(this.barcode);
        ShowProductInfoOpenFoodFactsAPIBoundary showProductInfoOpenFoodFactsAPIBoundaryInstance = ShowProductInfoOpenFoodFactsAPIBoundary.getInstance();
        showProductInfoOpenFoodFactsAPIBoundaryInstance.findProductInfoByBarcode(bean2);
        fruitPercentage = bean2.getFruitPercentage();
        energy = bean2.getCalories();
        sugars = bean2.getSugars();
        proteins = bean2.getProteins();
        saturatedFats = bean2.getSaturatedFats();
        fibers = bean2.getFibers();
        salt = bean2.getSalt();
        isBiological = bean2.getIsBiological();
        isBeverage = bean2.getIsBeverage();
        ingredients = bean2.getIngredients();
        additives = bean2.getAdditives();
        createItem();  //questo item viene rimandato alla view, per distinguerlo dal Bean che collega contoller e boundary.
        //Health score logic is inside this Item
        bean.setResultsItem(newItem);
    }
}
