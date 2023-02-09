package com.cappellinispirito.ispw_project_202223_jfx.Model.dao;

import javax.security.auth.login.FailedLoginException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdditivesDAO {

    public String getAdditiveDangerousness(String additive) throws SQLException{
        Statement stmt = null;
        Connection conn;

        try{
            conn = DBConnector.getInstance().getConnection();
            stmt = conn.createStatement();
            ResultSet rs = Queries.getAdditiveDangerousness(stmt, additive);
            if(!rs.first()){
                throw new FailedLoginException("Username or Password not valid!");
            }
            rs.first();
            return rs.getString("Dangerousness");
        } catch (FailedLoginException e) {
            throw new RuntimeException(e);
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
}
