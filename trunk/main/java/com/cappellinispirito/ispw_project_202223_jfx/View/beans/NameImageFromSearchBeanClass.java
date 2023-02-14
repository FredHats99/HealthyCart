package com.cappellinispirito.ispw_project_202223_jfx.View.beans;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.ResultsFromSearchBean;

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
