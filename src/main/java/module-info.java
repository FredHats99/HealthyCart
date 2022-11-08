module com.cappellinispirito.ispw_project_202223_jfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.cappellinispirito.ispw_project_202223_jfx to javafx.fxml;
    exports com.cappellinispirito.ispw_project_202223_jfx;
}