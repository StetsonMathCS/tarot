package com.stetson.controller.interfaces;

import java.sql.ResultSet;

public interface IDbController {
    String DB_CONNECTION_STRING = "jdbc:mysql://localhost/tarot?user=root&password=";

    /** Do sth. with the result. ResultSet might be null in case we executed just a statement.*
     * Working now with asyncresults!*/
    interface GotQueryResult {
        void onSuccess(ResultSet rs);
        void onFailure();
    }
}
