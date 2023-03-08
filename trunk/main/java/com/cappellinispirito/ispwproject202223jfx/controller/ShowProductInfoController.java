package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.Item;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.BarcodeToInformationBean;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.NameToItemSearchBean;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.ShowProductInfoOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.beans.BarcodeToInformationBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        List<Float> nutritionalValues = new ArrayList<>();
        List<String> itemInformation = new ArrayList<>();
        itemInformation.add(name);
        itemInformation.add(barcode);
        itemInformation.add(imageUrl);
        itemInformation.add(ingredients);
        nutritionalValues.add(energy);
        nutritionalValues.add(proteins);
        nutritionalValues.add(sugars);
        nutritionalValues.add(saturatedFats);
        nutritionalValues.add(fruitPercentage);
        nutritionalValues.add(salt);
        nutritionalValues.add(fibers);
        newItem = new Item(itemInformation, nutritionalValues, additives, isBiological, isBeverage);
    }

    public void findProductInfo(NameToItemSearchBean bean) throws IOException, ParseException, SQLException {
        String nameL = bean.getName();
        this.name = nameL;
        getBarcodeFromName(nameL);
        getImageUrlFromName(nameL);

        BarcodeToInformationBean bean2 = new BarcodeToInformationBeanClass();
        bean2.setBarcodeSearch(this.barcode);
        ShowProductInfoOpenFoodFactsAPIBoundary showProductInfoOpenFoodFactsAPIBoundaryInstance = ShowProductInfoOpenFoodFactsAPIBoundary.getInstance();
        showProductInfoOpenFoodFactsAPIBoundaryInstance.findProductInfoByBarcode(bean2);
        try{
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
        } catch (NullPointerException e){
            Logger logger = Logger.getLogger(ShowProductInfoController.class.getName());
            logger.log(Level.INFO, "Some value is not provided from OpenFoodFactsAPI. Thus, no information can be displayed.");
            fruitPercentage = -1;
            energy = -1;
            sugars = -1;
            proteins = -1;
            saturatedFats = -1;
            fibers = -1;
            salt = -1;
            isBiological = false;
            isBeverage = false;
            ingredients = "";
            additives = new ArrayList<>();
        }

        createItem();  //questo item viene rimandato alla view, per distinguerlo dal Bean che collega contoller e boundary.
        //Health score logic is inside this Item
        bean.setResultsItem(newItem);
    }
}
