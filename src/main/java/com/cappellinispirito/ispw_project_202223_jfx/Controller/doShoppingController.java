package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.ShoppingCart;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Supermarket;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.shopBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.supermarketsToProductsBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.CartsDAO;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.SearchProductsFromSupermarketBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.supermarketsToProductsBeanClass;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class doShoppingController {
    private ShoppingCart shoppingCart;
    private Supermarket shopSupermarket;
    private String username;
    //By now, the easiest way to pass a lot of values, is via Item classes...this controller class will track them.
    private List<Item> supermarketItems;

    public doShoppingController(){
        shopSupermarket = ShowNearestSupermarketsController.getInstance().getChosenSupermarket();
        supermarketItems = new ArrayList<>();
        shoppingCart = new ShoppingCart();
        username = LogInController.getInstance().getUserAccountInstance().getUsername();
        assert username != null;
        setUpShop();
    }

    public void setUpShop(){
        supermarketsToProductsBean bean = new supermarketsToProductsBeanClass();
        bean.setSupermarket(shopSupermarket);
        SearchProductsFromSupermarketBoundary boundary = new SearchProductsFromSupermarketBoundary();
        boundary.searchProductsBySupermarket(bean);
        supermarketItems = bean.getSoldProducts();
    }

    public void addItemToCart(shopBean bean){
        Item shopItem = supermarketItems.get(bean.getItemToAdd());
        shoppingCart.addItem(shopItem);
    }

    public void removeItemFromCart(shopBean bean){
        Item shopItem = supermarketItems.get(bean.getItemToRemove());
        shoppingCart.removeItem(shopItem);
    }

    public void saveCart() throws SQLException {
        CartsDAO cartsDAO = new CartsDAO();
        cartsDAO.addCart(username, shoppingCart.getAverageScore());
        int i;
        for(i=0; i<shoppingCart.getItemsList().size();i++){
            cartsDAO.addItemToCart(username,shoppingCart.getItemsList().get(i).getBarcode(), 1);
        }
    }

    public void getCartHealthScore(shopBean bean){
        bean.setCartHealthScore(shoppingCart.getAverageScore());
    }
}
