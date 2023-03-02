package com.cappellinispirito.ispwproject202223jfx.view.boundaries;

import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.PositionBean;

import java.io.IOException;

public interface APIProxyBoundary {
     void getNearestSupermarkets(PositionBean bean) throws IOException;
}
