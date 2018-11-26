package com.stetson.models;

import com.stetson.controller.interfaces.IDbController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DbRowMapTest {
    private DbRowMap dbRowMap;

    @BeforeEach
    void setupEach() throws SQLException {
        dbRowMap = new DbRowMap("time");
    }

    @Test
    void loadFromDb() throws SQLException {
        dbRowMap.loadFromDb(new IDbController.GotQueryResult() {
            @Override
            public void onSuccess(ResultSet rs) {
                System.out.println("test:loadFromDb: Success!");
                for (Map.Entry<String, ArrayList<DbVal>> row : dbRowMap.entrySet()) {
                    StringBuilder sb = new StringBuilder();
                    for (DbVal colVal : row.getValue()) {
                        sb.append(";");
                        sb.append(colVal.getUnparsedStr());
                    }
                    System.out.println("test:loadFromDb: "+row.getKey()+";;"+sb.toString());
                }
            }

            @Override
            public void onFailure() {
                fail("loadFromDb: Could not load and map data from db.");
            }
        });
    }

    @Test
    void saveToDb() throws SQLException {
        // UNCOMMEND, if you want to change dbRowMap before saving (MODIFIES REAL DATABASE!)
        ArrayList<DbVal> list = new ArrayList<>();
        //list.add(new DbVal("9999999999"));
        list.add(new DbVal("test_days"));
        list.add(new DbVal("test_time"));
        dbRowMap.put("999999999",list);

        dbRowMap.saveToDb(new IDbController.GotQueryResult() {
            @Override
            public void onSuccess(ResultSet rs) {
                System.out.println("test:saveToDb: Successfully updated/synced dbRowMap with db.");
            }

            @Override
            public void onFailure() {
                fail("test:saveToDb: Could not save values into db.");
            }
        });

    }
}