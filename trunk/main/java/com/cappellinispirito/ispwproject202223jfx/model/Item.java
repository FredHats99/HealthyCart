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




    //construction method
    public Item(
                String barcode,
                String imageUrl,
                String ingredients,
                float calories,
                float sugars,
                float saturatedFats,
                float salt,
                float fruitPercentage,
                float fibers,
                float proteins,
                List<String> additives,
                boolean isBiological,
                boolean isBeverage,
                float price,
                String name) throws SQLException {

        this.barcode = barcode;
        this.name = name;
        this.imageUrl = imageUrl;
        this.additives = additives;
        this.calories = calories;
        this.proteins = proteins;
        this.sugars = sugars;
        this.saturatedFats = saturatedFats;
        this.fruitPercentage = fruitPercentage;
        this.salt = salt;
        this.fibers = fibers;
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
            }
        }
        additivesScore = 30-(bAdditives*5+cAdditives*15+dAdditives*25+eAdditives*30);
        if (additivesScore<0) additivesScore=0;
        return additivesScore;
    }



    private int calculateScore() throws SQLException {
        int negativeScore;
        int positiveScore;
        int proteinScore;
        boolean checkFruitPercentage = false;
        int nutriScore; //this is the vanilla score from the research
        int healthScoreL; // this is newly calculated score

        if(isBeverage){
            //calculation for positive score
            proteinScore = (int)Math.floor(proteins/1.6);
            if (proteinScore>5){proteinScore = 5;}

            int fibersScore = (int)Math.floor(fibers/0.9);
            if (fibersScore>5) fibersScore = 5;

            int fruitPercentageScore;
            if (fruitPercentage<=40){fruitPercentageScore = 0;}
            else if (fruitPercentage<=60){fruitPercentageScore=2;}
            else if (fruitPercentage<=80){fruitPercentageScore=4;}
            else {fruitPercentageScore=10;}

            positiveScore = proteinScore + fibersScore + fruitPercentageScore;

            if (fruitPercentageScore!=10) {
                checkFruitPercentage = true;
            }

            //calculation for negative score
            int energyScore = (int) Math.floor(calories / 30) + 1;
            if (energyScore > 10) energyScore = 10;

            int sugarScore = (int) Math.floor(sugars / 1.5);
            if (sugarScore > 10) sugarScore = 10;

            int saturatedFatsScore = (int) Math.floor(saturatedFats);
            if (saturatedFatsScore > 10) saturatedFatsScore = 10;

            int sodiumScore = (int) Math.floor(salt / 90);
            if (sodiumScore > 10) sodiumScore = 10;

            negativeScore = energyScore + sugarScore + saturatedFatsScore + sodiumScore;

        }
        else {
            //calculation for positive score
            proteinScore = (int)Math.floor(proteins/1.6);
            if (proteinScore>5) {
                proteinScore = 5;
            }

            int fibersScore = (int)Math.floor(fibers/0.9);
            if (fibersScore>5) fibersScore = 5;

            int fruitPercentageScore;
            if (fruitPercentage<=40){fruitPercentageScore = 0;}
            else if (fruitPercentage<=60){fruitPercentageScore=1;}
            else if (fruitPercentage<=80){fruitPercentageScore=2;}
            else {fruitPercentageScore=5;}

            positiveScore = proteinScore + fibersScore + fruitPercentageScore;

            if (fruitPercentageScore!=5) checkFruitPercentage = true;

            //calculation for negative score
            int energyScore = (int)Math.floor(calories/335);
            if (energyScore>10) energyScore = 10;

            int sugarsScore = (int)Math.floor(sugars/4.5);
            if (sugarsScore>10) sugarsScore = 10;

            int saturatedFatsScore = (int)Math.floor(saturatedFats);
            if (saturatedFatsScore>10) saturatedFatsScore = 10;

            int sodiumScore = (int)Math.floor(salt/90);
            if (sodiumScore>10) sodiumScore = 10;

            negativeScore = energyScore+sugarsScore+saturatedFatsScore+sodiumScore;
        }

        if (negativeScore>=11 && checkFruitPercentage){
            positiveScore -= proteinScore; //if check==true then positiveScore does not count proteinsScore5
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

        return healthScoreL;
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

    public void setName(String name) {
        this.name = name;
    }

}
