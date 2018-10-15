package com.stetson.controller;

import com.stetson.controller.interfaces.IDbController;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.*;
import java.util.concurrent.Future;
// DO NOT IMPORT com.mysql.jdbc.* (broken implementations)

/**
 * Manages db connections.
 */
@EnableAsync
public class DbController {
    private Connection conn = null;


    public DbController() {
        loadDriver();
        connect();
    }

    private void loadDriver() {
        try {
            //new instance() call is work around for some broken java implementations.
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.err.println("DbController:loadDriver: Could not load db/mysql driver.");
            e.printStackTrace();
        }
    }

    private void connect() {
        try {
            this.conn = DriverManager.getConnection(IDbController.DB_CONNECTION_STRING);
        } catch (SQLException e) {
            System.err.println("SQLException: "+e.getMessage()+
                    "\nSQLState: "+e.getSQLState()+
                    "\nVendorError: "+e.getErrorCode());
            e.printStackTrace();
        }
    }

    //TODO: Need mapping method which receives ResultSet and returns e.g. mappedObject.
    @Async
    public void executeQueryAsync(String query, IDbController.GotQueryResult callback) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.conn.createStatement();

            //as we don't know if we have a query or sth else
            if (stmt.execute(query)) {
                rs = stmt.getResultSet();
            }
            callback.onSuccess(rs); //might be NULL!
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
            callback.onFailure();
        } finally {
            //release all resources the other way round we initialized it.
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            } //not else

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stmt = null;
            }
        }
    }


}
