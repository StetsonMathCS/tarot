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
import java.io.*;
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

    /**
     * Exports a provided ResultSet from our sql db to a csv file.
     *
     * @return exportFileName: To which file got the data exported. Might be null in case of failure.
     */
    public static String exportResultSetToCSV(ResultSet rs) {
        String exportFileName = null;
        try {
            exportFileName = createRandomFileName();
            CSVWriter writer = new CSVWriter(new FileWriter("./src/main/webapp/files/csv/db/" + exportFileName + ".csv"), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(rs, true);
            writer.close();
            return exportFileName;
        } catch (SQLException | IOException e) {
            System.err.println("CSVController:exportResultSetToCSV: Export failed.");
            e.printStackTrace();
            System.err.println("EXPORT_FAILED -> aimedFileName: " + exportFileName);
            return null; //maybe to provoke nullPointers.
        }
    }

    public static String exportCSVStringToCSV(String csvString) {
        String exportFileName = null;
        try {
            exportFileName = createRandomFileName();
            FileWriter fileWriter = new FileWriter("./src/main/webapp/files/csv/cplex/" + exportFileName + ".csv");
            fileWriter.write(csvString);
            fileWriter.flush();
            fileWriter.close();

            return exportFileName;
        } catch (IOException e) {
            System.err.println("CSVController:exportCSVStringToCSV: Export failed.");
            e.printStackTrace();
            System.err.println("EXPORT_FAILED -> aimedFileName: " + exportFileName);
            return null; //maybe to provoke nullPointers.
        }
    }

    private static String createRandomFileName() {
        return UUID.randomUUID().toString();//new String(hashDigest.digest());
    }

    // when accessing use /csv before as whole controller here appends csv to url.
    @RequestMapping(value = "/download/{source}/{filename}")
    public void downloadResource(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @PathVariable("filename") String fileName,
                                 @PathVariable("source") String source) {

        if (fileName == null || !fileName.matches("[a-zA-Z0-9-]*")) {
            try {
                String msg = "Forbidden filename requested! Filename can only contain letters, numbers and a dash.";
                System.err.println("CSVController:downloadResource: " + msg);
                response.setContentType("text/plain");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(msg);
                response.getWriter().flush();
                response.getWriter().close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        fileName += ".csv";
        String dataDirectory = request.getSession().getServletContext().getRealPath("./files/csv/" + source + "/");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            response.setContentType("text/csv");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.err.println("CSVController:downloadCsvResource: Could not download csv file.");
                e.printStackTrace();
            }
        } else {
            System.err.println("CSVController:downloadCsvResource: File does not exist -> " + file.toString());
        }


    }
}
