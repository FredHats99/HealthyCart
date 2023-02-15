package com.cappellinispirito.ispwproject202223jfx.model.dao;

import com.cappellinispirito.ispwproject202223jfx.model.dao.conenctor.DBConnector;

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
            if(!rs.next()){
                throw new FailedLoginException("No additive found!");
            }
            return rs.getString("Dangerousness");
        } catch (RuntimeException | FailedLoginException ignored) {
            return "A";
        } finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
}
