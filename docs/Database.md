# Documentation

## Software needed:

* mySQL 
* DBI (sudo apt-get install libclass-dbi-mysql-perl)
* Eclipse Java 8

## To install:
* MYSQL:
  * Visit the website https://dev.mysql.com/downloads/mysql/5.7.html#downloads to download the free version of the world’s most popular open source database.
 * Download the application, run the installer as administrator, which will guide through the process and setup MySQL.
 
* Eclipse Java 8
  * Visit the website https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2018-09/Ra/eclipse-inst-win64.exe to download the free version.
  * Download the application, run the installer as administrator, which will guide through the process and setup Eclipse Java 8.

## Milestones:
* Normalization
  * Insuring that all the tables follow normalization.

* Adding
  * MYSQL -> “insert into _”
  
* Changing
  * MYSQL -> “alter table ___ …”

* Removing
  * MYSQL -> “delete from ___ where …”

* Filtering
  * MYSQL -> “select ___ from ___ where…”

* Searching
  * MYSQL -> “select * from _, _ where…”

* Hard Constraints
  * Ensuring that ID’s from professors, rooms, and time tables exist correctly in classes table.

* Soft Constraints
  * Checking that there is only one ID per class/time/room/professor during upload into DB.

* Save generated schedule into database
  * MYSQL -> “create view _ as select * from _”

* Creating a join query
  * MYSQL -> “select __ from __ inner join __ on _...”

* Creating a join query to show what is in each classroom
  * MYSQL -> “select classes.roomID, classes.className from classes inner join rooms on classes.roomID = rooms.ID”

* Creating a join query to show what each professor is doing
  * MYSQL -> “select classes.profID, professors.name from classes inner join professors on classes.profID = professors.ID”
  
* Install MySQL

* Import csv file to database

* Export database table to csv file

* MySQL injection flaws

* Create user account in MySQL

* Measure how long it took to run a query
  * MYSQL -> SHOW PROFILE FOR QUERY name;

* Created a diagram in MySQL Workbench showing all the tables and the links

* How to backup database and restore a backup

## Functions
* The perl code that Eckroth helped me develop is called “parseCourseLookup.pl” and “courseLookup.sh”
  * It is run with “bash courseLookup.sh”
  
## How to run the program: 
* To show the table imported from the csv file to the database in MySQL
  * Log into the database using the MySQL command line client
  * Issue the use command to connect to the database 
  * To show the table imported from csv file to the database 

* To run Java program
  * In order to run the program and access to the database, the user need to create a text file in Java called “input.txt” with the password and username.
  
  ![alt text](https://github.com/StetsonMathCS/tarot/blob/master/docs/img/input.png)

## How does the importation and exportation works in Java:

* The Java program will get the password and username from the textfile “input” to connect to the database table_schedule.
* To import the csv file to database, first it would find the csv file in the computer, then it reads the file from the csv file and create a table table_schedule in the database using the following command lines.
* After creating the table we insert the information into the table table_schedule using the following command lines.
* To export the database table to csv file, first we read the information from the table and insert the information into a csv file and save it in a temporary file.
