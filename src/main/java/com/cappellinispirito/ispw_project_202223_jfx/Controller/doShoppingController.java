package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.ShoppingCart;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Supermarket;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.BarcodeToInformationBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.NameToItemSearchBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.shopBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.supermarketsToProductsBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.CartsDAO;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.SearchProductsFromSupermarketOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.ShowProductInfoOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.BarcodeToInformationBeanClass;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameToItemSearchBeanClass;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.supermarketsToProductsBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class doShoppingController {
    private ShoppingCart shoppingCart;
    private Supermarket shopSupermarket;
    private String username;
    //By now, the easiest way to pass a lot of values, is via Item classes...this controller class will track them.
    private List<String> sellableSupermarketNames;
    private List<String> sellableSupermarketImages;
    private List<String> sellableSupermarketBarcodes;

    private HashMap<String, String> nameToBarcodeMap = new HashMap<String, String>();
    private HashMap<String, String> nameToImageMap = new HashMap<String, String>();

    public doShoppingController(){
        shopSupermarket = ShowNearestSupermarketsController.getInstance().getChosenSupermarket();
        sellableSupermarketNames = new ArrayList<>();
        sellableSupermarketImages = new ArrayList<>();
        sellableSupermarketBarcodes = new ArrayList<>();
        shoppingCart = new ShoppingCart();
        username = LogInController.getInstance().getUserAccountInstance().getUsername();
    }

    public void setUpShop(shopBean bean) throws FailedQueryToOpenFoodFacts {
        supermarketsToProductsBean bean2 = new supermarketsToProductsBeanClass();
        bean2.setSupermarket(shopSupermarket);
        SearchProductsFromSupermarketOpenFoodFactsAPIBoundary boundary = new SearchProductsFromSupermarketOpenFoodFactsAPIBoundary();
        boundary.searchProductsBySupermarket(bean2);
        sellableSupermarketNames = bean2.getSellableProductsName();
        sellableSupermarketImages = bean2.getSellableProductsImage();
        sellableSupermarketBarcodes = bean2.getSellableProductsBarcode();
        int i;
        for(i=0;i<sellableSupermarketNames.size();i++){
            nameToBarcodeMap.put(sellableSupermarketNames.get(i),sellableSupermarketBarcodes.get(i));
            nameToImageMap.put(sellableSupermarketNames.get(i), sellableSupermarketImages.get(i));
        }
        bean.setSellableProductName(sellableSupermarketNames);
        bean.setSellableProductImage(sellableSupermarketImages);
    }

    public void addItemToCart(shopBean bean) throws FailedQueryToOpenFoodFacts, IOException, ParseException, SQLException {
        String itemBarcode = nameToBarcodeMap.get(bean.getItemToAdd());

        BarcodeToInformationBean bean2 = new BarcodeToInformationBeanClass();
        bean2.setBarcodeSearch(itemBarcode);

        ShowProductInfoOpenFoodFactsAPIBoundary boundary = ShowProductInfoOpenFoodFactsAPIBoundary.getInstance();
        boundary.findProductInfoByBarcode(bean2);

        Float calories = bean2.getCalories();
        Float proteins = bean2.getProteins();
        Float fibers = bean2.getFibers();
        Float salt = bean2.getSalt();
        Float sugars = bean2.getSugars();
        Float fruitPercentage = bean2.getFruitPercentage();
        Float saturatedFats = bean2.getSaturatedFats();
        List<String> additives = bean2.getAdditives();
        String ingredients = bean2.getIngredients();
        Boolean isBiological = bean2.getIsBiological();
        Boolean isBeverage = bean2.getIsBeverage();

        Item shopItem = new Item(itemBarcode,
                nameToImageMap.get(bean.getItemToAdd()),
                ingredients,
                calories,
                sugars,
                saturatedFats,
                salt,
                fruitPercentage,
                fibers,
                proteins,
                additives,
                isBiological,
                isBeverage,
                0,
                bean.getItemToAdd());
        shoppingCart.addItem(shopItem);
        getCartHealthScore(bean);
    }

    public void removeItemFromCart(shopBean bean){
        String itemBarcode = bean.getItemToRemove();
        Item shopItem;
        int i;
        for(i=0;i<shoppingCart.getItemsList().size();i++){
            shopItem = shoppingCart.getItemsList().get(i);
            if(Objects.equals(shopItem.getBarcode(), itemBarcode)){
                shoppingCart.removeItem(shopItem);
            }
        }
        getCartHealthScore(bean);
    }

    public void saveCart() throws SQLException {
        CartsDAO cartsDAO = new CartsDAO();
        cartsDAO.addCart(username, shoppingCart.getAverageScore());
        int i;
        for(i=0; i<shoppingCart.getItemsList().size();i++){
            cartsDAO.addItemToCart(username,shoppingCart.getItemsList().get(i).getBarcode(), 1);
        }
    }

    private void getCartHealthScore(shopBean bean){
        bean.setCartHealthScore(shoppingCart.getAverageScore());
    }
}
