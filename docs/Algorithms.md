Our program generates university time tables with IBM ILOG CPLEX optimization software.

### To install:
  * For Windows
    * Visit https://www.ibm.com/products/ilog-cplex-optimization-studio Where you can either sign up for a trial version, purchase the program, or sign up for an academic license which provides you full access to CPLEX in a non-commercial setting
    * Download, run the installer as administrator, which will guide you through the process and setup the CPLEX Optimization Studio
    * Default install location is C:\Program Files\IBM\ILOG\CPLEX_Studio[edition]1261 or Program Files(x86) if installed 32-bit version on 64-bit machine
      * See [here](https://www.ibm.com/support/knowledgecenter/SSSA5P_12.6.2/ilog.odms.studio.help/pdf/gsoplide.pdf?origURL=SSSA5P_12.6.2/ilog.odms.studio.help/Optimization_Studio/topics/PLUGINS_ROOT/ilog.odms.studio.help/pdf/gsoplide.pdf) for more information on the Optimation Studio IDE
  * For Unix/linux systems/servers
    * Visit same [website](https://www.ibm.com/products/ilog-cplex-optimization-studio) for download
     * The linux/unix installer comes as an executable .bin file, located within the installation folder, default location /opt/IBM/ILOG/CPLEX_Studio[edition]1261. 
     * Run the executable on your system's commandline with the syntax __./(installname).bin__ make sure the file has execute permissions or installation will fail
      * Launch CPLEX using __./cplex__ located in /opt/IBM/ILOG/CPLEX_Studio[edition]1261/cplex/bin/x86-64_linux/


### How to run the program:

  * On the server:
    * Go to the following directory: /opt/ibm/ILOG/CPLEX_Studio128/opl/bin/x86-64_linux 
    * Then run the following line: LD_LIBRARY_PATH=./ ./oplrun tarot/Tarot.mod tarot/Tarot.dat
    * The output.txt file will be generated/updated with the results in tarot folder


  * On Windows/Mac:
    * run IBM ILOG CPLEX Optimization Studio (oplide)
    * Import the files for the program ex. Tarot.dat and Tarot.mod
    * Add them to Run Configuration – Basic Configuration to be able to run them
    * Click run in the top :)



### How does the program work:
The program uses two files, Tarot.dat and Tarot.mod

* Tarot.dat contains all the data of the program 

    * totalPeriods: is the total number of periods for a day
    * numRooms: is the total number of classrooms available
    * Weekdays: are the days that will be used for scheduling
    * Professors: set of strings that contains the professors
    * Courses: set of strings that contains the courses
    * Rooms: set of strings that contains the classrooms
    * HardSign: list of the classes that must be assigned at a specific time before scheduling other classes
      - <int Period number, {set of strings days assigned}, string professor’s name, string name of the room, string name of the course>
      - Ex. <1,{Mon, Wed, Fri},"Eckroth","Room1","Database Systems">
    * CoursePrefs: Two dimensional array (columns are courses, rows are professors) that contains a float representing a weighting of courses preferences for each professor 
    * PeriodPrefs: Two dimensional array (columns are courses, rows are periods) that contains a float representing a weighting of courses preferences for each period
    * LongCourses: list of the courses and which days they should be taught depending if a course is a regular or a long course
      - <string name of the course, {set of strings days assigned}> 
      - <"Database Systems", {Mon, Wed, Fri}>


* Tarot.mod is where all the constraints and features that runs the program

    *  Allow the user to set multiple specific timeslots for different professors in different specific classrooms (has to be at this time and this classroom) (HardSign)
       - The user can set the period, course, classroom and a professor to the HardSign variable in Tarot.dat file
    *  Assign each professor to teach a course
       - Each professor has specific courses to teach. If a course needs to be taught, the user could change the preference of the course in CoursesPrefs for a professor, or multiple professors, and the program should be able to assign the best choice between professors to teach that course.
    *  Allow the user to set more than one section for a course
       - By adding the same course more than once to the courses list and LongCourses.
    *  Set 2-day courses to appropriate time-slots
       - Long period courses (75 mins) are only taught twice a week (Mondays and Wednesdays) and they are not taught on Fridays. Long period courses are scheduled on period 5 during a day.
    *  Allow the user to add courses that may or may not be taught in this semester (taught if can be taught, such as elective course)
       - Courses could be added, but not taught depending their preferences in CoursesPrefs
    *  Allow the user to rearrange the classes as preferred after solving the problem
       - The user can run the program and after getting the results, they can add what they like to HardSign (with their time, period, etc.) and run the program again to get new results after reassigning the courses they like.
    *  Allow the user to choose multiple timeslots preferred for a course
       - The user can prefer periods for courses in PeriodPrefs
    *  Set each course/section to a different period during a day
       - A course should not be taught twice at the same time during a day.
    *  The course is taught at the same timeslot in different days (e.g. MWF from 11:00am – 11:50am) and same classroom
       - A course is scheduled at the same time for different days (Friday is different because long courses are not taught on Fridays)
