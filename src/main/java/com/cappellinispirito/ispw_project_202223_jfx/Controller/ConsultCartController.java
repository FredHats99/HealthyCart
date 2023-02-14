package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.BarcodeToInformationBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.CartItemsBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.CartsDAO;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.ShowProductInfoOpenFoodFactsAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.BarcodeToInformationBeanClass;
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
