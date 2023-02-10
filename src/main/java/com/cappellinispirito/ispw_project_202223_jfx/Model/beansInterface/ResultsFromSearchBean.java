package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import java.util.HashMap;
import java.util.List;

public interface ResultsFromSearchBean {

    String getNameToSearch();
    void setNameToSearch(String newName);

    List<String> getResultsNames();
    void setResultsNames(List<String> list);

    List<String> getResultsImages();
    void setResultsImages(List<String> list);
}
