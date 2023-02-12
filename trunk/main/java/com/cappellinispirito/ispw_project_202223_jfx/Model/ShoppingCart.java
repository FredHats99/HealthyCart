package com.cappellinispirito.ispw_project_202223_jfx.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    public int getAverageScore() {
        return averageFinalScore;
    }

    public Date getDate(){
        return this.date;
    }

    public void addItem(Item newItem){
        itemsList.add(newItem);
        setAverageScore();
    }

    public void removeItem(Item trashItem){
        itemsList.remove(trashItem);
        setAverageScore();
    }

    public void setTotalPrice(){
        float tmpPrice = 0;
        for (Item item : this.itemsList) {
            tmpPrice += item.getPrice();
        }
        this.totalPrice=tmpPrice;
    }

    public void setAverageScore(){
        int tmp=0;
        for (Item item:this.itemsList) {
            tmp+=item.getHealthScore();
        }
        this.averageFinalScore= tmp/itemsList.size();
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
