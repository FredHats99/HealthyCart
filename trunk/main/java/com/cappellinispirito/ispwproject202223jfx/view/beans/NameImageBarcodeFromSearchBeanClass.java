package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.ResultsFromSearchBean;

import java.util.List;

public class NameImageBarcodeFromSearchBeanClass implements ResultsFromSearchBean {
    private String searchName;
    private List<String> resultsNames;
    private List<String> resultsImages;
    private List<String> resultsBarcodes;

    @Override
    public String getNameToSearch() {
        return searchName;
    }

    @Override
    public void setNameToSearch(String newName) {
        searchName = newName;
    }

    @Override
    public List<String> getResultsNames() {
        return resultsNames;
    }

    @Override
    public void setResultsNames(List<String> list) {
        resultsNames = list;
    }

    @Override
    public List<String> getResultsImages() {
        return resultsImages;
    }

    @Override
    public void setResultsImages(List<String> list) {
        resultsImages = list;
    }

    public List<String> getResultsBarcodes(){
        return resultsBarcodes;
    }

    public void setResultsBarcodes(List<String> list){
        resultsBarcodes = list;
    }
}
