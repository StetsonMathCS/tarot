package com.stetson.models;

import com.stetson.controller.DbController;
import com.stetson.exceptions.MappingException;

import java.util.ArrayList;
import java.util.HashMap;

/** ArrayList = Cols without col_names / String row-Id
 * String = Row, Primary Key
 * ArrayList<String> = Values of all columns (exkl. primary key).
 * */
public class RowMap extends HashMap<String, ArrayList<String>> {
    private static final DbController dbController = new DbController();
    /** SQL-Table name necessary to save/reload data from database. */
    private String tableName;

    public RowMap(String tableName) throws MappingException {
        this.setTableName(tableName);
        this.loadFromDb();
    }

    /** (Re)loads rowMap from Database and discards current version. */
    public void loadFromDb() throws MappingException {

    }

    /** Save (modified) rowMap into Db. Value gets overwritten. */
    public void saveToDb() throws MappingException {

    }

    //GETTER/SETTER +++++++++++++++++++++++++++++++++++++++++++++++++
    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
