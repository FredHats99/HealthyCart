package com.cappellinispirito.ispwproject202223jfx.model.beansinterface;

import java.util.List;

public interface BarcodeToInformationBean {
    String getBarcodeSearch();
    void setBarcodeSearch(String name);

    String getIngredients();
    void setIngredients(String ingredients);

    Float getCalories();
    void setCalories(Float calories);

    Float getSugars();
    void setSugars(Float sugars);

    Float getFibers();
    void setFibers(Float fibers);

    Float getSaturatedFats();
    void setSaturatedFats(Float saturatedFats);

    Float getSalt();
    void setSalt(Float salt);

    Float getFruitPercentage();
    void setFruitPercentage(Float fruitPercentage);

    Float getProteins();
    void setProteins(Float proteins);

    List<String> getAdditives();
    void setAdditives(List<String> additives);

    Boolean getIsBiological();
    void setIsBiological(Boolean isBiological);

    Boolean getIsBeverage();
    void setIsBeverage(Boolean isBeverage);

    String getName();
    void setName(String name);

    String getImage();
    void setImage(String image);
}
