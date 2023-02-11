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
    //todo: undo for using mysql
    //requires mysql.connector.j;

    opens com.cappellinispirito.ispw_project_202223_jfx to javafx.fxml;
    exports com.cappellinispirito.ispw_project_202223_jfx;
    exports com.cappellinispirito.ispw_project_202223_jfx.Model;
    opens com.cappellinispirito.ispw_project_202223_jfx.Model to javafx.fxml;
    exports com.cappellinispirito.ispw_project_202223_jfx.View;
    opens com.cappellinispirito.ispw_project_202223_jfx.View to javafx.fxml;
    exports com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;
    opens com.cappellinispirito.ispw_project_202223_jfx.View.Graphics to javafx.fxml;
}