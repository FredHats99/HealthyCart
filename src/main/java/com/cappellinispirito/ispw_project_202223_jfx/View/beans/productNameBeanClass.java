package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.productNameBean;

import java.util.HashMap;

public class productNameBeanClass implements productNameBean {
    private String name;
    private HashMap map;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public HashMap getNameToBarcode() {
        return map;
    }

    @Override
    public void setNameToBarcode(HashMap map) {
        this.map = map;
    }
}
