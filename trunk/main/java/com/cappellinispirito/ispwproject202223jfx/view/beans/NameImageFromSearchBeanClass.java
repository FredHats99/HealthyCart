package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.ResultsFromSearchBean;

import java.util.List;

public class NameImageFromSearchBeanClass implements ResultsFromSearchBean {
    private String nameToSearch;
    private List<String> resultsNames;
    private List<String> resultsImages;

    @Override
    public String getNameToSearch() {
        return nameToSearch;
    }

    @Override
    public void setNameToSearch(String newName) {
        this.nameToSearch = newName;
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


}
