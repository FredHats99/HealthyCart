package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.NameToItemSearchBean;

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
