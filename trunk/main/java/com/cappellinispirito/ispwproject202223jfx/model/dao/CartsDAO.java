package com.cappellinispirito.ispwproject202223jfx.model.dao;

import com.cappellinispirito.ispwproject202223jfx.model.dao.conenctor.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartsDAO {
    public void addCart(String username, int avgScore) throws SQLException {
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.addCart(stmt,username,avgScore);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CartsDAO.class.getName());
            logger.log(Level.INFO, e.getMessage());
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
            Logger logger = Logger.getLogger(CartsDAO.class.getName());
            logger.log(Level.INFO, e.getMessage());
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
            rs.next();
            do{
                dateList.add(rs.getDate("date"));
            } while(rs.next());
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CartsDAO.class.getName());
            logger.log(Level.INFO, e.getMessage());
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
            rs.next();
            do{
                scoreList.add(rs.getInt("avgScore"));
            } while(rs.next());
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CartsDAO.class.getName());
            logger.log(Level.INFO, e.getMessage());
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
            rs.next();
            do{
                idList.add(rs.getInt("idCarts"));
            } while(rs.next());
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CartsDAO.class.getName());
            logger.log(Level.INFO, e.getMessage());
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
            Logger logger = Logger.getLogger(CartsDAO.class.getName());
            logger.log(Level.INFO, e.getMessage());
        } finally {
            assert stmt != null;
            stmt.close();
        }
        return barcodesInCart;
    }

    public void deleteHistory(String username) throws SQLException {
        Statement stmt = null;
        Connection conn;
        try{
            List<Integer> cartsId = getOldCartId(username);
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            for(Integer cartId : cartsId){
                Queries.deleteHistory(stmt, cartId);
            }

        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CartsDAO.class.getName());
            logger.log(Level.INFO, e.getMessage());
        } finally {
            assert stmt != null;
            stmt.close();
        }
    }
}
