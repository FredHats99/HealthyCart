package com.cappellinispirito.ispw_project_202223_jfx;

public class SolidNutriscore extends Nutriscore {

    //attributes
    private final float saturatedFattyAcidsValue;
    private final float saltValue;
    private final float fibersValue;
    private final float proteinsValue;
    private boolean checkOtherPercentages = false;//used in generateScore()
    private int proteinsScore5; //need to be global cause generateScore()
    private final int caloriesValue = getCaloriesValue();
    private final float sugarsValue = getSugarsValue();
    private final int otherPercentagesValue = getOtherPercentagesValue();

    //constructor method
    public SolidNutriscore(int calories, float sugars, int otherPercentages, float saturatedFattyAcids, float salt, float fibers, float proteins){
        super(calories, sugars, otherPercentages);
        this.saturatedFattyAcidsValue=saturatedFattyAcids;
        this.saltValue=salt*400;
        this.fibersValue=fibers;
        this.proteinsValue=proteins;
        this.generateNutriscore();
    }

    //methods
    @Override
    public void generateNutriscore() {
        generateNegativeScore();
        generatePositiveScore();
        if (getNegativeScore()>=11 && checkOtherPercentages){setPositiveScore(getPositiveScore()-proteinsScore5);}
        this.setNutriscoreValue(getNegativeScore() - getPositiveScore());
    }
    @Override
    public void generatePositiveScore() {
        int otherPercentagesScore5;
        proteinsScore5 = (int)Math.floor(this.proteinsValue/1.6);
        if (proteinsScore5>5) proteinsScore5 = 5;
        int fibersScore5 = (int)Math.floor(this.fibersValue/0.9);
        if (fibersScore5>5) fibersScore5 = 5;
        if (otherPercentagesValue<=40){otherPercentagesScore5 = 0;}
        else if (otherPercentagesValue<=60){otherPercentagesScore5=1;}
        else if (otherPercentagesValue<=80){otherPercentagesScore5=2;}
        else {otherPercentagesScore5=5;}
        setPositiveScore(proteinsScore5 + fibersScore5 + otherPercentagesScore5);
        if (otherPercentagesScore5!=5) checkOtherPercentages = true;
        //System.out.format("proteins %d,fibers %d,others %d\n", proteinsScore5, fibersScore5, otherPercentagesScore5);//debugging

    }
    @Override
    public void generateNegativeScore() {
        int energyScore10 = (int)Math.floor((float)this.caloriesValue/335);
        if (energyScore10>10) energyScore10 = 10;
        int sugarScore10 = (int)Math.floor(this.sugarsValue/4.5);
        if (sugarScore10>10) sugarScore10 = 10;
        int saturatedFattyAcidsScore10 = (int)Math.floor(this.saturatedFattyAcidsValue);
        if (saturatedFattyAcidsScore10>10) saturatedFattyAcidsScore10 = 10;
        int sodiumScore10 = (int)Math.floor(this.saltValue/90);
        if (sodiumScore10>10) sodiumScore10 = 10;
        setNegativeScore(energyScore10+sugarScore10+saturatedFattyAcidsScore10+sodiumScore10);
        //System.out.format("energy %d,sugars %d,fattyacids %d, sodium %d\n", energyScore10, sugarScore10, saturatedFattyAcidsScore10, sodiumScore10); //debugging
    }

}
