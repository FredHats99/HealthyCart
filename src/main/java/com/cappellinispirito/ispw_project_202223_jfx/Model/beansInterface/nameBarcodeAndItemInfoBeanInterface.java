package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

import java.util.List;

public interface nameBarcodeAndItemInfoBeanInterface {
    String getName();
    void setName(String name);

    String getBarcode();
    void setBarcode(String barcode);

    int getPrice();
    void setPrice(int price);

    int getScore();
    void setScore(int score);

    float getFruitPercentage();
    void setFruitPercentage(float fruitPercentage);

    float getEnergy();
    void setEnergy(float energy);

    float getSugars();
    void setSugars(float sugars);

    float getProteins();
    void setProtein(float protein);

    float getSaturatedFats();
    void setSaturatedFats(float saturated_fat);

    float getFibers();
    void setFibers(float fiber);

    float getSalt();
    void setSalt(float salt);

    boolean getIsBio();
    void setIsBio(boolean isBio);

    boolean getIsBeverage();
    void setIsBeverage(boolean isBeverage);

    String getImageUrl();
    void setImageUrl(String imageUrl);

    String getIngredients();
    void setIngredients(String ingredients);

    List<String> getAdditives();
    void setAdditives(List<String> additives);
}
