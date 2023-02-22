package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.DoShoppingController;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.ShopBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.ShopBeanClass;

import java.io.IOException;

public class DoShoppingCustomerView {

    public DoShoppingCustomerView() throws FailedQueryToOpenFoodFacts, IOException {
        //Whenever this View starts up, invokes a Controller for obtaining items to put into the cart.
        DoShoppingController controller = new DoShoppingController();
        ShopBean bean = new ShopBeanClass();
        controller.setUpShop(bean);
    }
}
