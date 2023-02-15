package com.cappellinispirito.ispwproject202223jfx.model.beansInterface;

import java.util.List;

public interface nearestSupermarketBeanInterface {

    String getSearch();
    void setSearch(String search);

    List<String> getSupermarketsNamesList();
    void setSupermarketsNamesList(List<String> list);

    List<String> getSupermarketsAddressesList();
    void setSupermarketsNamesAddresses(List<String> list);

    List<Float> getSupermarketsNamesDistances();
    void setSupermarketsNamesDistances(List<Float> list);
}
