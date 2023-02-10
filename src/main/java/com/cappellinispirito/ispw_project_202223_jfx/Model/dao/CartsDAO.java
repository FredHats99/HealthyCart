package com.cappellinispirito.ispw_project_202223_jfx.Model.dao;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CartsDAO {
    public void addCart(String username, int avgScore) throws SQLException {
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.addCart(stmt,username,avgScore);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }

    public void addItemToCart(String username, String barcode, int quantity) throws SQLException {
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.addCartItem(stmt,username,barcode,quantity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }

    private List<Date> getOldCart(String username){
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.getOldCartsDate(stmt, username);
            return stmt.getResultSet();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public HashMap<Integer, String> getOldCartItems(String username) throws SQLException {
        HashMap<Integer, String> CartToItembarcode = new HashMap<>();
        CallableStatement stmt = null;
        Connection conn;
        int cartId;
        String Itembarcode;
        ResultSet rs;
        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.prepareCall("{call get_carts(?)}");
            stmt.setString(1,username);
            rs = stmt.getResultSet();
            rs.first();
            do{
                cartId = rs.getInt("Cart");
                Itembarcode = rs.getString("Barcode");
                CartToItembarcode.put(cartId, Itembarcode);
            } while(rs.next());
            return CartToItembarcode;

        } catch (NullPointerException e){
           e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, Integer> getOldItemsQuantity(String username) throws SQLException{
        HashMap<String, Integer> ItemsToQuantity = new HashMap<>();
        String Itembarcode;
        int quantity;
        ResultSet rs = getOldCart(username);
        try{
            rs.first();
            do{
                Itembarcode = rs.getString("barcode");
                quantity = Integer.parseInt(rs.getString("quantity"));
                ItemsToQuantity.put(Itembarcode, quantity);
            } while(rs.next());
            return ItemsToQuantity;
        } catch (NullPointerException e){
            return null;
        }
    }
}
