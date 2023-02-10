package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;

import java.util.List;

public interface NameToItemSearchBean {
    String getName();
    void setName(String name);

    Item getResultsItem();
    void setResultsItem(Item item);
}
