package com.cappellinispirito.ispw_project_202223_jfx;

public abstract class Nutriscore {

    //attributes
    private int nutriscoreValue;
    private final int caloriesValue;
    private final float sugarsValue;
    private final int otherPercentagesValue;
    private int positiveScore; //healthier
    private int negativeScore; //unhealthier

    //constructor method
    public Nutriscore(int calories, float sugars, int otherPercentages){
        this.caloriesValue=calories;
        this.sugarsValue=sugars;
        this.otherPercentagesValue=otherPercentages;
    }

    //methods
    public abstract void generateNutriscore(); //initialize scoreValue
    public abstract void generatePositiveScore(); //used in generateScore
    public abstract void generateNegativeScore(); //used in generateScore
    public int getNutriscoreValue(){return this.nutriscoreValue;}
    public void setNutriscoreValue(int a){this.nutriscoreValue = a;}
    public int getCaloriesValue(){return this.caloriesValue;}
    public float getSugarsValue(){return this.sugarsValue;}
    public int getOtherPercentagesValue(){return this.otherPercentagesValue;}
    public int getPositiveScore(){return this.positiveScore;}
    public void setPositiveScore(int a){this.positiveScore= a;}
    public int getNegativeScore(){return this.negativeScore;}
    public void setNegativeScore(int a){this.negativeScore= a;}

}