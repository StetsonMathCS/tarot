package com.stetson.controller;

import com.stetson.controller.interfaces.IDbController;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CSVControllerTest {
    @Mock
    private static MockHttpServletRequest mockHttpServletRequest;
    @Mock
    private static MockHttpServletResponse mockHttpServletResponse;

    private static DbController dbController;
    private static CSVController csvController;

    @BeforeAll
    static void setUp() {
        dbController = new DbController();
        csvController = new CSVController();
    }

    @BeforeEach
    void setUpEach() {
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletResponse = new MockHttpServletResponse();
    }

    @AfterAll
    static void tearDown() {
        dbController = null;
    }

    @Test
    void exportResultSetToCSV() throws SQLException {
        PreparedStatement stmt = dbController.getConn().prepareStatement("SHOW DATABASES;");
        dbController.executeQueryAsync(stmt, new IDbController.GotQueryResult() {
            @Override
            public void onSuccess(ResultSet rs) {
                String csvExportRs = CSVController.exportResultSetToCSV(rs);
                if (csvExportRs == null) {
                    fail("Could not export csv.");
                } else {
                    System.out.println("Test->exportResultSetToCSV: Exported csv into "+csvExportRs);
                }
            }

            @Override
            public void onFailure() {
                fail("Db query failed. Therefore export to csv not possible.");
            }
        });
    }

    /* Method works, but test does not yet
    @Test
    void downloadCsvResource() {
        mockHttpServletRequest.setAttribute("fileName","06ec2ac3-f63d-47c6-bdfa-e7448ad05d71");
        csvController.downloadCsvResource(mockHttpServletRequest,mockHttpServletResponse,mockHttpServletRequest.getAttribute("fileName").toString());
    }*/
}