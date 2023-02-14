package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.positionBean;

import java.util.HashMap;

public class CacheProxy implements APIProxyBoundary{
    private HashMap<String, String> searchToSupermarketsNameCache = new HashMap<>();
    private HashMap<String, String> searchToSupermarketsAddressCache = new HashMap<>();
    private HashMap<String, Float> searchToDistanceCache = new HashMap<>();
    private APIProxyBoundary instance;

    public CacheProxy(APIProxyBoundary boundary) {
        this.instance = boundary;
    }

    @Override
    public void getNearestSupermarkets(positionBean bean) throws Exception {
        String searchAddress = bean.getAddress();
        //proxy will check if the search results are already been searched. If yes, return them.
        if(searchToSupermarketsNameCache.containsKey(searchAddress)){
            bean.appendToSupermarketsNames(searchToSupermarketsNameCache.get(searchAddress));
            bean.appendToSupermarketsAddress(searchToSupermarketsAddressCache.get(searchAddress));
            bean.appendToSupermarketsDistance(searchToDistanceCache.get(searchAddress));
        } else {
            //If no, call the API and then save the results into the cache.
            instance.getNearestSupermarkets(bean);
            //Missing how to save them...
        }
    }
}
