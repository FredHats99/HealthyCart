package com.cappellinispirito.ispwproject202223jfx.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

    private Queries(){}

    public static void createAccount(Statement stmt, String username, String password) throws SQLException {
        String insertStatement = String.format("INSERT INTO Users(Username, Password, isPremium) VALUES ('%s', '%s', 0);", username, password);
        stmt.execute(insertStatement);
    }

    public static ResultSet checkCredentials(Statement stmt, String username, String password) throws SQLException{
        String selectStatement = String.format("SELECT * FROM Users WHERE Username = '%s' AND Password = '%s';", username, password);
        stmt.execute(selectStatement);
        return stmt.getResultSet();
    }

    public static void updateToPremium(Statement stmt, String username, boolean prevIsPremium) throws SQLException{
        int newTinyInt;
        if(prevIsPremium){
            newTinyInt = 1;
        } else {
            newTinyInt = 0;
        }
        String updateStatement = String.format("Update Users set isPremium = %d where username = '%s';",newTinyInt, username);
        stmt.executeUpdate(updateStatement);
    }

    public static void addCart(Statement stmt, String username, int avgScore) throws SQLException {
        String insertStatement = String.format("INSERT INTO carts(user, date, avgScore) VALUES ('%s', curdate(), '%s');", username, avgScore);
        stmt.execute(insertStatement);
    }

    public static ResultSet getAdditiveDangerousness(Statement stmt, String additive) throws SQLException{
        String selectStatement = String.format("SELECT Dangerousness FROM Additives WHERE name = '%s';",additive);
        stmt.execute(selectStatement);
        return stmt.getResultSet();
    }

    public static void addCartItem(Statement stmt, String cartUser, String barcode, int quantity) throws SQLException{
        String selectCartIdStatement = String.format("SELECT idCarts FROM carts WHERE user = '%s'", cartUser);
        stmt.execute(selectCartIdStatement);
        ResultSet tmpRs = stmt.getResultSet();
        tmpRs.next();
        int cartId = tmpRs.getInt(1);
        String insertStatement = String.format("INSERT INTO ItemsInCart(Cart, Barcode, Quantity) VALUES ('%s', '%s', '%s');",cartId, barcode, quantity);
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

    public static void deleteHistory(Statement stmt, Integer cartsId) throws SQLException {
        String deleteStatement = String.format("DELETE FROM ItemsInCart WHERE cart = '%d'", cartsId);
        stmt.execute(deleteStatement);
        deleteStatement = String.format("DELETE FROM Carts WHERE idCarts = '%d'", cartsId);
        stmt.execute(deleteStatement);
    }
}
