package com.cappellinispirito.ispwproject202223jfx.model.beansinterface;

import com.cappellinispirito.ispwproject202223jfx.model.Item;


public interface NameToItemSearchBean {
    String getName();
    void setName(String name);

    Item getResultsItem();
    void setResultsItem(Item item);
}
