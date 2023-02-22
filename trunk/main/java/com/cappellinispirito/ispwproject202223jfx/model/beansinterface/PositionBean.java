package com.cappellinispirito.ispwproject202223jfx.model.beansinterface;

import java.util.List;

public interface PositionBean {
    String getAddress();
    void setAddress(String address);

    //output data
    List<String> getSupermarketsNames();
    void appendToSupermarketsNames(String name);

    List<String> getSupermarketsAddress();
    void appendToSupermarketsAddress(String address);

    List<Float> getSupermarketsDistance();
    void appendToSupermarketsDistance(float distance);

}
