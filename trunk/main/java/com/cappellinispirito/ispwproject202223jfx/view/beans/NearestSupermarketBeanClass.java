package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.NearestSupermarketBeanInterface;

import java.util.List;


public class NearestSupermarketBeanClass implements NearestSupermarketBeanInterface {
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
