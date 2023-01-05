package com.cappellinispirito.ispw_project_202223_jfx.scores;

import java.util.List;

public class FinalScore {

    //attributes
    private double nutriscore100;
    private int nutriscore60;
    private int finalScoreValue;
    private int additivesScore;
    private int B_additives;
    private int C_additives;
    private int D_additives;
    private int E_additives;

    //constructor method
    public FinalScore(Nutriscore nutriscore, List<String> additivesList, boolean isBio){
        assignLetterToAdditives(additivesList);
        generateNutriscoreTo60(nutriscore, E_additives);
        generateAdditivesScore(B_additives, C_additives,  D_additives,  E_additives);
        generateFinalScore();
        if (isBio) this.finalScoreValue+=10;
    }

    private void assignLetterToAdditives(List<String> additivesList) {
    }

    //methods
    private void generateNutriscoreTo100(Nutriscore item){
        if (item.getNutriscoreValue()<-2) this.nutriscore100 =100;
        else if (item.getNutriscoreValue()>19) this.nutriscore100 =0;
        else this.nutriscore100 =0.00000281*Math.pow(item.getNutriscoreValue(),7) -0.00017858*Math.pow(item.getNutriscoreValue(),6) +0.00402463*Math.pow(item.getNutriscoreValue(),5) -0.03479528*Math.pow(item.getNutriscoreValue(),4) +0.02908120*Math.pow(item.getNutriscoreValue(),3) +0.91405353*Math.pow(item.getNutriscoreValue(),2) -7.98065470* item.getNutriscoreValue() +81.31242712;
    }
    private void generateNutriscoreTo60(Nutriscore item, int E_additives){
        generateNutriscoreTo100(item);
        this.nutriscore60 = (int)(this.nutriscore100 *3)/5 ;
        if (E_additives!=0 && this.nutriscore60 >49) this.nutriscore60 =49;
    }
    private void generateAdditivesScore(int B_additives,int C_additives, int D_additives, int E_additives){
        this.additivesScore = 30-(B_additives*5+C_additives*15+D_additives*25+E_additives*30);
        if (this.additivesScore<0) this.additivesScore=0;
    }
    private void generateFinalScore(){
        this.finalScoreValue = this.nutriscore60 + this.additivesScore;
    }


    //getters & setters
    public int getFinalScoreValue(){
        return this.finalScoreValue;
    }

}
