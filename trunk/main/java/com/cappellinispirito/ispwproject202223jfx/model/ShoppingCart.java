package com.cappellinispirito.ispwproject202223jfx.model;

import com.cappellinispirito.ispwproject202223jfx.view.Observer;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Subject{

    //attributes
    private final List<Item> itemsList = new ArrayList<>();
    private int averageFinalScore;
    private Observer doShoppingObserver;

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
        notifyObservers();
    }

    public void removeItem(Item trashItem){
        itemsList.remove(trashItem);
        setAverageScore();
        notifyObservers();
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
        try{
            this.averageFinalScore= tmp/tmpSize;
        } catch (ArithmeticException e){
            this.averageFinalScore = 0;
        }

    }

    @Override
    public void registerObserver(Observer observer) {
        this.doShoppingObserver = observer;
    }

    @Override
    public void removeObserver(Observer observer) {
        doShoppingObserver = null;
    }

    @Override
    public void notifyObservers() {
        doShoppingObserver.update(this);
    }
}
