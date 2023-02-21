package com.cappellinispirito.ispwproject202223jfx.model.beansinterface;

import java.util.Date;
import java.util.List;

public interface CartHistoryBean {
    List<Date> getCartsDate();
    void setCartsDate(List<Date> cartsDate);

    List<Integer> getCartsScore();
    void setCartsScore(List<Integer> cartsScore);
}
