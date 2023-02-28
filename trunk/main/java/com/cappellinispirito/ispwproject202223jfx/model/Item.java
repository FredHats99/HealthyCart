package com.cappellinispirito.ispwproject202223jfx.model;

import com.cappellinispirito.ispwproject202223jfx.model.dao.AdditivesDAO;

import java.sql.SQLException;
import java.util.List;

public class Item {

    //attributes
    private final String barcode;
    private String name;
    private final String imageUrl;
    private final int healthScore;
    private final float calories;
    private final float sugars;
    private final float saturatedFats;
    private final float salt;
    private final float fruitPercentage;
    private final float fibers;
    private final float proteins;
    private final List<String> additives;
    private final boolean isBiological;
    private final boolean isBeverage;
    private final String ingredients;

    private int proteinScore;
    private int fiberScore;
    private int fruitPercentageScore;
    private int energyScore;
    private int sugarScore;
    private int saturatedFatsScore;
    private int sodiumScore;

    //construction method
    public Item(
                List<String> informations,
                List<Float> nutritionalValues,
                List<String> additives,
                boolean isBiological,
                boolean isBeverage
                ) throws SQLException {

        this.barcode = informations.get(1);
        this.name = informations.get(0);
        this.imageUrl = informations.get(2);
        this.ingredients = informations.get(3);
        this.additives = additives;
        this.calories = nutritionalValues.get(0);
        this.proteins = nutritionalValues.get(1);
        this.sugars = nutritionalValues.get(2);
        this.saturatedFats = nutritionalValues.get(3);
        this.fruitPercentage = nutritionalValues.get(4);
        this.salt = nutritionalValues.get(5);
        this.fibers = nutritionalValues.get(6);
        this.isBeverage = isBeverage;
        this.isBiological = isBiological;
        this.healthScore = calculateScore();
    }


    //methods
    private int generateAdditivesScore(List<String> additives) throws SQLException {
        int bAdditives = 0;
        int cAdditives = 0;
        int dAdditives = 0;
        int eAdditives = 0;
        int notFoundAdditives = 0;
        int additivesScore;
        AdditivesDAO additivesDAO = new AdditivesDAO();
        int i;
        if(additives == null){
            return 30;
        }
        for(i=0; i<additives.size(); i++){
            switch (additivesDAO.getAdditiveDangerousness(additives.get(i))) {
                case "B" -> bAdditives++;
                case "C" -> cAdditives++;
                case "D" -> dAdditives++;
                case "E" -> eAdditives++;
                default -> notFoundAdditives++;
            }
        }
        additivesScore = 30-(bAdditives*5+cAdditives*15+dAdditives*25+eAdditives*30);
        if (additivesScore<0) additivesScore=0;
        return additivesScore;
    }



    private int calculateScore() throws SQLException {
        int negativeScore;
        int positiveScore;
        boolean checkFruitPercentage = false;
        int nutriScore; //this is the vanilla score from the research
        int healthScoreL; // this is newly calculated score

        if(this.getCalories()<0 || this.getFibers()<0 || this.getProteins() < 0 || this.getFruitPercentage()<0 || this.getSalt()<0 || this.getSaturatedFats()<0 || this.getSugars()<0){
            healthScoreL = -1; // Not valid for calculation
        } else {
            setProteinsScore();
            setFibersScore();
            setFruitPercentageScore(isBeverage);
            setEnergyScore(isBeverage);
            setSugarScore(isBeverage);
            setSaturatedFatsScore();
            setSodiumScore();

            //calculation for positive score
            positiveScore = this.proteinScore + this.fiberScore + this.fruitPercentageScore;
            negativeScore = this.energyScore + this.sugarScore + this.saturatedFatsScore + this.sodiumScore;

            if(isBeverage){
                if (this.fruitPercentageScore!=10) {
                    checkFruitPercentage = true;
                }
            }
            else {
                if (fruitPercentageScore!=5) checkFruitPercentage = true;
            }

            if (negativeScore>=11 && checkFruitPercentage){
                positiveScore -= this.proteinScore; //if check==true then positiveScore does not count proteinsScore5
            }
            nutriScore = negativeScore - positiveScore;

            //logic for health score
            if (nutriScore<-2) healthScoreL = 100;
            else if (nutriScore>19) healthScoreL = 0;
            else {
                healthScoreL = (int) (0.00000281*Math.pow(nutriScore,7)-0.00017858*Math.pow(nutriScore,6) +0.00402463*Math.pow(nutriScore,5) -0.03479528*Math.pow(nutriScore,4) +0.02908120*Math.pow(nutriScore,3) +0.91405353*Math.pow(nutriScore,2) -7.98065470*nutriScore +81.31242712) * 3/5; //this is a plot function made by us which plots in 60ties.
                //logic for additives
                healthScoreL += generateAdditivesScore(additives);
                //logic for bio
                if(isBiological){
                    healthScoreL += 10;
                }
            }
        }
        return healthScoreL;
    }

    private void setProteinsScore(){
        this.proteinScore = (int)Math.floor(proteins/1.6);
        if (this.proteinScore>5){this.proteinScore = 5;}
    }

    private void setFibersScore(){
        this.fiberScore = (int)Math.floor(fibers/0.9);
        if (this.fiberScore>5) this.fiberScore = 5;
    }

    private void setFruitPercentageScore(boolean isBeverage){
        if(isBeverage){
            if (this.fruitPercentage<=40){this.fruitPercentageScore = 0;}
            else if (this.fruitPercentage<=60){this.fruitPercentageScore=2;}
            else if (this.fruitPercentage<=80){this.fruitPercentageScore=4;}
            else {fruitPercentageScore=10;}
        } else {
            if (this.fruitPercentage<=40){this.fruitPercentageScore = 0;}
            else if (this.fruitPercentage<=60){this.fruitPercentageScore=1;}
            else if (this.fruitPercentage<=80){this.fruitPercentageScore=2;}
            else {fruitPercentageScore=5;}
        }
    }

    private void setEnergyScore(boolean isBeverage){
        if(isBeverage){
            this.energyScore = (int) Math.floor(calories / 30) + 1;
        } else {
            this.energyScore = (int)Math.floor(calories/335);
        }
        if (this.energyScore>10) this.energyScore = 10;
    }

    private void setSugarScore(boolean isBeverage){
        if(isBeverage){
            this.sugarScore = (int) Math.floor(sugars / 1.5);
        } else {
            this.sugarScore = (int)Math.floor(sugars/4.5);
        }
        if (this.sugarScore>10) this.sugarScore = 10;


    }

    private void setSaturatedFatsScore(){
        this.saturatedFatsScore = (int) Math.floor(saturatedFats);
        if (saturatedFatsScore > 10) this.saturatedFatsScore = 10;

    }

    private void setSodiumScore(){
        this.sodiumScore = (int) Math.floor(salt / 90);
        if (this.sodiumScore > 10) this.sodiumScore = 10;
    }
    //getters
    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getHealthScore(){
        return this.healthScore;
    }

    public float getProteins() {
        return proteins;
    }

    public float getFibers() {
        return fibers;
    }

    public float getFruitPercentage() {
        return fruitPercentage;
    }

    public float getSalt() {
        return salt;
    }

    public float getSaturatedFats() {
        return saturatedFats;
    }

    public float getSugars() {
        return sugars;
    }

    public float getCalories() {
        return calories;
    }

    public boolean getIsBiological() {
        return isBiological;
    }

    public List<String> getAdditives() {
        return additives;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }
}
