package com.cappellinispirito.ispw_project_202223_jfx.Model.dao;

import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.conenctor.DBConnector;

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
        CallableStatement stmt;
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
                if(cartId == idCart){
                    barcodesInCart.add(Itembarcode);
                }
            } while(rs.next());
            return barcodesInCart;

        } catch (NullPointerException e){
           e.printStackTrace();
        }
        return barcodesInCart;
    }
}
