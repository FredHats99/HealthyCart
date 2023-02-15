package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.Item;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.NameToItemSearchBean;

public class NameToItemSearchBeanClass implements NameToItemSearchBean {
    private String name;
    private Item itemResult;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Item getResultsItem() {
        return itemResult;
    }

    @Override
    public void setResultsItem(Item item) {
        itemResult = item;
    }


}
