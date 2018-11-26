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
 *
 * PeriodAssigned = 1;
 WeekdayAssigned = {"Mon" "Wed"};
 ProfessorAssigned = "Eckroth";
 RoomAssigned = "Room1";
 CourseAssigned = "Database Systems";
 totalPeriods = 5;
 totalStudents = 100;
 NbPeriods = 4;
 maxStudents = 25;
 Weekdays = {"Mon" "Wed" "Fri"};
 Professors = {"Eckroth" "Plante" "ElAarag" "Koc" "TBA"};
 Courses = {"Database Systems" "Web App" "Big Data" "Senior P1" "Senior P2" "Intro to Computing"
 "Intro to Comp1" "Intro to Comp2" "Discrete Structures" "Soft Dev1"
 "Operating Systems" "Computer Networks" "Computer Graphics" "Senior R1"
 "Senior R2"};
 Rooms = {"Room1" "Room2" "Room3" "Room4" "Room5" "empty" "Lab1" "Lab2"};
 ProfCourses = {<"Eckroth" {"DatabaseSystems" "BigData" "SoftDev1"}>
 <"Plante" {"WebApp" "SeniorP1" "SeniorP2" "IntrotoComp2" "SeniorR1"
 "SeniorR2"}> <"ElAarag" {"DiscreteStructures" "OperatingSystems"
 "ComputerNetworks"}> <"Koc" {"IntrotoComp1" "ComputerGraphics"}>
 <"TBA" {"IntrotoComputing" "IntrotoComp1"}>};

 CoursePrefs = [[1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
 [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
 [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
 [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
 [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]];
 // RANGE_INT Periods [1 ,5]
 numRooms = 8;
 */
public class CPlexObj {
    private static final String CPLEX_OUTPUT_REGEX_STR = "Assign = ([01\\[\\] \\n\\t\\r]*)";
    private static final Pattern CPLEX_PATTERN = Pattern.compile(CPLEX_OUTPUT_REGEX_STR);
    /** Input configuration for parsing parsed CPLex Data into human readable format */
    private static JSONArray INPUT;
    /** Output data parsed into JsonArray */
    private JSONArray parsedCplexData;
    public enum ConvertFormat {
        HTML, CSV
    }

    /** @param cplexOutput: Equals outputstream (no matter what) of the outputfile*/
    public CPlexObj(String cplexOutput) {
        initializeINPUT();

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

    public String convertToReadableFormat(ConvertFormat type) {
        StringBuilder sbCourseList = new StringBuilder();
        JSONArray parsedCplexData = this.getParsedCplexData();
        for (int i = 0; i<parsedCplexData.length(); i++) {
            // Monday, Wednesday, Friday
            JSONArray weekDayData = parsedCplexData.getJSONArray(i);
            for (int j = 0; j<weekDayData.length();j++) {
                // TeacherName:{List of courses (s)he does}
                JSONArray profCourseData = weekDayData.getJSONArray(j);
                for (int k = 0; k<profCourseData.length();k++) {
                    // room1, room2,...
                    JSONArray roomData = profCourseData.getJSONArray(k);
                    for (int l = 0;l<roomData.length();l++) {
                        // eckroth, plante, elaarag, koc, tba..
                        JSONArray professorData = roomData.getJSONArray(l);

                        for (int m = 0; m<professorData.length();m++) {
                            // course
                            JSONArray availableCourses = (JSONArray) INPUT.getJSONArray(1).get(j);
                            if (professorData.get(m).equals(1)) {
                                for (int n = 0; n < availableCourses.length(); n++) {
                                    sbCourseList.append(INPUT.getJSONArray(0).get(i));
                                    sbCourseList.append(";");
                                    sbCourseList.append(availableCourses.get(n));
                                    sbCourseList.append(";");
                                    sbCourseList.append(INPUT.getJSONArray(2).get(k));
                                    sbCourseList.append(";");
                                    sbCourseList.append(INPUT.getJSONArray(3).get(l));
                                    sbCourseList.append(";");
                                    sbCourseList.append(INPUT.getJSONArray(4).get(m));
                                    sbCourseList.append("\n");
                                }
                            }
                        }
                    }
                }
            }
        }
        return sbCourseList.toString();
    }

    private void initializeINPUT() {
        if (INPUT == null) {
            INPUT = new JSONArray();
            INPUT.put(0,new JSONArray("[\"Monday\",\"Wednesday\",\"Friday\"]"));
            // Eckroth, Plante, ElAarag, Koc, Tba (in that order)
            INPUT.put(1,new JSONArray("[[" +
                    "\"DatabaseSystems\"," +
                    "\"BigData\"," +
                    "\"SoftDev1\"" +
                    "]," +
                    "[" +
                    "\"WebApp\"," +
                    "\"SeniorP1\"," +
                    "\"SeniorP2\"," +
                    "\"IntrotoComp2\"," +
                    "\"SeniorR1\"," +
                    "\"SeniorR2\"" +
                    "]," +
                    "[" +
                    "\"DiscreteStructures\"," +
                    "\"OperatingSystems\"," +
                    "\"ComputerNetworks\"" +
                    "]," +
                    "[" +
                    "\"IntrotoComp1\"," +
                    "\"ComputerGraphics\"" +
                    "]," +
                    "[" +
                    "\"IntrotoComputing\"," +
                    "\"IntrotoComp1\"" +
                    "]" +
                    "]"));
            INPUT.put(2, new JSONArray("[\"Room1\", \"Room2\", \"Room3\", \"Room4\", \"Room5\", \"empty\", \"Lab1\", \"Lab2\"]"));
            INPUT.put(3, new JSONArray("[\"Eckroth\", \"Plante\", \"ElAarag\", \"Koc\", \"TBA\"]"));
            INPUT.put(4, new JSONArray("[\"Database Systems\", \"Web App\", \"Big Data\", \"Senior P1\", \"Senior P2\", \"Intro to Computing\", \"Intro to Comp1\", \"Intro to Comp2\", \"Discrete Structures\", \"Soft Dev1\", \"Operating Systems\", \"Computer Networks\", \"Computer Graphics\", \"Senior R1\", \"Senior R2\"]"));
        } else {
            System.out.println("CplexObj:initializeINPUT: Input already initialized.");
        }
    }



    //GETTER/SETTER +++++++++++++++++++++++++++++++++
    public JSONArray getParsedCplexData() {
        return parsedCplexData;
    }

    public void setParsedCplexData(JSONArray parsedCplexData) {
        this.parsedCplexData = parsedCplexData;
    }
}
