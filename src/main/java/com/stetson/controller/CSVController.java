package com.stetson.controller;

import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Converts between classes (e.g. ResultSets, or simple beans) to csv and reverse.
 */
@Controller
@RequestMapping("/csv")
public class CSVController {
    private static MessageDigest hashDigest;

    /** Exports a provided ResultSet from our sql db to a csv file.
     * @return exportFileName: To which file got the data exported. Might be null in case of failure.*/
    public static String exportResultSetToCSV(ResultSet rs) {
        String exportFileName = null;
        try {
            exportFileName = createRandomFileName();
            CSVWriter writer = new CSVWriter(new FileWriter("./src/main/webapp/files/csv/" + exportFileName+".csv"), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
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

    private static String createRandomFileName() {
        return UUID.randomUUID().toString();//new String(hashDigest.digest());
    }

    @RequestMapping("/{fileName}")
    public void downloadCsvResource(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable("fileName") String fileName) {

        fileName += ".csv";
        String dataDirectory = request.getSession().getServletContext().getRealPath("./files/csv/");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            response.setContentType("text/csv");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.err.println("CSVController:downloadCsvResource: Could not download csv file.");
                e.printStackTrace();
            }
        } else {
            System.err.println("CSVController:downloadCsvResource: File does not exist -> "+file.toString());
        }


    }
}
