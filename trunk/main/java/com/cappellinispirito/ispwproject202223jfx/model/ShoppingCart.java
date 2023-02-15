package com.cappellinispirito.ispwproject202223jfx.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {


    //attributes
    private final List<Item> itemsList = new ArrayList<>();
    private int averageFinalScore;

    //getters & setters
    public List<Item> getItemsList() {
        return itemsList;
    }

    public int getAverageScore() {
        return averageFinalScore;
    }

    public void addItem(Item newItem){
        itemsList.add(newItem);
        setAverageScore();
    }

    public void removeItem(Item trashItem){
        itemsList.remove(trashItem);
        setAverageScore();
    }

    public void setAverageScore(){
        int tmp=0;
        for (Item item:this.itemsList) {
            tmp+=item.getHealthScore();
        }
        this.averageFinalScore= tmp/itemsList.size();
    }
}
