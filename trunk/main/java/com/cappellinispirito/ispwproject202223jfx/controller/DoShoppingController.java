package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.Subject;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.Item;
import com.cappellinispirito.ispwproject202223jfx.model.ShoppingCart;
import com.cappellinispirito.ispwproject202223jfx.model.Supermarket;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.BarcodeToInformationBean;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.ShopBean;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.SupermarketsToProductsBean;
import com.cappellinispirito.ispwproject202223jfx.model.dao.CartsDAO;
import com.cappellinispirito.ispwproject202223jfx.view.Observer;
import com.cappellinispirito.ispwproject202223jfx.view.beans.SupermarketNameBean;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.SearchProductsFromSupermarketOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.ShowProductInfoOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.beans.BarcodeToInformationBeanClass;
import com.cappellinispirito.ispwproject202223jfx.view.beans.SupermarketsToProductsBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoShoppingController {
    private final ShoppingCart shoppingCart;
    private final String username;
    SearchProductsFromSupermarketOpenFoodFactsAPIBoundary boundary = new SearchProductsFromSupermarketOpenFoodFactsAPIBoundary();
    SupermarketsToProductsBean bean2 = new SupermarketsToProductsBeanClass();
    //By now, the easiest way to pass a lot of values, is via Item classes...this controller class will track them.
    private List<String> sellableSupermarketNames;
    private List<String> sellableSupermarketImages;
    private List<String> sellableSupermarketBarcodes;


    public DoShoppingController(){
        sellableSupermarketNames = new ArrayList<>();
        sellableSupermarketImages = new ArrayList<>();
        sellableSupermarketBarcodes = new ArrayList<>();
        shoppingCart = new ShoppingCart();
        username = LogInController.getInstance().getUserAccountInstance().getUsername();
    }

    public void setUpShop(ShopBean bean) throws IOException {
        Supermarket shopSupermarket = new Supermarket(SupermarketNameBean.getInstance().getSupermarketName());
        bean2.setSupermarket(shopSupermarket);
        boundary.searchProductsBySupermarket(bean2);
        sellableSupermarketNames = bean2.getSellableProductsName();
        sellableSupermarketImages = bean2.getSellableProductsImage();
        sellableSupermarketBarcodes = bean2.getSellableProductsBarcode();
        bean.setSellableProductName(sellableSupermarketNames);
        bean.setSellableProductImage(sellableSupermarketImages);
    }

    public void addItemToCart(ShopBean bean) throws FailedQueryToOpenFoodFacts, IOException, ParseException, SQLException {
        String itemBarcode = sellableSupermarketBarcodes.get(sellableSupermarketNames.indexOf(bean.getItemToAdd()));

        BarcodeToInformationBean localBean = new BarcodeToInformationBeanClass();
        localBean.setBarcodeSearch(itemBarcode);

        ShowProductInfoOpenFoodFactsAPIBoundary localBoundary = ShowProductInfoOpenFoodFactsAPIBoundary.getInstance();
        localBoundary.findProductInfoByBarcode(localBean);

        List<Float> nutritionalValues = new ArrayList<>();
        nutritionalValues.add(localBean.getCalories());
        nutritionalValues.add(localBean.getProteins());
        nutritionalValues.add(localBean.getSugars());
        nutritionalValues.add(localBean.getSaturatedFats());
        nutritionalValues.add(localBean.getFruitPercentage());
        nutritionalValues.add(localBean.getSalt());
        nutritionalValues.add(localBean.getFibers());
        List<String> additives = localBean.getAdditives();
        String ingredients = localBean.getIngredients();
        Boolean isBiological = localBean.getIsBiological();
        Boolean isBeverage = localBean.getIsBeverage();

        List<String> informations = new ArrayList<>();
        informations.add(bean.getItemToAdd());
        informations.add(itemBarcode);
        informations.add(sellableSupermarketImages.get(sellableSupermarketNames.indexOf(bean.getItemToAdd())));
        informations.add(ingredients);
        Item shopItem = new Item(
                informations,
                nutritionalValues,
                additives,
                isBiological,
                isBeverage);
        shoppingCart.addItem(shopItem);
        getCartHealthScore(bean);

        bean.setItemToAdd("");
    }

    public void removeItemFromCart(ShopBean bean){
        shoppingCart.removeItem(shoppingCart.getItemsList().get(bean.getItemToRemove()));
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

    private void getCartHealthScore(ShopBean bean){
        bean.setCartHealthScore(shoppingCart.getAverageScore());
    }

    public void loadNewPage() throws IOException, FailedQueryToOpenFoodFacts {
        boundary.searchProductsBySupermarketLoadNewPage(bean2);
    }

    public void registerObserver(Observer observer) {
        shoppingCart.registerObserver(observer);
    }
}
