package com.cappellinispirito.ispw_project_202223_jfx.View.Boundaries;

import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.positionBean;



import java.util.*;


public class showNearestSupermarketsNominatimAPIBoundary implements APIProxyBoundary{

    public static String address;
    private final List<String> supermarketsName = new ArrayList<>();
    private final List<Float> supermarketsDistances = new ArrayList<>();


    @Override
    public void getNearestSupermarkets(positionBean bean){
        address = bean.getAddress().replace(" ","+");

        supermarketsName.add("Carrefour");
        supermarketsName.add("Auchan");
        supermarketsName.add("Esselunga");
        supermarketsName.add("Conad");
        supermarketsName.add("Lidl");
        int i;
        for(i=0;i<7;i++){
            float x = (float) (1 + Math.random()* 9);
            supermarketsDistances.add(Math.round(x * 100f) / 100f);
        }
        Collections.sort(supermarketsDistances);

        for(i=0;i<5;i++){
            String name = supermarketsName.get(i);
            String address = "";
            Float distance = supermarketsDistances.get(i);

            bean.appendToSupermarketsNames(name);
            bean.appendToSupermarketsAddress(address);
            bean.appendToSupermarketsDistance(distance);
        }
    }
}
