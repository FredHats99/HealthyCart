package com.cappellinispirito.ispwproject202223jfx.model.beansinterface;
import java.util.List;

public interface ResultsFromSearchBean {

    String getNameToSearch();
    void setNameToSearch(String newName);

    List<String> getResultsNames();
    void setResultsNames(List<String> list);

    List<String> getResultsImages();
    void setResultsImages(List<String> list);
}
