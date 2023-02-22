package com.cappellinispirito.ispwproject202223jfx.view.boundaries;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.PositionBean;



import java.util.*;


public class ShowNearestSupermarketsNominatimApiBoundary implements APIProxyBoundary{

    private final List<String> supermarketsName = new ArrayList<>();
    private final List<Float> supermarketsDistances = new ArrayList<>();


    @Override
    public void getNearestSupermarkets(PositionBean bean){
        String address = bean.getAddress().replace(" ","+");

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
            Float distance = supermarketsDistances.get(i);

            bean.appendToSupermarketsNames(name);
            bean.appendToSupermarketsAddress(address);
            bean.appendToSupermarketsDistance(distance);
        }
    }
}
