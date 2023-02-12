package com.cappellinispirito.ispw_project_202223_jfx.Model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

    public static void createAccount(Statement stmt, String username, String password) throws SQLException {
        String insertStatement = String.format("INSERT INTO Users(Username, Password, isPremium) VALUES ('%s', '%s', 0);", username, password);
        stmt.execute(insertStatement);
    }

    public static ResultSet checkCredentials(Statement stmt, String username, String password) throws SQLException{
        String selectStatement = String.format("SELECT * FROM Users WHERE Username = '%s' AND Password = '%s';", username, password);
        stmt.execute(selectStatement);
        return stmt.getResultSet();
    }

    public static void updateToPremium(Statement stmt, String username) throws SQLException{
        String updateStatement = String.format("if exists(SELECT username from Users WHERE username = '%s') then update Users set isPremium = 1 where username = '%s'; end if;", username, username);
        stmt.executeUpdate(updateStatement);
    }

    public static void addCart(Statement stmt, String username, int avgScore) throws SQLException {
        String insertStatement = String.format("INSERT INTO Carts(user, date, avgScore) VALUES ('%s', curdate(), '%s');", username, avgScore);
        stmt.execute(insertStatement);
    }

    public static ResultSet getAdditiveDangerousness(Statement stmt, String additive) throws SQLException{
        String selectStatement = String.format("SELECT Dangerousness FROM Additives WHERE name = '%s';",additive);
        stmt.execute(selectStatement);
        return stmt.getResultSet();
    }

    public static void addCartItem(Statement stmt, String cartUser, String barcode, int quantity) throws SQLException{
        String insertStatement = String.format("DECLARE Cart_id int;" +
                                                "SET Cart_id = (SELECT CartId FROM Carts WHERE user = '%s');" +
                                                "INSERT INTO ItemsInCart(CartId, Barcode, Quantity) VALUES (CartId, '%s', '%s');",cartUser, barcode, quantity);
        stmt.execute(insertStatement);
    }

    public static ResultSet getPremium(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT isPremium FROM Users WHERE Username = '%s';",username);
        stmt.execute(selectStatement);
        return stmt.getResultSet();
    }

    public static ResultSet checkIfUserExists(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT * FROM Users WHERE Username = '%s';", username);
        stmt.execute(selectStatement);
        return stmt.getResultSet();
    }

    public static void getOldCartsDate(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT date FROM Carts WHERE user = '%s'",username);
        stmt.execute(selectStatement);
    }

    public static void getOldCartsScore(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT avgScore FROM Carts WHERE user = '%s'",username);
        stmt.execute(selectStatement);
    }

    public static void getOldCartsId(Statement stmt, String username) throws SQLException {
        String selectStatement = String.format("SELECT idCarts FROM Carts WHERE user = '%s'",username);
        stmt.execute(selectStatement);
    }
}
