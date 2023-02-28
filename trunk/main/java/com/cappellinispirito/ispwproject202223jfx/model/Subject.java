package com.cappellinispirito.ispwproject202223jfx.model;

import com.cappellinispirito.ispwproject202223jfx.view.Observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
