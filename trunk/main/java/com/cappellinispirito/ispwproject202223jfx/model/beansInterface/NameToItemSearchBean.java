package com.cappellinispirito.ispwproject202223jfx.model.beansInterface;

import com.cappellinispirito.ispwproject202223jfx.model.Item;


public interface NameToItemSearchBean {
    String getName();
    void setName(String name);

    Item getResultsItem();
    void setResultsItem(Item item);
}
