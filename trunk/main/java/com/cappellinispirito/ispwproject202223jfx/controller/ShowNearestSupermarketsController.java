package com.cappellinispirito.ispwproject202223jfx.controller;


import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.nearestSupermarketBeanInterface;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.positionBean;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.APIProxyBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.CacheProxy;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.ShowNearestSupermarketsNominatimApiBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.beans.PositionBeanClass;

public class ShowNearestSupermarketsController {
    public static ShowNearestSupermarketsController instance;

    private ShowNearestSupermarketsController(){}

    public static ShowNearestSupermarketsController getInstance(){
        if(instance == null){
            instance = new ShowNearestSupermarketsController();
        }
        return instance;
    }

    public void getNearestSupermarkets(nearestSupermarketBeanInterface bean) throws Exception {
            String address = bean.getSearch();
            positionBean bean2 = new PositionBeanClass();
            bean2.setAddress(address);
            APIProxyBoundary boundary = new ShowNearestSupermarketsNominatimApiBoundary();
            APIProxyBoundary proxyBoundary = new CacheProxy(boundary);

            proxyBoundary.getNearestSupermarkets(bean2);

            bean.setSupermarketsNamesList(bean2.getSupermarketsNames());
            bean.setSupermarketsNamesAddresses(bean2.getSupermarketsAddress());
            bean.setSupermarketsNamesDistances(bean2.getSupermarketsDistance());
    }

}
