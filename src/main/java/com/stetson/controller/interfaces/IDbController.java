package com.stetson.controller.interfaces;

import com.sun.istack.internal.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDbController {
    String DB_CONNECTION_STRING = "jdbc:mysql://localhost/tarot?user=root&password=";

    /** Do sth. with the result. ResultSet might be null in case we executed just a statement.*/
    interface GotQueryResult {
        void onSuccess(@Nullable ResultSet rs);
        void onFailure();
    }
}
