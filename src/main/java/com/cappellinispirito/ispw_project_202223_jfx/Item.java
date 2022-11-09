package com.cappellinispirito.ispw_project_202223_jfx;

public class Item {

    //attributes
    private final Nutriscore nutriscore;
    private final FinalScore finalScore;

    //construction method
    public Item(boolean isLiquid, int calories, float sugars, int otherPercentages, float saturatedFattyAcids, float salt, float fibers, float proteins, int B_additives,int C_additives, int D_additives, int E_additives, boolean isBio){

        if (isLiquid) {
            nutriscore = new LiquidNutriscore(calories, sugars, otherPercentages, saturatedFattyAcids, salt, fibers, proteins);

        }
        else {
            nutriscore = new SolidNutriscore(calories, sugars, otherPercentages, saturatedFattyAcids, salt, fibers, proteins);
        }
        finalScore = new FinalScore(this.nutriscore, B_additives, C_additives,  D_additives,  E_additives, isBio);

    }

    //methods
    public Nutriscore getNutriscore(){return this.nutriscore;}
    public FinalScore getFinalScore(){return this.finalScore;}

}
