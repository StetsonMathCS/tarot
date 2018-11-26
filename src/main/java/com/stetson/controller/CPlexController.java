package com.stetson.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CPlexController {

    public static JSONObject parseJsonData(String file) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(file));
        return new JSONObject(new String(encoded, Charset.defaultCharset()));
    }


}