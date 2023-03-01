package com.cappellinispirito.ispwproject202223jfx.model;

import com.cappellinispirito.ispwproject202223jfx.view.Observer;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Subject{

    //attributes
    private final List<Item> itemsList = new ArrayList<>();
    private int averageFinalScore;
    private Observer DoShoppingObserver;

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
        System.out.println("Score is "+ newItem.getHealthScore());
        notifyObservers();
    }

    public void removeItem(Item trashItem){
        itemsList.remove(trashItem);
        setAverageScore();
    }

    public void setAverageScore(){
        int tmp=0;
        int tmpSize = itemsList.size();
        for (Item item:this.itemsList) {
            if(item.getHealthScore() != -1){
                tmp+=item.getHealthScore();
            } else {
                tmpSize--;
            }
        }
        this.averageFinalScore= tmp/tmpSize;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.DoShoppingObserver = observer;
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {
        DoShoppingObserver.update(this);
    }
}
