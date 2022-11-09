package com.cappellinispirito.ispw_project_202223_jfx;

public class LiquidNutriscore extends Nutriscore{
    /*attributes
    private final int caloriesValue = getCaloriesValue();
    private final float sugarsValue = getSugarsValue();
    private final int otherPercentagesValue = getOtherPercentagesValue();*/

    //constructor method
    public LiquidNutriscore(int calories, float sugars, int otherPercentages, float saturatedFattyAcids, float salt, float fibers, float proteins) {
        super(calories, sugars, otherPercentages, saturatedFattyAcids, salt, fibers, proteins);
    }

    //methods

    @Override
    public void generatePositiveScore() {
        int otherPercentagesScore5;
        setProteinsScore5((int)Math.floor(this.getProteinsValue()/1.6));
        if (getProteinsScore5()>5) setProteinsScore5(5);
        int fibersScore5 = (int)Math.floor(this.getFibersValue()/0.9);
        if (fibersScore5>5) fibersScore5 = 5;
        if (getOtherPercentagesValue()<=40){otherPercentagesScore5 = 0;}
        else if (getOtherPercentagesValue()<=60){otherPercentagesScore5=2;}
        else if (getOtherPercentagesValue()<=80){otherPercentagesScore5=4;}
        else {otherPercentagesScore5=10;}
        setPositiveScore(getProteinsScore5() + fibersScore5 + otherPercentagesScore5);
        if (otherPercentagesScore5!=10) this.checkOtherPercentages = !this.checkOtherPercentages;
        System.out.format("proteins %d,fibers %d,others %d\n", getProteinsScore5(), fibersScore5, otherPercentagesScore5);//debugging
    }
    @Override
    public void generateNegativeScore() {
        int energyScore10 = (int)Math.floor((float)this.getCaloriesValue()/30) +1;
        if (energyScore10>10) energyScore10 = 10;
        int sugarScore10 = (int)Math.floor(this.getSugarsValue()/1.5)+1;
        if (sugarScore10>10) sugarScore10 = 10;
        int saturatedFattyAcidsScore10 = (int)Math.floor(this.getSaturatedFattyAcidsValue());
        if (saturatedFattyAcidsScore10>10) saturatedFattyAcidsScore10 = 10;
        int sodiumScore10 = (int)Math.floor(this.getSaltValue()/90);
        if (sodiumScore10>10) sodiumScore10 = 10;
        setNegativeScore(energyScore10+sugarScore10+saturatedFattyAcidsScore10+sodiumScore10);
        System.out.format("energy %d,sugars %d,fattyacids %d, sodium %d\n", energyScore10, sugarScore10, saturatedFattyAcidsScore10, sodiumScore10); //debugging
    }

}
