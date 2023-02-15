package com.cappellinispirito.ispwproject202223jfx.view.boundaries;

import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.positionBean;

import java.util.HashMap;

public class CacheProxy implements APIProxyBoundary{
    private final HashMap<String, String> searchToSupermarketsNameCache = new HashMap<>();
    private final HashMap<String, String> searchToSupermarketsAddressCache = new HashMap<>();
    private final HashMap<String, Float> searchToDistanceCache = new HashMap<>();
    private final APIProxyBoundary instance;

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
            searchToSupermarketsNameCache.put(bean.getAddress(), bean.getSupermarketsNames().get(bean.getSupermarketsNames().size()));
            searchToSupermarketsAddressCache.put(bean.getAddress(), bean.getSupermarketsAddress().get(bean.getSupermarketsAddress().size()));
            searchToDistanceCache.put(bean.getAddress(), bean.getSupermarketsDistance().get(bean.getSupermarketsDistance().size()));
        }
    }
}
