package com.stetson.models;

import com.stetson.exceptions.MappingException;

import java.sql.ResultSetMetaData;
import java.util.HashMap;

/** Used in DbRowMap */
public class DbVal {
    public enum DB_TYPE {
        BIT(-7), TINYINT(-6), BIGINT(-5),
        LONGVARBINARY(-4),VARBINARY(-3),
        BINARY(-2),LONGVARCHAR(-1),NULL(0),
        CHAR(1),NUMERIC(2),DECIMAL(3),INTEGER(4),
        SMALLINT(5),FLOAT(6),REAL(7),DOUBLE(8),VARCHAR(12),
        DATE(91),TIME(92),TIMESTAMP(93),OTHER(1111);

        int type;

        DB_TYPE(int type) {
            this.type = type;
        }
    }

    private DB_TYPE dbType;
    private String unparsedStr;

    public DbVal(String unparsedStr) {
        //this.setDbType(type);
        this.setUnparsedStr(unparsedStr);
    }

    @Override
    public String toString() {
        return this.getUnparsedStr();
    }

    //GETTER/SETTER
    /*public DB_TYPE getDbType() {
        return dbType;
    }

    public void setDbType(DB_TYPE dbType) {
        this.dbType = dbType;
    }*/

    public String getUnparsedStr() {
        return unparsedStr;
    }

    public void setUnparsedStr(String unparsedStr) {
        this.unparsedStr = unparsedStr;
    }
}
