package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.doShoppingController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.shopBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.shopBeanClass;

import java.sql.SQLException;

public class DoShoppingCustomerView {
    private final shopBean bean = new shopBeanClass();
    private int indexOfItemToAdd;
    private int indexOfItemToRemove;
    private int cartScore;
    private final doShoppingController controller = new doShoppingController();

    public void addItem() throws SQLException {
        bean.setItemToAdd(indexOfItemToAdd);
        controller.addItemToCart(bean);
        getCartHealthScore();
    }

    public void removeItem(){
        bean.setItemToRemove(indexOfItemToRemove);
        controller.removeItemFromCart(bean);
        getCartHealthScore();
    }

    public void saveCart() throws SQLException {
        controller.saveCart();
    }

    public void getCartHealthScore(){
        cartScore = bean.getCartHealthScore();
    }
}
