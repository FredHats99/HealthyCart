package com.cappellinispirito.ispw_project_202223_jfx.Model.dao;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.SingletonInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector{
    private static final String USER = "root";
    private static final String PASS = "Federico";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";

    private static DBConnector instance = null;
    private Connection conn = null;

    private DBConnector(){
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnector getInstance(){
        if(instance == null){
            instance = new DBConnector();
        }
        return instance;
    }

    public Connection getConnection(){
           return conn;
    }
}
