package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.ResultsFromSearchBean;

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
