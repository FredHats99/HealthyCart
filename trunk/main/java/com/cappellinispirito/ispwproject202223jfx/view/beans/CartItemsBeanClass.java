package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.CartItemsBean;

import java.util.List;

public class CartItemsBeanClass implements CartItemsBean {
    private Integer cartId;
    private List<String> itemsNamesList;
    private List<String> itemsImagesList;

    @Override
    public Integer getCartId() {
        return cartId;
    }

    @Override
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @Override
    public List<String> getItemsNamesList() {
        return itemsNamesList;
    }

    @Override
    public void setItemsNamesList(List<String> itemsNamesList) {
        this.itemsNamesList = itemsNamesList;
    }

    @Override
    public List<String> getItemsImagesList() {
        return itemsImagesList;
    }

    @Override
    public void setItemsImagesList(List<String> itemsImagesList) {
        this.itemsImagesList = itemsImagesList;
    }


}
