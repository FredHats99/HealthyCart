package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.BarcodeToInformationBean;

import java.util.List;

public class BarcodeToInformationBeanClass implements BarcodeToInformationBean {
    String barcodeInput;
    //Float priceResult;
    String ingredientsResult;
    Float caloriesResult;
    Float sugarsResult;
    Float saturatedFatsResult;
    Float saltResult;
    Float fibersResult;
    Float fruitPercentageResult;
    Float proteinsResult;
    List<String> additivesResult;
    Boolean isBioResult;
    Boolean isBeverageResult;
    //Optional, unless coming from Consult Old Cart use case
    String name;
    String image;

    @Override
    public String getBarcodeSearch() {
        return barcodeInput;
    }

    @Override
    public void setBarcodeSearch(String barcode) {
        barcodeInput = barcode;
    }

    @Override
    public String getIngredients() {
        return ingredientsResult;
    }

    @Override
    public void setIngredients(String ingredients) {
        ingredientsResult = ingredients;
    }

    @Override
    public Float getCalories() {
        return caloriesResult;
    }

    @Override
    public void setCalories(Float calories) {
        caloriesResult = calories;
    }

    @Override
    public Float getSugars() {
        return sugarsResult;
    }

    @Override
    public void setSugars(Float sugars) {
        sugarsResult = sugars;
    }

    @Override
    public Float getFibers() {
        return fibersResult;
    }

    @Override
    public void setFibers(Float fibers) {
        fibersResult = fibers;
    }

    @Override
    public Float getSaturatedFats() {
        return saturatedFatsResult;
    }

    @Override
    public void setSaturatedFats(Float saturatedFats) {
        saturatedFatsResult = saturatedFats;
    }

    @Override
    public Float getSalt() {
        return saltResult;
    }

    @Override
    public void setSalt(Float salt) {
        saltResult = salt;
    }

    @Override
    public Float getFruitPercentage() {
        return fruitPercentageResult;
    }

    @Override
    public void setFruitPercentage(Float fruitPercentage) {
        fruitPercentageResult = fruitPercentage;
    }

    @Override
    public Float getProteins() {
        return proteinsResult;
    }

    @Override
    public void setProteins(Float proteins) {
        proteinsResult = proteins;
    }

    @Override
    public List<String> getAdditives() {
        return additivesResult;
    }

    @Override
    public void setAdditives(List<String> additives) {
        additivesResult = additives;
    }

    @Override
    public Boolean getIsBiological() {
        return isBioResult;
    }

    @Override
    public void setIsBiological(Boolean isBiological) {
        isBioResult = isBiological;
    }

    @Override
    public Boolean getIsBeverage() {
        return isBeverageResult;
    }

    @Override
    public void setIsBeverage(Boolean isBeverage) {
        isBeverageResult = isBeverage;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImage() {
        return this.image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }
}
