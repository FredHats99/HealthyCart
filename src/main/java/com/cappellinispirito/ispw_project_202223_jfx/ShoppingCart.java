package com.cappellinispirito.ispw_project_202223_jfx;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {


    //attributes
    private final List<Item> itemsList = new ArrayList<Item>();
    private float totalPrice;
    private int averageFinalScore;

    //constructor
    public ShoppingCart(){
    }

    //methods
    public void addItem(Item newItem){itemsList.add(newItem);}
    public void removeItem(Item trashItem){itemsList.remove(trashItem);}

    //getters & setters
    public List<Item> getItemsList() {return itemsList;}

    public float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(){
        float tmpPrice = 0;
        for (Item item : this.itemsList) {
            tmpPrice += item.getPrice();
        }
        this.totalPrice=tmpPrice;
    }

    public int getAverageFinalScore() {
        return averageFinalScore;
    }
    public void setAverageFinalScore(){
        int tmp=0;
        for (Item item:this.itemsList) {
            tmp+=item.getFinalScore().getFinalScoreValue();
        }
        this.averageFinalScore= tmp/itemsList.size();
    }




}
