package com.cappellinispirito.ispw_project_202223_jfx.scores;

public abstract class Nutriscore {

    //attributes
    private int nutriscoreValue;

    private final float caloriesValue;
    private final float sugarsValue;
    private final float saturatedFattyAcidsValue;
    private final float saltValue;


    private final int otherPercentagesValue;
    private final float fibersValue;
    private final float proteinsValue;

    private int positiveScore; //healthier
    private int negativeScore; //unhealthier
    private int proteinsScore5; //need to be here cause generateNutriscore(). This is the only Score-N reliable using this class, other Score-N are local to Solid/Liquid class
    public boolean checkOtherPercentages = false;//used in generateNutriscore() TODO: make it private





    //constructor method
    public Nutriscore(float calories, float sugars, float saturatedFattyAcids, float salt, int otherPercentages, float fibers, float proteins){

        this.caloriesValue=calories;
        this.sugarsValue=sugars;
        this.saturatedFattyAcidsValue=saturatedFattyAcids;
        this.saltValue=salt*400;

        this.otherPercentagesValue=otherPercentages;
        this.fibersValue=fibers;
        this.proteinsValue=proteins;

        this.generateNutriscore();
    }





    //methods
    private void generateNutriscore() {
        generateNegativeScore();
        generatePositiveScore();
        if (getNegativeScore()>=11 && this.checkOtherPercentages)
            setPositiveScore(getPositiveScore()-this.proteinsScore5); //if check==true then positiveScore does not count proteinsScore5
        this.setNutriscoreValue(getNegativeScore() - getPositiveScore());
    }
    public abstract void generateNegativeScore();
    public abstract void generatePositiveScore();





    //getters & setters bad
    public float getCaloriesValue(){return this.caloriesValue;}

    public float getSugarsValue(){return this.sugarsValue;}

    public float getSaturatedFattyAcidsValue() {return saturatedFattyAcidsValue;}

    public float getSaltValue() {return saltValue;}



    //getters & setters good
    public int getOtherPercentagesValue(){return this.otherPercentagesValue;}

    public float getFibersValue() {return fibersValue;}

    public float getProteinsValue() {return proteinsValue;}

    public int getProteinsScore5(){return this.proteinsScore5;}
    public void setProteinsScore5(int a){this.proteinsScore5=a;}



    //getters & setters other
    public int getPositiveScore(){return this.positiveScore;}
    public void setPositiveScore(int a){this.positiveScore= a;}

    public int getNegativeScore(){return this.negativeScore;}
    public void setNegativeScore(int a){this.negativeScore= a;}

    public int getNutriscoreValue(){return this.nutriscoreValue;}
    public void setNutriscoreValue(int a){this.nutriscoreValue = a;}

} //TODO: are inheritance or polymorphism worth?