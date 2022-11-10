package com.cappellinispirito.ispw_project_202223_jfx.scores;

public abstract class Nutriscore {

    //attributes
    private int nutriscoreValue;
    private final int caloriesValue;
    private final float sugarsValue;
    private final float saturatedFattyAcidsValue;
    private final float saltValue;
    private final float fibersValue;
    private final float proteinsValue;
    private final int otherPercentagesValue;
    private int positiveScore; //healthier
    private int negativeScore; //unhealthier
    private int proteinsScore5; //need to be global cause generateScore()
    public boolean checkOtherPercentages = false;//used in generateScore()

    //constructor method
    public Nutriscore(int calories, float sugars, int otherPercentages, float saturatedFattyAcids, float salt, float fibers, float proteins){
        this.caloriesValue=calories;
        this.sugarsValue=sugars;
        this.otherPercentagesValue=otherPercentages;
        this.saturatedFattyAcidsValue=saturatedFattyAcids;
        this.saltValue=salt*400;
        this.fibersValue=fibers;
        this.proteinsValue=proteins;
        this.generateNutriscore();
    }

    //methods
    public void generateNutriscore() {
        generateNegativeScore();
        generatePositiveScore();
        if (getNegativeScore()>=11 && this.checkOtherPercentages){setPositiveScore(getPositiveScore()-this.proteinsScore5);}
        this.setNutriscoreValue(getNegativeScore() - getPositiveScore());
    }
    public abstract void generatePositiveScore(); //used in generateScore
    public abstract void generateNegativeScore(); //used in generateScore
    public int getNutriscoreValue(){return this.nutriscoreValue;}
    public void setNutriscoreValue(int a){this.nutriscoreValue = a;}
    public int getCaloriesValue(){
        //System.out.println(this.caloriesValue);
        return this.caloriesValue;}
    public float getSugarsValue(){return this.sugarsValue;}
    public int getOtherPercentagesValue(){return this.otherPercentagesValue;}
    public int getPositiveScore(){return this.positiveScore;}
    public void setPositiveScore(int a){this.positiveScore= a;}
    public int getNegativeScore(){return this.negativeScore;}
    public void setNegativeScore(int a){this.negativeScore= a;}
    public int getProteinsScore5(){return this.proteinsScore5;}
    public void setProteinsScore5(int a){this.proteinsScore5=a;}
    public float getSaturatedFattyAcidsValue() {return saturatedFattyAcidsValue;}
    public float getFibersValue() {return fibersValue;}
    public float getSaltValue() {return saltValue;}
    public float getProteinsValue() {return proteinsValue;}
}