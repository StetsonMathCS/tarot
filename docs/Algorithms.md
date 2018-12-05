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
Tarot.dat contains all the data of the program 
totalPeriods: is the total number of periods for a day
numRooms: is the total number of classrooms available
Weekdays: are the days that will be used for scheduling
Professors: set of strings that contains the professors
Courses: set of strings that contains the courses
Rooms: set of strings that contains the classrooms

HardSign: list of the classes that must be assigned at a specific time before scheduling other classes
<int Period number, {set of strings days assigned}, string professor’s name, string name of the room, string name of the course>
Ex. <1,{Mon, Wed, Fri},"Eckroth","Room1","Database Systems">

CoursePrefs: Two dimensional array (columns are courses, rows are professors) that contains a float representing a weighting of preferences  
PeriodPrefs: 

