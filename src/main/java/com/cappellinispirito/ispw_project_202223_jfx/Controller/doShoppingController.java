package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.ShoppingCart;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Supermarket;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.shopBean;

import java.sql.SQLException;

public class doShoppingController {
    private ShoppingCart shoppingCart;
    private Supermarket shopSupermarket;

    public boolean addItemToCart(shopBean bean) throws SQLException {
        //TODO: WHENEVER I ADD AN ITEM, I HAVE TO KNOW ALL OF ITS INFORMATION, SO THEY MUST HAVE BEEN CALCULATED BEFORE.
        return true;
    }

    public void removeItemFromCart(shopBean bean){}

    public void saveCart(){}

    public void getCartHealthScore(shopBean bean){}
}
