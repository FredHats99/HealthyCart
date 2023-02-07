package com.cappellinispirito.ispw_project_202223_jfx.Model;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {


    //attributes
    private final List<Item> itemsList = new ArrayList<Item>();
    private float totalPrice;
    private int averageFinalScore;
    private Date date;

    //constructor
    public ShoppingCart(){
    }

    //getters & setters
    public List<Item> getItemsList() {
        return itemsList;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getAverageFinalScore() {
        return averageFinalScore;
    }

    public Date getDate(){
        return this.date;
    }

    public void addItem(Item newItem){
        itemsList.add(newItem);
    }

    public void removeItem(Item trashItem){
        itemsList.remove(trashItem);
    }

    public void setTotalPrice(){
        float tmpPrice = 0;
        for (Item item : this.itemsList) {
            tmpPrice += item.getPrice();
        }
        this.totalPrice=tmpPrice;
    }

    public void setAverageFinalScore(){
        int tmp=0;
        for (Item item:this.itemsList) {
            tmp+=item.getFinalScore().getFinalScoreValue();
        }
        this.averageFinalScore= tmp/itemsList.size();
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
