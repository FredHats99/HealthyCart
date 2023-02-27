package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.Item;
import com.cappellinispirito.ispwproject202223jfx.model.ShoppingCart;
import com.cappellinispirito.ispwproject202223jfx.model.Supermarket;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.BarcodeToInformationBean;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.ShopBean;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.SupermarketsToProductsBean;
import com.cappellinispirito.ispwproject202223jfx.model.dao.CartsDAO;
import com.cappellinispirito.ispwproject202223jfx.view.beans.SupermarketNameBean;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.SearchProductsFromSupermarketOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.ShowProductInfoOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.beans.BarcodeToInformationBeanClass;
import com.cappellinispirito.ispwproject202223jfx.view.beans.SupermarketsToProductsBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DoShoppingController {
    private final ShoppingCart shoppingCart;
    private Supermarket shopSupermarket;
    private final String username;
    SearchProductsFromSupermarketOpenFoodFactsAPIBoundary boundary = new SearchProductsFromSupermarketOpenFoodFactsAPIBoundary();
    SupermarketsToProductsBean bean2 = new SupermarketsToProductsBeanClass();
    //By now, the easiest way to pass a lot of values, is via Item classes...this controller class will track them.
    private List<String> sellableSupermarketNames;
    private List<String> sellableSupermarketImages;
    private List<String> sellableSupermarketBarcodes;

    private final HashMap<String, String> nameToBarcodeMap = new HashMap<>();
    private final HashMap<String, String> nameToImageMap = new HashMap<>();

    public DoShoppingController(){
        sellableSupermarketNames = new ArrayList<>();
        sellableSupermarketImages = new ArrayList<>();
        sellableSupermarketBarcodes = new ArrayList<>();
        shoppingCart = new ShoppingCart();
        username = LogInController.getInstance().getUserAccountInstance().getUsername();
    }

    public void setUpShop(ShopBean bean) throws IOException {
        shopSupermarket = new Supermarket(SupermarketNameBean.getInstance().getSupermarketName());
        bean2.setSupermarket(shopSupermarket);
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

    public void addItemToCart(ShopBean bean) throws FailedQueryToOpenFoodFacts, IOException, ParseException, SQLException {
        String itemBarcode = nameToBarcodeMap.get(bean.getItemToAdd());

        BarcodeToInformationBean bean2 = new BarcodeToInformationBeanClass();
        bean2.setBarcodeSearch(itemBarcode);

        ShowProductInfoOpenFoodFactsAPIBoundary boundary = ShowProductInfoOpenFoodFactsAPIBoundary.getInstance();
        boundary.findProductInfoByBarcode(bean2);

        List<Float> nutritionalValues = new ArrayList<>();
        nutritionalValues.add(bean2.getCalories());
        nutritionalValues.add(bean2.getProteins());
        nutritionalValues.add(bean2.getSugars());
        nutritionalValues.add(bean2.getSaturatedFats());
        nutritionalValues.add(bean2.getFruitPercentage());
        nutritionalValues.add(bean2.getSalt());
        nutritionalValues.add(bean2.getFibers());
        List<String> additives = bean2.getAdditives();
        String ingredients = bean2.getIngredients();
        Boolean isBiological = bean2.getIsBiological();
        Boolean isBeverage = bean2.getIsBeverage();

        List<String> informations = new ArrayList<>();
        informations.add(bean.getItemToAdd());
        informations.add(itemBarcode);
        informations.add(nameToImageMap.get(bean.getItemToAdd()));
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
        System.out.println(shoppingCart.getItemsList());
    }

    public void removeItemFromCart(ShopBean bean){
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

    private void getCartHealthScore(ShopBean bean){
        bean.setCartHealthScore(shoppingCart.getAverageScore());
    }

    public void loadNewPage() throws IOException {
        boundary.searchProductsBySupermarketLoadNewPage(bean2);
    }
}
