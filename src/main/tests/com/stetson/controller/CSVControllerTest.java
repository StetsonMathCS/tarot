package com.stetson.controller;

import com.stetson.controller.interfaces.IDbController;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 15.10.2018
 * Time: 17:18
 */
class CSVControllerTest {
    private static DbController dbController;

    @BeforeAll
    static void setUp() {
        dbController = new DbController();
    }

    @AfterAll
    static void tearDown() {
        dbController = null;
    }

    @Test
    void exportResultSetToCSV() {
        dbController.executeQueryAsync("SHOW DATABASES;", new IDbController.GotQueryResult() {
            @Override
            public void onSuccess(ResultSet rs) {
                if (CSVController.exportResultSetToCSV(rs) == null) {
                    fail("Could not export csv.");
                }
            }

            @Override
            public void onFailure() {
                fail("Db query failed. Therefore export to csv not possible.");
            }
        });
    }
}