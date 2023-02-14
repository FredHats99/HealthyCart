package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nearestSupermarketBeanInterface;

import java.util.List;


public class nearestSupermarketBeanClass implements nearestSupermarketBeanInterface {
    private String search;
    private List<String> supermarketsNamesList;
    private List<String> supermarketsAddressesList;
    private List<Float> supermarketsDistancesList;

    @Override
    public String getSearch() {
        return search;
    }

    @Override
    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public List<String> getSupermarketsNamesList() {
        return supermarketsNamesList;
    }

    @Override
    public void setSupermarketsNamesList(List<String> list) {
        this.supermarketsNamesList = list;
    }

    @Override
    public List<String> getSupermarketsAddressesList() {
        return supermarketsAddressesList;
    }

    @Override
    public void setSupermarketsNamesAddresses(List<String> list) {
        this.supermarketsAddressesList = list;
    }

    @Override
    public List<Float> getSupermarketsNamesDistances() {
        return supermarketsDistancesList;
    }

    @Override
    public void setSupermarketsNamesDistances(List<Float> list) {
        this.supermarketsDistancesList = list;
    }


}
