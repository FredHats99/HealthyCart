package com.cappellinispirito.ispw_project_202223_jfx.Controller;

public interface SingletonInstance {
    default SingletonInstance getInstance() {
        return null;
    }
}
