package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.View.beans.supermarketNameBean;

public class DoShoppingControllerG {

    public String chosenSupermarket;

    public DoShoppingControllerG(){
        supermarketNameBean bean = supermarketNameBean.getInstance();
        chosenSupermarket = bean.getSupermarketName();
    }
}
