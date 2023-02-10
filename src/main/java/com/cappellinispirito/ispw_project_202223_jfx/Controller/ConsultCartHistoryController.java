package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.CartHistoryBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.CartsDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ConsultCartHistoryController {
    private String username = LogInController.getInstance().getUserAccountInstance().getUsername();
    private List<Date> CartsDates;
    private List<Integer> CartsId;
    private List<Integer> CartsScores;
    private HashMap<Integer, String> cartIdToBarcodemap = new HashMap<Integer, String>();

    public void getCartHistory(CartHistoryBean bean) throws SQLException {
        CartsDAO cartsDAO = new CartsDAO();
        cartIdToBarcodemap = cartsDAO.getOldCartItems(username);
    }
}
