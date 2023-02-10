package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.CartItemsBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConsultCartController {
    private HashMap<Integer, String> cartIdToBarcodemap = new HashMap<Integer, String>();


    public void getItemInfosFromId(CartItemsBean bean) {
        int cartId = bean.getCartId();
        List<String> barcodeList = new ArrayList<>();
        barcodeList.add(cartIdToBarcodemap.get(cartId));
    }
}
