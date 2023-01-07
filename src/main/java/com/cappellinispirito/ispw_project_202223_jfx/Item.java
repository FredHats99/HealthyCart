package com.cappellinispirito.ispw_project_202223_jfx;

import com.cappellinispirito.ispw_project_202223_jfx.scores.FinalScore;
import com.cappellinispirito.ispw_project_202223_jfx.scores.LiquidNutriscore;
import com.cappellinispirito.ispw_project_202223_jfx.scores.Nutriscore;
import com.cappellinispirito.ispw_project_202223_jfx.scores.SolidNutriscore;

import java.util.List;

public class Item {

    //attributes
    private final float price;
    private final Nutriscore nutriscore;
    private final FinalScore finalScore;
    private String name;
    private String imageUrl;
    private String ingredients;

    //construction method
    public Item(
                //basics for nutriscore
                float calories,
                float sugars,
                float saturatedFattyAcids,
                float salt,
                int otherPercentages,
                float fibers,
                float proteins,
                //additives
                List<String> additivesList,
                //misc
                boolean isBio,
                boolean isLiquid,
                float price,
                String name){

        if (isLiquid) {
            nutriscore = new LiquidNutriscore(calories, sugars, saturatedFattyAcids, salt, otherPercentages, fibers, proteins);
        }
        else {
            nutriscore = new SolidNutriscore(calories, sugars, saturatedFattyAcids, salt, otherPercentages, fibers, proteins);
        }
        finalScore = new FinalScore(this.nutriscore,additivesList, isBio);

        this.price = price;
        this.name = name;

    }

    //getters & setters
    public Nutriscore getNutriscore(){return this.nutriscore;}
    public FinalScore getFinalScore(){return this.finalScore;}
    public float getPrice() {return price;}
    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
