package com.cappellinispirito.ispwproject202223jfx.view;

import com.cappellinispirito.ispwproject202223jfx.controller.ConsultCartHistoryController;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.CartHistoryBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.CartHistoryBeanClass;

import java.util.Date;
import java.util.List;

public class ConsultCartHistoryCustomerView {

    private final ConsultCartHistoryController controller = ConsultCartHistoryController.getInstance();
    private List<Integer> cartsScores;
    private List<Date> cartsDates;

    public void getCartHistory(String username) {
        CartHistoryBean bean = new CartHistoryBeanClass();
        bean.setUsername(username);
        controller.getCartHistory(bean);
        cartsScores = bean.getCartsScore();
        cartsDates = bean.getCartsDate();
    }

    public String getDateAt(int i) {
        return cartsDates.get(i).toString();
    }

    public Integer getCartScoreAt(int i) {
        return cartsScores.get(i);
    }
}