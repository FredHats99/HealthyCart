package com.cappellinispirito.ispwproject202223jfx.model.beansInterface;

import java.util.List;

public interface positionBean {
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
