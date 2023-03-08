package com.cappellinispirito.ispwproject202223jfx.controller;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.CartHistoryBean;

import com.cappellinispirito.ispwproject202223jfx.model.dao.CartsDAO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

public class ConsultCartHistoryController {
    private static ConsultCartHistoryController instance;
    private final String username = LogInController.getInstance().getUserAccountInstance().getUsername();
    private List<Integer> cartsId = new ArrayList<>();

    private ConsultCartHistoryController(){}

    public static ConsultCartHistoryController getInstance(){
        if(instance == null){
            instance = new ConsultCartHistoryController();
        }
        return instance;
    }

    public void getCartHistory(CartHistoryBean bean) {
        CartsDAO cartsDAO = new CartsDAO();
        List<Date> cartsDates = cartsDAO.getOldCartDate(username);
        cartsId = cartsDAO.getOldCartId(username);
        List<Integer> cartsScores = cartsDAO.getOldCartScores(username);
        bean.setCartsDate(cartsDates);
        bean.setCartsScore(cartsScores);
    }

    public Integer getCartIdFromIndex(int index){
        return cartsId.get(index);
    }

    public void deleteHistory() throws SQLException {
        CartsDAO cartsDAO = new CartsDAO();
        cartsDAO.deleteHistory(username);
    }
}
