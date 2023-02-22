package com.cappellinispirito.ispwproject202223jfx.controller;


import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.NearestSupermarketBeanInterface;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.PositionBean;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.APIProxyBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.CacheProxy;
import com.cappellinispirito.ispwproject202223jfx.view.boundaries.ShowNearestSupermarketsNominatimApiBoundary;
import com.cappellinispirito.ispwproject202223jfx.view.beans.PositionBeanClass;

public class ShowNearestSupermarketsController {
    private static ShowNearestSupermarketsController instance;

    private ShowNearestSupermarketsController(){}

    public static ShowNearestSupermarketsController getInstance(){
        if(instance == null){
            instance = new ShowNearestSupermarketsController();
        }
        return instance;
    }

    public void getNearestSupermarkets(NearestSupermarketBeanInterface bean) {
            String address = bean.getSearch();
            PositionBean bean2 = new PositionBeanClass();
            bean2.setAddress(address);
            APIProxyBoundary boundary = new ShowNearestSupermarketsNominatimApiBoundary();
            APIProxyBoundary proxyBoundary = new CacheProxy(boundary);

            proxyBoundary.getNearestSupermarkets(bean2);

            bean.setSupermarketsNamesList(bean2.getSupermarketsNames());
            bean.setSupermarketsNamesAddresses(bean2.getSupermarketsAddress());
            bean.setSupermarketsNamesDistances(bean2.getSupermarketsDistance());
    }

}
