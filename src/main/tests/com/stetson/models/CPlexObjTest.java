package com.stetson.models;

import com.stetson.controller.CPlexController;
import org.json.JSONObject;
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
            encoded = Files.readAllBytes(Paths.get("./src/main/tests/com/stetson/models/cplex_test/cplex_output.txt"));
            CPlexObj cPlexObj = new CPlexObj(new String(encoded, Charset.defaultCharset()));
            System.out.println("test:CPlexObjTest:parseCplex: Parsing successful -> "+cPlexObj.getParsedCplexData().toString());
            System.out.println(cPlexObj.convertToReadableFormat(CPlexObj.ConvertFormat.CSV));
        } catch (IOException e) {
            fail("test:CPlexObjTest:parseCplex: Could not parse cplex.");
            e.printStackTrace();
        }
    }

}