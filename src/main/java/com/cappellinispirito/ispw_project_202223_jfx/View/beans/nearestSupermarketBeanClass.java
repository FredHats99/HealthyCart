package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nearestSupermarketBean;

import java.util.HashMap;


public class nearestSupermarketBeanClass implements nearestSupermarketBean {
    private String search;
    private HashMap<String, String> map;

    @Override
    public String getSearch() {
        return search;
    }

    @Override
    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public HashMap<String, String> getSupermarketList() {
        return map;
    }


    @Override
    public void setSupermarketList(HashMap<String, String> map) {
        this.map = map;
    }


}
