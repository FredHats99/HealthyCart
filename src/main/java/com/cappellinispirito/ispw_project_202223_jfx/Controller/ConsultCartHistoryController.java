package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.CartHistoryBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.CartItemsBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.CartsDAO;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.CartItemsBeanClass;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ConsultCartHistoryController {
    public static ConsultCartHistoryController instance;
    private String username = LogInController.getInstance().getUserAccountInstance().getUsername();
    private List<Date> cartsDates;
    private List<Integer> cartsId;
    private List<Integer> cartsScores;
    private HashMap<Integer, String> cartIdToBarcodemap = new HashMap<Integer, String>();

    private ConsultCartHistoryController(){}

    public static ConsultCartHistoryController getInstance(){
        if(instance == null){
            instance = new ConsultCartHistoryController();
        }
        return instance;
    }

    public void getCartHistory(CartHistoryBean bean) throws SQLException {
        CartsDAO cartsDAO = new CartsDAO();
        cartsDates = cartsDAO.getOldCartDate(username);
        cartsId = cartsDAO.getOldCartId(username);
        cartsScores = cartsDAO.getOldCartScores(username);
        cartIdToBarcodemap = cartsDAO.getOldCartItems(username);
        bean.setCartsDate(cartsDates);
        bean.setCartsScore(cartsScores);
    }

    public HashMap<Integer, String> getCartIdToBarcodemap(){
        return cartIdToBarcodemap;
    }

    public Integer getCartIdFromIndex(int index){
        return cartsId.get(index);
    }

    public void getCartItems(){}
}
