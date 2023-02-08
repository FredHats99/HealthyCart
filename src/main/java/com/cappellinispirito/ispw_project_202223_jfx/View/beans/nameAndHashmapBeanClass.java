package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nameAndHashmapBeanInterface;

import java.util.HashMap;

public class nameAndHashmapBeanClass implements nameAndHashmapBeanInterface {
    private String nameToSearch;
    private HashMap map;

    @Override
    public String getNameToSearch() {
        return nameToSearch;
    }

    @Override
    public void setNameToSearch(String newName) {
        this.nameToSearch = newName;
    }

    @Override
    public HashMap getHmNameAndBarcode() {
        return map;
    }

    @Override
    public void setHmNameAndBarcode(HashMap map) {
        this.map = map;
    }
}
