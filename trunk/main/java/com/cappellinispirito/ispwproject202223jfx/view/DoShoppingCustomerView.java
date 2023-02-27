package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.DoShoppingController;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.ShopBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.ShopBeanClass;
import com.cappellinispirito.ispwproject202223jfx.view.beans.SupermarketNameBean;

import java.io.IOException;
import java.util.List;

public class DoShoppingCustomerView {

    private List<String> sellableProductName;
    private List<String> sellableProductImage;
    private final DoShoppingController controller = new DoShoppingController();
    ShopBean bean2 = new ShopBeanClass();

    public DoShoppingCustomerView() throws FailedQueryToOpenFoodFacts, IOException {

    }

    public void displayShop() throws IOException {
        controller.setUpShop(bean2);
        sellableProductName = bean2.getSellableProductName();
        sellableProductImage = bean2.getSellableProductImage();
    }

    public List<String> getSellableProductImage() {
        return sellableProductImage;
    }

    public List<String> getSellableProductName() {
        return sellableProductName;
    }

    public void loadNewPage() throws IOException {
        controller.loadNewPage();
        sellableProductName = bean2.getSellableProductName();
        sellableProductImage = bean2.getSellableProductImage();
    }
}
