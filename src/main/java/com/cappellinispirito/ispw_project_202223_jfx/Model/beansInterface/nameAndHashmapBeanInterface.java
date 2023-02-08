package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import java.util.HashMap;

public interface nameAndHashmapBeanInterface {

    String getNameToSearch();
    void setNameToSearch(String newName);

    HashMap<String, String> getHmNameAndBarcode();
    void setHmNameAndBarcode(HashMap<String, String> map);
}
