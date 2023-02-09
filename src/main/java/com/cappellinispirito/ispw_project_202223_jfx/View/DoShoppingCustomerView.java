package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.doShoppingController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.shopBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.shopBeanClass;

import java.sql.SQLException;

public class DoShoppingCustomerView {
    private shopBean bean = new shopBeanClass();
    private String itemToAdd; //obviously not a string...
    private doShoppingController controller = new doShoppingController();

    public void addItem() throws SQLException {
        bean.setItemToAdd(itemToAdd);
        //The function below will try to add the item and return exactly if it has correctly added the item
        if(controller.addItemToCart(bean)){
            //Item has been added. Hooray!
        } else {
            //Oh-oh. Something went wrong!
        }

    }

    public void removeItem(){}

    public void saveCart(){}

    public void getCartHealthScore(){}
}
