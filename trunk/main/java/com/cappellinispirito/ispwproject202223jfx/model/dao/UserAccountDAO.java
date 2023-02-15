package com.cappellinispirito.ispwproject202223jfx.model.dao;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedLoginException;
import com.cappellinispirito.ispwproject202223jfx.model.dao.conenctor.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAccountDAO {


    public boolean checkCredentials(String username, String password) throws SQLException, FailedLoginException{
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Queries.checkCredentials(stmt, username, password);

            assert rs != null;
            if(!rs.first()){
                throw new FailedLoginException("Username or Password not valid!");
            }
            rs.first();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
        return false;
    }


    public void createAccount(String username, String password) throws SQLException{
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.createAccount(stmt, username, password);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }

    public void updateToPremium(String username, boolean prevIsPremium) throws SQLException{
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.updateToPremium(stmt, username, prevIsPremium);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }

    public boolean getPremium(String username) throws SQLException {
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            ResultSet rs = Queries.getPremium(stmt, username);

            assert rs != null;
            if(!rs.next()){
                throw new FailedLoginException("Username or Password not valid!");
            }
            return rs.getBoolean("isPremium");

        } catch (SQLException | FailedLoginException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
        return false;
    }

    public boolean checkIfUserExists(String username) throws SQLException {
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            ResultSet rs = Queries.checkIfUserExists(stmt, username);
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
        return false;
    }
}
