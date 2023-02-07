package com.cappellinispirito.ispw_project_202223_jfx.Model;

public class Item {

    //attributes
    private String barcode;
    private String name;
    private float price;
    private String imageUrl;
    private String ingredients;
    private int healthScore;
    private float calories;
    private float sugars;
    private float saturatedFats;
    private float salt;
    private float fruitPercentage;
    private float fibers;
    private float proteins;
    private String additives;
    private float isBiological;
    private float isBeverage;




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
                String additives,
                boolean isBiological,
                boolean isBeverage,
                float price,
                String name){

        /*
        private void generateAdditivesScore(int B_additives,int C_additives, int D_additives, int E_additives){
        this.additivesScore = 30-(B_additives*5+C_additives*15+D_additives*25+E_additives*30);
        if (this.additivesScore<0) this.additivesScore=0;
           }*/

        this.healthScore = calculateScore(isBeverage, proteins, fibers, fruitPercentage, calories, sugars, saturatedFats, salt, additives, isBiological);
        //By now this method does not count the additives. A controller class will do it separately.
        this.barcode = barcode;
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;

    }
    //methods
    private int calculateScore(boolean isBeverage, float proteins, float fibers, float fruitPercentage, float calories, float sugars, float saturatedFats, float salt, String additives, boolean isBiological){
        int negativeScore;
        int positiveScore;
        int proteinScore;
        boolean checkFruitPercentage = false;
        int nutriScore; //this is the vanilla score from the research
        int healthScore; // this is newly calculated score

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

            if (fruitPercentageScore!=10) {checkFruitPercentage = !checkFruitPercentage;}

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

            if (fruitPercentageScore!=5) checkFruitPercentage = !checkFruitPercentage;

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
        if (nutriScore<-2) healthScore = 100;
        else if (nutriScore>19) healthScore = 0;
        else healthScore = (int) (0.00000281*Math.pow(nutriScore,7)-0.00017858*Math.pow(nutriScore,6) +0.00402463*Math.pow(nutriScore,5) -0.03479528*Math.pow(nutriScore,4) +0.02908120*Math.pow(nutriScore,3) +0.91405353*Math.pow(nutriScore,2) -7.98065470*nutriScore +81.31242712) * 3/5; //this is a plot function made by us which plots in 60ties.

        if(isBiological){
            healthScore += 10;
        }

        return healthScore;
    }

    //getters
    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getHealthScore(){
        return this.healthScore;
    }

    public float getCalories() {
        return calories;
    }

    public float getSugars() {
        return sugars;
    }

    public float getSaturatedFats() {
        return saturatedFats;
    }

    public float getSalt() {
        return salt;
    }

    public float getFruitPercentage() {
        return fruitPercentage;
    }

    public float getFibers() {
        return fibers;
    }

    public float getProteins() {
        return proteins;
    }

    public String getAdditives() {
        return additives;
    }

    public float getIsBiological() {
        return isBiological;
    }

    public float getIsBeverage() {
        return isBeverage;
    }

    //setters
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setSugars(float sugars) {
        this.sugars = sugars;
    }

    public void setSaturatedFats(float saturatedFats) {
        this.saturatedFats = saturatedFats;
    }

    public void setSalt(float salt) {
        this.salt = salt;
    }

    public void setFruitPercentage(float fruitPercentage) {
        this.fruitPercentage = fruitPercentage;
    }

    public void setFibers(float fibers) {
        this.fibers = fibers;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public void setAdditives(String additives) {
        this.additives = additives;
    }

    public void setIsBiological(float isBiological) {
        this.isBiological = isBiological;
    }

    public void setIsBeverage(float isBeverage) {
        this.isBeverage = isBeverage;
    }
}
