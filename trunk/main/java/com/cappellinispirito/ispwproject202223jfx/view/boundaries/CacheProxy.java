package com.cappellinispirito.ispwproject202223jfx.view.boundaries;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.PositionBean;
import com.cappellinispirito.ispwproject202223jfx.model.dao.CartsDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CacheProxy implements APIProxyBoundary{
    private final HashMap<String, String> searchToSupermarketsNameCache = new HashMap<>();
    private final HashMap<String, String> searchToSupermarketsAddressCache = new HashMap<>();
    private final HashMap<String, Float> searchToDistanceCache = new HashMap<>();
    private final APIProxyBoundary instance;

    public CacheProxy(APIProxyBoundary boundary) {
        this.instance = boundary;
    }

    @Override
    public void getNearestSupermarkets(PositionBean bean) throws IOException {
        String searchAddress = bean.getAddress();
        //proxy will check if the search results are already been searched. If yes, return them.
        if(searchToSupermarketsNameCache.containsKey(searchAddress)){
            bean.appendToSupermarketsNames(searchToSupermarketsNameCache.get(searchAddress));
            bean.appendToSupermarketsAddress(searchToSupermarketsAddressCache.get(searchAddress));
            bean.appendToSupermarketsDistance(searchToDistanceCache.get(searchAddress));
        } else {
            //If no, call the API and then save the results into the cache.
            instance.getNearestSupermarkets(bean);
            try{
                searchToSupermarketsNameCache.put(bean.getAddress(), bean.getSupermarketsNames().get(bean.getSupermarketsNames().size()-1));
                searchToSupermarketsAddressCache.put(bean.getAddress(), bean.getSupermarketsAddress().get(bean.getSupermarketsAddress().size()-1));
                searchToDistanceCache.put(bean.getAddress(), bean.getSupermarketsDistance().get(bean.getSupermarketsDistance().size()-1));
            } catch (IndexOutOfBoundsException e){
                Logger logger = Logger.getLogger(CacheProxy.class.getName());
                logger.log(Level.INFO, e.getMessage());
            }

        }
    }
}
