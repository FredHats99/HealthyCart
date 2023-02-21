module com.cappellinispirito.ispw_project_202223_jfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    //requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires json.simple;
    requires java.sql;
    requires mysql.connector.j;
    //todo: undo for using mysql
    //requires mysql.connector.j;

    opens com.cappellinispirito.ispwproject202223jfx to javafx.fxml;
    exports com.cappellinispirito.ispwproject202223jfx;
    exports com.cappellinispirito.ispwproject202223jfx.model;
    opens com.cappellinispirito.ispwproject202223jfx.model to javafx.fxml;
    exports com.cappellinispirito.ispwproject202223jfx.view;
    opens com.cappellinispirito.ispwproject202223jfx.view to javafx.fxml;
    exports com.cappellinispirito.ispwproject202223jfx.view.graphics;
    opens com.cappellinispirito.ispwproject202223jfx.view.graphics to javafx.fxml;
    exports com.cappellinispirito.ispwproject202223jfx.controller;
    exports com.cappellinispirito.ispwproject202223jfx.model.beansinterface;
    exports com.cappellinispirito.ispwproject202223jfx.view.beans;
    exports com.cappellinispirito.ispwproject202223jfx.model.exceptions;
    exports com.cappellinispirito.ispwproject202223jfx.model.dao;
}