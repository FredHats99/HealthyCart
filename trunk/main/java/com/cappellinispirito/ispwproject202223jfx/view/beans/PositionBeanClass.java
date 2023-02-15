package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.positionBean;

import java.util.ArrayList;
import java.util.List;

public class PositionBeanClass implements positionBean {
    private String searchAddress;
    private final List<String> supermarketsNames = new ArrayList<>();
    private final List<String> supermarketsAddresses = new ArrayList<>();
    private final List<Float> supermarketsDistances = new ArrayList<>();

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
