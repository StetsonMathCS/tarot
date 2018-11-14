package com.stetson.models;

import com.stetson.controller.DbController;
import com.stetson.controller.interfaces.IDbController;
import com.stetson.exceptions.MappingException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/** ArrayList = Cols without col_names / String row-Id
 * String = Row, Primary Key
 * ArrayList<DbVal> = Values of all columns (incl. primary key)!.
 * */
public class DbRowMap extends HashMap<String, ArrayList<DbVal>> {



    private static final DbController dbController = new DbController();
    /** SQL-Table name necessary to save/reload data from database. */
    private String tableName;

    public DbRowMap(String tableName) throws MappingException {
        this.setTableName(tableName);
        this.loadFromDb(new IDbController.GotQueryResult() {
            @Override
            public void onSuccess(ResultSet rs) {
                System.out.println("DbRowMap:constr: DbRowMap successfully created.");
            }

            @Override
            public void onFailure() {
                System.err.println("DbRowMap:contstr: Data couldn't be loaded or mapped into DbRowMap.");
                throw new MappingException("DbRowMap:constr: Loading and/or mapping to DbRowMap FAILED.");
            }
        });
    }
    
    private void mapResultsetToRowMap(ResultSet rs) {
        try {
            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                //Craft row with all column values
                ArrayList<DbVal> valuesOfRow = new ArrayList<>();
                //start from 2 to skip on primary key
                for (int column = 2; column <= meta.getColumnCount(); ++column) {
                    valuesOfRow.add(new DbVal(String.valueOf(rs.getObject(column))));
                }

                // Save to current instance
                this.put(String.valueOf(rs.getObject(1)),valuesOfRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MappingException("DbRowMap:loadFromDb: Could not load data from database.");
        }
    }

    /** maps instance to sql executable value-list which will be inserted into REPLACE table INTO VALUES(HERE);*/
    private String mapRowMapToValueList() {
        StringBuilder valueList = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, ArrayList<DbVal>> row : this.entrySet()) {
            if (i++ > 0) {
                valueList.append(",");
            }

            valueList.append("(");

            // add primary key first
            valueList.append("'");
            valueList.append(row.getKey());
            valueList.append("'");
            int colCount = 0;
            for (DbVal colVal : row.getValue()) {
                //as primary key is first (= key)
                valueList.append(",");

                //is colVal a string or integer or whatever
                valueList.append("'"); //just do this as mysql should accept it no matter if number or not.
                valueList.append(colVal.getUnparsedStr());
                valueList.append("'");
            }
            valueList.append(")");
        }
        return valueList.toString();
    }

    /** (Re)loads rowMap from Database and discards current version. */
    public void loadFromDb(final IDbController.GotQueryResult gotQueryResult) throws MappingException {
        DbRowMap.dbController.executeQueryAsync("SELECT * FROM "+this.getTableName()+";", new IDbController.GotQueryResult() {
            @Override
            public void onSuccess(ResultSet rs) {
                mapResultsetToRowMap(rs);
                gotQueryResult.onSuccess(rs);
            }

            @Override
            public void onFailure() {
                //one onFailure() is enough.
                System.err.println("DbRowMap:loadFromDb: DbRowMap couldn't be loaded with data from database.");
                gotQueryResult.onFailure();
            }
        });
    }

    /** Save (modified) rowMap into Db. Value gets overwritten. */
    public void saveToDb(final IDbController.GotQueryResult gotQueryResult) throws MappingException {

        DbRowMap.dbController.executeQueryAsync("REPLACE INTO "+this.getTableName()+" VALUES "+this.mapRowMapToValueList()+";", new IDbController.GotQueryResult() {
            @Override
            public void onSuccess(ResultSet rs) {
                //Database already updated with replace statement.

                System.out.println("DbRowMap:saveToDb: DbRowMap has been synced with database.");
                gotQueryResult.onSuccess(rs);
            }

            @Override
            public void onFailure() {
                System.err.println("DbRowMap:saveToDb: DbRowMap couldn't be saved to database.");
                gotQueryResult.onFailure();
            }
        });
    }

    private static String sqlInjection(String str) {
        if (str == null || str.matches(".*['*+~,;\"].*")) {
            throw new MappingException("DbRowMap:setTableName: Tablename consists illegal characters!");
        }
        return str;
    }

    //GETTER/SETTER +++++++++++++++++++++++++++++++++++++++++++++++++
    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) throws MappingException {
            this.tableName = sqlInjection(tableName);
    }
}
