package com.cappellinispirito.ispwproject202223jfx.view.beans;

import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.CartHistoryBean;

import java.util.Date;
import java.util.List;

public class CartHistoryBeanClass implements CartHistoryBean {
    private List<Date> cartsDate;
    private List<Integer> cartsScore;

    @Override
    public List<Date> getCartsDate() {
        return cartsDate;
    }

    @Override
    public void setCartsDate(List<Date> cartsDate) {
        this.cartsDate = cartsDate;
    }

    @Override
    public List<Integer> getCartsScore() {
        return cartsScore;
    }

    @Override
    public void setCartsScore(List<Integer> cartsScore) {
        this.cartsScore = cartsScore;
    }
}
