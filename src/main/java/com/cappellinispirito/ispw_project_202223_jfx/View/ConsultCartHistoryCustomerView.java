package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ConsultCartHistoryController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.CartHistoryBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.CartHistoryBeanClass;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ConsultCartHistoryCustomerView {
    private List<Date> cartsDate;
    private List<Integer> cartsScore;

    public void showOldCarts() throws SQLException {
        CartHistoryBean bean = new CartHistoryBeanClass();
        ConsultCartHistoryController controller = new ConsultCartHistoryController();
        controller.getCartHistory(bean);
        cartsDate = bean.getCartsDate();
        cartsScore = bean.getCartsScore();
        //Now display them...
    }
}
