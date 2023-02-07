package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import java.util.HashMap;

public interface productNameBean {

    String getName();
    void setName(String newName);

    HashMap<String, String> getNameToBarcode();
    void setNameToBarcode(HashMap<String, String> map);
}
