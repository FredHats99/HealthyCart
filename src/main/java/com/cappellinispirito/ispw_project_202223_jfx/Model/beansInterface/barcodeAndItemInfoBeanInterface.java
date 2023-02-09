package com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface;

public interface barcodeAndItemInfoBeanInterface {
    String getBarcode();
    void setBarcode(String barcode);

    float getFruitPercentage();
    void setFruitPercentage(int fruitPercentage);

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

    String getName();
    void setName(String name);

    String getImageUrl();
    void setImageUrl(String imageUrl);

    String getIngredients();
    void setIngredients(String ingredients);

    String getAdditives();
    void setAdditives(String additives);
}
