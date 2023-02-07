package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.productJSONBean;

import java.util.HashMap;

public class productJSONBeanClass implements productJSONBean {
    private HashMap map;

    @Override
    public HashMap getMap() {
        return map;
    }

    @Override
    public void setMap(HashMap map) {
        this.map = map;
    }
}
