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
    private static CPlexController cPlexController = new CPlexController();

    @Test
    public void parseCplex() {
        byte[] encoded;
        try {
            encoded = Files.readAllBytes(Paths.get("./src/main/tests/com/stetson/models/cplex_test/cplex_output.txt"));
            CPlexObj cPlexObj = new CPlexObj(new String(encoded, Charset.defaultCharset()));
            System.out.println("test:CPlexObjTest:parseCplex: Parsing successful -> "+cPlexObj.getParsedCplexData().toString());
        } catch (IOException e) {
            fail("test:CPlexObjTest:parseCplex: Could not parse cplex.");
            e.printStackTrace();
        }
    }

    @Test
    public void parseCplexJson() {
        try {

        }
    }

}