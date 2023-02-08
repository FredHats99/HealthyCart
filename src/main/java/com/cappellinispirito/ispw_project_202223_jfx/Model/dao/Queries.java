package com.cappellinispirito.ispw_project_202223_jfx.Model.dao;

import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

    public static void createAccount(Statement stmt, String username, String password) throws SQLException {
        String insertStatement = String.format("INSERT INTO Users(Username, Password_, isPremium) VALUES ('%s', '%s', 0);");
        stmt.execute(insertStatement);
    }

    public static void changePassword(Statement stmt, String username, String newPassword) throws SQLException {
        String updateStatement = String.format("if exists(SELECT username from Users WHERE username = '%s') then update Users set Users.password = '%s' where username = '%s' end if;", username, newPassword, username);
        stmt.executeUpdate(updateStatement);
    }

}
