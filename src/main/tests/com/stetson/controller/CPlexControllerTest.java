package com.stetson.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CPlexControllerTest {
    @Test
    public void parseCPlexData() {
        try {
            CPlexController.parseCplexData("./src/main/tests/com/stetson/controller/json_test/test.json");
        } catch (IOException e) {
            e.printStackTrace();
            fail("Parsing failed.");
        }
    }

}