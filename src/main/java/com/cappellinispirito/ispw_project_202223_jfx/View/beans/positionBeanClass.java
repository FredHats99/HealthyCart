package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.positionBean;

import java.util.List;

public class positionBeanClass implements positionBean {
    private String searchAddress;
    private List<String> supermarketsNames;
    private List<String> supermarketsAddresses;
    private List<Float> supermarketsDistances;

    @Override
    public String getAddress() {
        return searchAddress;
    }

    @Override
    public void setAddress(String address) {
        searchAddress = address;
    }

    @Override
    public List<String> getSupermarketsNames() {
        return supermarketsNames;
    }

    @Override
    public void appendToSupermarketsNames(String name) {
        supermarketsNames.add(name);
    }


    @Override
    public List<String> getSupermarketsAddress() {
        return supermarketsAddresses;
    }

    @Override
    public void appendToSupermarketsAddress(String address) {
        supermarketsAddresses.add(address);
    }


    @Override
    public List<Float> getSupermarketsDistance() {
        return supermarketsDistances;
    }

    @Override
    public void appendToSupermarketsDistance(float distance) {
        supermarketsDistances.add(distance);
    }


}
