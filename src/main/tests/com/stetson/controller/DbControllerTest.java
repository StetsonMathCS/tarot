package com.stetson.controller;

import com.stetson.controller.interfaces.IDbController;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class DbControllerTest {
    static DbController dbController;
    static IDbController.GotQueryResult dbCallback;

    @BeforeAll
    static void setUp() {
        dbController = new DbController();
        dbCallback = new IDbController.GotQueryResult() {
            @Override
            public void onSuccess(ResultSet rs) {
                System.out.println("Fetched resultSet -> " + rs.toString());

                //Thanks to https://stackoverflow.com/questions/9009422/how-do-i-iterate-through-the-values-of-a-row-from-a-result-set-in-java
                try {
                    final ResultSetMetaData meta = rs.getMetaData();
                    final int columnCount = meta.getColumnCount();
                    final List<List<String>> rowList = new LinkedList<List<String>>();
                    final List<String> columnList = new LinkedList<>();
                    while (rs.next()) {
                        rowList.add(columnList);

                        for (int column = 1; column <= columnCount; ++column) {
                            final Object value = rs.getObject(column);
                            columnList.add(String.valueOf(value));
                        }
                    }

                    for (String column : columnList) {
                        System.out.print("Column -> " + column + " \n");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    fail("SQL Exception catched, but test case failed.");
                }
            }

            @Override
            public void onFailure() {
                System.err.println("Could not execute query.");
            }
        };
    }

    @AfterAll
    static void tearDown() {
        dbController = null;
    }


    @Test
    void executeQueryAsync() throws ExecutionException, InterruptedException, SQLException, IOException {
        dbController.executeQueryAsync("SHOW DATABASES;", dbCallback);
    }
}