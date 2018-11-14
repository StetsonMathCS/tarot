package com.stetson.models;

import com.stetson.exceptions.CPLEXException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsed Cplex output
 */
public class CPlexObj {
    private static final String CPLEX_OUTPUT_REGEX_STR = "Assign = ([01\\[\\] \\n\\t\\r]*)";
    private static final Pattern CPLEX_PATTERN = Pattern.compile(CPLEX_OUTPUT_REGEX_STR);
    private JSONArray parsedCplexData;

    /** @param cplexOutput: Equals outputstream (no matter what) of the outputfile*/
    public CPlexObj(String cplexOutput) {
        Matcher matcher = CPLEX_PATTERN.matcher(cplexOutput);
        if (!matcher.find()) {
            throw new CPLEXException("CPlexObj:constr: Cplex output does not match requirements! -> "+CPLEX_OUTPUT_REGEX_STR);
        } else {
            //Format cplex output (remove whitespaces, add commas etc. to transform it to a json.
            String binaryArray = matcher.group(1)
                    .replaceAll("[ ]{2,}","")
                    .replaceAll("\\][ \\n\\r\\t]*\\[","],[")
                    .replace(' ',',');

            this.setParsedCplexData(new JSONArray(binaryArray));
        }
    }

    public JSONArray getParsedCplexData() {
        return parsedCplexData;
    }

    public void setParsedCplexData(JSONArray parsedCplexData) {
        this.parsedCplexData = parsedCplexData;
    }
}
