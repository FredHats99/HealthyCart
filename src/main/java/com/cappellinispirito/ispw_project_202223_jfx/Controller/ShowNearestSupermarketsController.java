package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Supermarket;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nearestSupermarketBeanInterface;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.positionBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.APIProxyBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.CacheProxy;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.showNearestSupermarketsNominatimAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.positionBeanClass;

public class ShowNearestSupermarketsController {
    public static ShowNearestSupermarketsController instance;
    private Supermarket chosenSupermarket;

    private ShowNearestSupermarketsController(){}

    public static ShowNearestSupermarketsController getInstance(){
        if(instance == null){
            instance = new ShowNearestSupermarketsController();
        }
        return instance;
    }

    public void getNearestSupermarkets(nearestSupermarketBeanInterface bean) throws Exception {
            String address = bean.getSearch();
            positionBean bean2 = new positionBeanClass();
            bean2.setAddress(address);
            APIProxyBoundary boundary = new showNearestSupermarketsNominatimAPIBoundary();
            APIProxyBoundary ProxyBoundary = new CacheProxy(boundary);

            ProxyBoundary.getNearestSupermarkets(bean2);

            bean.setSupermarketsNamesList(bean2.getSupermarketsNames());
            bean.setSupermarketsNamesAddresses(bean2.getSupermarketsAddress());
            bean.setSupermarketsNamesDistances(bean2.getSupermarketsDistance());
    }

    public void createSupermarket(String name, String address) {
        chosenSupermarket = new Supermarket(name, address);
    }

    public Supermarket getChosenSupermarket(){
        return chosenSupermarket;
    }
}
