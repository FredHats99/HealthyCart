package com.cappellinispirito.ispw_project_202223_jfx.Controller;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Supermarket;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.nearestSupermarketBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.positionBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.APIProxyBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.CacheProxy;
import com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries.showNearestSupermarketsGeoLocalizationAPIBoundary;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.positionBeanClass;

import java.util.List;

public class ShowNearestSupermarketsController {

    public void getNearestSupermarkets(nearestSupermarketBean bean) {
            String search = bean.getSearch();
            positionBean bean2 = new positionBeanClass();
            bean2.setAddress(search);
            APIProxyBoundary boundary = new showNearestSupermarketsGeoLocalizationAPIBoundary();
            APIProxyBoundary ProxyBoundary = new CacheProxy(boundary);
            ProxyBoundary.getNearestSupermarkets(bean2);
            bean.setSupermarketsNamesList(bean2.getSupermarketsNames());
            bean.setSupermarketsNamesAddresses(bean2.getSupermarketsAddress());
            bean.setSupermarketsNamesDistances(bean2.getSupermarketsDistance());
    }

    public void createSupermarket(String name, String address) {
        Supermarket supermarket = new Supermarket(name, address);
    }
}
