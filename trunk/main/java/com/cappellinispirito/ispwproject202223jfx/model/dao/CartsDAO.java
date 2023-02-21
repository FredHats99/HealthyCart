package com.cappellinispirito.ispwproject202223jfx.model.dao;

import com.cappellinispirito.ispwproject202223jfx.model.dao.conenctor.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
            e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }

    public List<Date> getOldCartDate(String username){
        List<Date> dateList = new ArrayList<>();
        Statement stmt;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.getOldCartsDate(stmt, username);
            ResultSet rs = stmt.getResultSet();
            rs.first();
            do{
                dateList.add(rs.getDate("date"));
            } while(rs.next());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dateList;
    }

    public List<Integer> getOldCartScores(String username){
        List<Integer> scoreList = new ArrayList<>();
        Statement stmt;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.getOldCartsScore(stmt, username);
            ResultSet rs = stmt.getResultSet();
            rs.first();
            do{
                scoreList.add(rs.getInt("avgScore"));
            } while(rs.next());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return scoreList;
    }

    public List<Integer> getOldCartId(String username){
        List<Integer> idList = new ArrayList<>();
        Statement stmt;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.getOldCartsId(stmt, username);
            ResultSet rs = stmt.getResultSet();
            rs.first();
            do{
                idList.add(rs.getInt("idCarts"));
            } while(rs.next());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idList;
    }

    public List<String> getOldCartItems(String username, int idCart) throws SQLException {
        List<String> barcodesInCart = new ArrayList<>();
        CallableStatement stmt = null;
        Connection conn;
        int cartId;
        String itemBarcode;
        ResultSet rs;
        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.prepareCall("{call get_carts(?)}");
            stmt.setString(1,username);
            rs = stmt.getResultSet();
            rs.first();
            do{
                cartId = rs.getInt("Cart");
                itemBarcode = rs.getString("Barcode");
                if(cartId == idCart){
                    barcodesInCart.add(itemBarcode);
                }
            } while(rs.next());
            return barcodesInCart;

        } catch (NullPointerException e){
           e.printStackTrace();
        } finally {
            assert stmt != null;
            stmt.close();
        }
        return barcodesInCart;
    }
}
