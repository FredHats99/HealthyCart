package com.cappellinispirito.ispw_project_202223_jfx;

import com.cappellinispirito.ispw_project_202223_jfx.scores.FinalScore;
import com.cappellinispirito.ispw_project_202223_jfx.scores.LiquidNutriscore;
import com.cappellinispirito.ispw_project_202223_jfx.scores.Nutriscore;
import com.cappellinispirito.ispw_project_202223_jfx.scores.SolidNutriscore;

public class Item {

    //attributes
    private final float price;
    private final Nutriscore nutriscore;
    private final FinalScore finalScore;
    private final String name;

    //construction method
    public Item(int calories, float sugars, float saturatedFattyAcids, float salt, int otherPercentages, float fibers, float proteins, int B_additives,int C_additives, int D_additives, int E_additives, boolean isBio, boolean isLiquid,  float price, String name){

        if (isLiquid) {
            nutriscore = new LiquidNutriscore(calories, sugars, saturatedFattyAcids, salt, otherPercentages, fibers, proteins);
        }
        else {
            nutriscore = new SolidNutriscore(calories, sugars, saturatedFattyAcids, salt, otherPercentages, fibers, proteins);
        }
        finalScore = new FinalScore(this.nutriscore, B_additives, C_additives,  D_additives,  E_additives, isBio);

        this.price = price;
        this.name = name;

    }

    //getters & setters
    public Nutriscore getNutriscore(){return this.nutriscore;}
    public FinalScore getFinalScore(){return this.finalScore;}
    public float getPrice() {return price;}
    public String getName() {return name;}
}
