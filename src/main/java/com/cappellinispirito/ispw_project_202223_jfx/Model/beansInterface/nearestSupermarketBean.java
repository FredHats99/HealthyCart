package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import java.util.HashMap;
import java.util.List;

public interface nearestSupermarketBean {
    String getSearch();
    void setSearch(String search);

    HashMap<String, String> getSupermarketList();
    void setSupermarketList(HashMap<String, String> map);
}
