package com.stetson.controller;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Converts between classes (e.g. ResultSets, or simple beans) to csv and reverse.
 */
public class CSVController {
    private static MessageDigest hashDigest;

    /** Exports a provided ResultSet from our sql db to a csv file.
     * @return exportFileName: To which file got the data exported. Might be null in case of failure.*/
    public static String exportResultSetToCSV(ResultSet rs) {
        String exportFileName = null;
        try {
            exportFileName = createRandomFileName();
            CSVWriter writer = new CSVWriter(new FileWriter("./exportedFiles/csv/"+exportFileName+".csv"));
            writer.writeAll(rs, true);
            writer.close();
            return exportFileName;
        } catch (SQLException | IOException e) {
            System.err.println("CSVController:exportResultSetToCSV: Export failed.");
            e.printStackTrace();
            System.err.println("EXPORT_FAILED -> aimedFileName: "+exportFileName);
            return null; //maybe to provoke nullPointers.
        }
    }

    public static String createRandomFileName() {
        return UUID.randomUUID().toString();//new String(hashDigest.digest());


    }
}
