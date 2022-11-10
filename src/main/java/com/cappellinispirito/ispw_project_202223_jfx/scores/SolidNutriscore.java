package com.cappellinispirito.ispw_project_202223_jfx.scores;

public class SolidNutriscore extends Nutriscore {

    //attributes

    //constructor method
    public SolidNutriscore(int calories, float sugars, int otherPercentages, float saturatedFattyAcids, float salt, float fibers, float proteins){
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
        else if (getOtherPercentagesValue()<=60){otherPercentagesScore5=1;}
        else if (getOtherPercentagesValue()<=80){otherPercentagesScore5=2;}
        else {otherPercentagesScore5=5;}
        setPositiveScore(getProteinsScore5() + fibersScore5 + otherPercentagesScore5);
        if (otherPercentagesScore5!=5) this.checkOtherPercentages = !this.checkOtherPercentages;
        System.out.format("proteins %d,fibers %d,others %d\n", getProteinsScore5(), fibersScore5, otherPercentagesScore5);//debugging

    }
    @Override
    public void generateNegativeScore() {
        int energyScore10 = (int)Math.floor((float)this.getCaloriesValue()/335);
        if (energyScore10>10) energyScore10 = 10;
        int sugarScore10 = (int)Math.floor(this.getSugarsValue()/4.5);
        if (sugarScore10>10) sugarScore10 = 10;
        int saturatedFattyAcidsScore10 = (int)Math.floor(this.getSaturatedFattyAcidsValue());
        if (saturatedFattyAcidsScore10>10) saturatedFattyAcidsScore10 = 10;
        int sodiumScore10 = (int)Math.floor(this.getSaltValue()/90);
        if (sodiumScore10>10) sodiumScore10 = 10;
        setNegativeScore(energyScore10+sugarScore10+saturatedFattyAcidsScore10+sodiumScore10);
        System.out.format("energy %d,sugars %d,fattyacids %d, sodium %d\n", energyScore10, sugarScore10, saturatedFattyAcidsScore10, sodiumScore10); //debugging
    }

}
