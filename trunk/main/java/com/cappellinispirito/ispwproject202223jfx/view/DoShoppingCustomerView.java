package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.DoShoppingController;
import com.cappellinispirito.ispwproject202223jfx.model.Subject;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.ShopBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.ShopBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DoShoppingCustomerView {

    private List<String> sellableProductName;
    private List<String> sellableProductImage;
    private final DoShoppingController controller = new DoShoppingController();
    ShopBean bean2 = new ShopBeanClass();

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

    public void loadNewPage() throws IOException, FailedQueryToOpenFoodFacts {
        controller.loadNewPage();
        sellableProductName = bean2.getSellableProductName();
        sellableProductImage = bean2.getSellableProductImage();
    }

    public void addItemToCart(int index) throws FailedQueryToOpenFoodFacts, SQLException, IOException, ParseException {
       bean2.setItemToAdd(sellableProductName.get(index));
       controller.addItemToCart(bean2);
    }


    public Subject registerObserver(Observer observer) {
        return controller.registerObserver(observer);
    }

    public void saveCart() throws SQLException {
        controller.saveCart();
    }

    public void removeItemFromCart(int indexOf) {
        bean2.setItemToRemove(indexOf);
        controller.removeItemFromCart(bean2);
    }
}
