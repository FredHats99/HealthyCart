package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import java.util.List;

public interface CartItemsBean {
    Integer getCartId();
    void setCartId(Integer cartId);

    List<String> getItemsNamesList();
    void setItemsNamesList(List<String> itemsNamesList);

    List<String> getItemsImagesList();
    void setItemsImagesList(List<String> itemsImagesList);
}
