package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.BarcodeToInformationBean;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.CartItemsBean;
import com.cappellinispirito.ispwproject202223jfx.model.dao.CartsDAO;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.ShowProductInfoOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.beans.BarcodeToInformationBeanClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ConsultCartController {
    private final String username = LogInController.getInstance().getUserAccountInstance().getUsername();
    private final List<String> cartItemsName = new ArrayList<>();
    private final List<String> cartItemsImage = new ArrayList<>();


    public void getItemInfosFromId(CartItemsBean bean) throws SQLException, FailedQueryToOpenFoodFacts, IOException, ParseException {
        int cartId = bean.getCartId();
        CartsDAO cartsDAO = new CartsDAO();
        List<String> cartBarcodes = cartsDAO.getOldCartItems(username, cartId);
        BarcodeToInformationBean bean2 = new BarcodeToInformationBeanClass();
        ShowProductInfoOpenFoodFactsAPIBoundary boundary = ShowProductInfoOpenFoodFactsAPIBoundary.getInstance();
        int i;
        for(i=0; i< cartBarcodes.size(); i++){
            bean2.setBarcodeSearch(cartBarcodes.get(i));
            boundary.findProductInfoByBarcode(bean2);
            cartItemsName.add(bean2.getName());
            cartItemsImage.add(bean2.getImage());
        }
        bean.setItemsNamesList(cartItemsName);
        bean.setItemsImagesList(cartItemsImage);
    }
}
