package com.cappellinispirito.ispw_project_202223_jfx.Model.dao;

import javax.security.auth.login.FailedLoginException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAccountDAO {


    public boolean checkCredentials(String username, String password) throws SQLException {
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            ResultSet rs = Queries.checkCredentials(stmt, username, password);

            assert rs != null;
            if(!rs.first()){
                throw new FailedLoginException("Username or Password not valid!");
            }
            rs.first();
            return true;

        } catch (SQLException | FailedLoginException e) {
            throw new RuntimeException(e);
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }

    public void changePassword(String username, String newPassword) throws SQLException {
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.changePassword(stmt, username, newPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }

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

    public void updateToPremium(String username) throws SQLException{
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            Queries.updateToPremium(stmt, username);
        } catch (SQLException e){
            throw new RuntimeException();
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
            if(!rs.first()){
                throw new FailedLoginException("Username or Password not valid!");
            }
            rs.first();
            return rs.getBoolean("isPremium");

        } catch (SQLException | FailedLoginException e) {
            throw new RuntimeException(e);
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }

    public boolean checkIfUserExists(String username) throws SQLException {
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            ResultSet rs = Queries.checkIfUserExists(stmt, username);
            return rs.first();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
}
