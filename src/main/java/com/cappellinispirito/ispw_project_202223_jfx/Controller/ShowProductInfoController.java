package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nameBarcodeAndItemInfoBeanInterface;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.ShowProductInfoOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameBarcodeAndItemInfoBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowProductInfoController{

    private static ShowProductInfoController instance;
    private String barcode;
    private float FruitPercentage;
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
        this.barcode = searchProductController.getBarcodeByName(name); //non è detto che abbia sempre un hashmap pronta
    }

    private void createItem() throws SQLException {
        newItem = new Item(barcode, imageUrl, ingredients, energy, sugars, saturatedFats, salt, FruitPercentage, fibers, proteins, additives, isBiological, isBeverage,0,name); //must implement price
    }

    public void findProductInfo(nameBarcodeAndItemInfoBeanInterface bean) throws IOException, ParseException, SQLException {
        String name = bean.getName();
        getBarcodeFromName(name);
        nameBarcodeAndItemInfoBeanInterface bean2 = new NameBarcodeAndItemInfoBeanClass();
        bean2.setBarcode(this.barcode);
        ShowProductInfoOpenFoodFactsAPIBoundary showProductInfoOpenFoodFactsAPIBoundaryInstance = ShowProductInfoOpenFoodFactsAPIBoundary.getInstance();
        showProductInfoOpenFoodFactsAPIBoundaryInstance.findProductInfoByBarcode(bean2);
        FruitPercentage = bean2.getFruitPercentage();
        energy = bean2.getEnergy();
        sugars = bean2.getSugars();
        proteins = bean2.getProteins();
        saturatedFats = bean2.getSaturatedFats();
        fibers = bean2.getFibers();
        salt = bean2.getSalt();
        isBiological = bean2.getIsBio();
        isBeverage = bean2.getIsBeverage();
        this.name = name;
        imageUrl = bean2.getImageUrl();
        ingredients = bean2.getIngredients();
        additives = bean2.getAdditives();
        createItem();  //questo item non viene rimandato alla view, e nemmeno le sue informazioni che andrebbero visualizzate
        bean.setScore(newItem.getHealthScore());
        bean.setBarcode(this.barcode);
        bean.setName(this.name);
        bean.setImageUrl(this.imageUrl);
        bean.setIngredients(this.ingredients);
        bean.setEnergy(this.energy);
        bean.setSugars(this.sugars);
        bean.setSaturatedFats(this.saturatedFats);
        bean.setSalt(this.salt);
        bean.setFruitPercentage(this.FruitPercentage);
        bean.setFibers(this.fibers);
        bean.setProtein(this.proteins);
        bean.setAdditives(this.additives);
        bean.setIsBeverage(this.isBeverage);
        bean.setIsBio(this.isBiological);
        //can't pass price
        //maybe some are redundant
        //UPDATE: all those values must be passed to bean for the view to get them, not bean2!
    }
}
