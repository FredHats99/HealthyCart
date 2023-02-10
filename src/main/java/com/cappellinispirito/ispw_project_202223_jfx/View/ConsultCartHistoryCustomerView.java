package com.cappellinispirito.ispw_project_202223_jfx.View;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.ConsultCartController;
import com.cappellinispirito.ispw_project_202223_jfx.Controller.ConsultCartHistoryController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.CartHistoryBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.CartItemsBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.CartHistoryBeanClass;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.CartItemsBeanClass;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ConsultCartHistoryCustomerView {
    private List<Date> cartsDate;
    private List<Integer> cartsScore;

    private int chosenIndex;
    private List<String> CartItemsNames;
    private List<String> CartItemsImages;

    public void showOldCarts() throws SQLException {
        CartHistoryBean bean = new CartHistoryBeanClass();
        ConsultCartHistoryController controller = ConsultCartHistoryController.getInstance();
        controller.getCartHistory(bean);
        cartsDate = bean.getCartsDate();
        cartsScore = bean.getCartsScore();
        //Now display them...
    }

    public void showCart(){
        ConsultCartHistoryController controller = ConsultCartHistoryController.getInstance();
        //todo: I need to know which index has been clicked by user
        int cartId = controller.getCartIdFromIndex(chosenIndex);
        CartItemsBean bean = new CartItemsBeanClass();
        bean.setCartId(cartId);
        ConsultCartController controller2 = new ConsultCartController();
        controller2.getItemInfosFromId(bean);
        CartItemsNames = bean.getItemsNamesList();
        CartItemsImages = bean.getItemsImagesList();
        //Now display them...
    }
}
