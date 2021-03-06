package com.stetson.models;

import com.stetson.controller.CSVController;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CPlexObjTest {
    @Test
    public void parseCplex() {

        byte[] encoded;
        try {
            encoded = Files.readAllBytes(Paths.get("./src/main/tests/com/stetson/models/cplex_test/cplex_output_2.txt"));
            CPlexObj cPlexObj = new CPlexObj(new String(encoded, Charset.defaultCharset()));
            System.out.println("test:CPlexObjTest:parseCplex: Parsing successful -> "+cPlexObj.getParsedCplexData().toString());

            //Save parsed csv to file
            CSVController.exportCSVStringToCSV(cPlexObj.convertToReadableFormat());

        } catch (IOException e) {
            fail("test:CPlexObjTest:parseCplex: Could not parse cplex.");
            e.printStackTrace();
        }
    }

}