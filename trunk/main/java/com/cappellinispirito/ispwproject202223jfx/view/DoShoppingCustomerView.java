package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.DoShoppingController;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.shopBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.ShopBeanClass;

public class DoShoppingCustomerView {

    public DoShoppingCustomerView() throws FailedQueryToOpenFoodFacts {
        //Whenever this View starts up, invokes a Controller for obtaining items to put into the cart.
        DoShoppingController controller = new DoShoppingController();
        shopBean bean = new ShopBeanClass();
        controller.setUpShop(bean);
    }
}
