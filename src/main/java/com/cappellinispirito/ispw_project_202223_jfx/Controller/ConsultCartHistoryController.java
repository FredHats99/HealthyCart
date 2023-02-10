package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.CartHistoryBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.CartsDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ConsultCartHistoryController {
    private String username = LogInController.getInstance().getUserAccountInstance().getUsername();
    private List<Date> cartsDates;
    private List<Integer> cartsId;
    private List<Integer> cartsScores;
    private HashMap<Integer, String> cartIdToBarcodemap = new HashMap<Integer, String>();

    public void getCartHistory(CartHistoryBean bean) throws SQLException {
        CartsDAO cartsDAO = new CartsDAO();
        cartsDates = cartsDAO.getOldCartDate(username);
        cartsId = cartsDAO.getOldCartId(username);
        cartsScores = cartsDAO.getOldCartScores(username);
        cartIdToBarcodemap = cartsDAO.getOldCartItems(username);
        bean.setCartsDate(cartsDates);
        bean.setCartsScore(cartsScores);
    }
}
