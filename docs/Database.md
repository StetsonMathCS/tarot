#Documentation

##Software needed:

* mySQL 
* DBI (sudo apt-get install libclass-dbi-mysql-perl)

##Milestones:
* Normalization
  * Insuring that all the tables follow normalization.

* Adding
  * MYSQL -> “insert into ___”
  
* Changing
  * MYSQL -> “alter table ___ …”

* Removing
  * MYSQL -> “delete from ___ where …”

* Filtering
  * MYSQL -> “select ___ from ___ where…”

* Searching
  * MYSQL -> “select * from ___, ___ where…”

* Hard Constraints
  * Ensuring that ID’s from professors, rooms, and time tables exist correctly in classes table.

* Soft Constraints
  * Checking that there is only one ID per class/time/room/professor during upload into DB.

* Save generated schedule into database
  * MYSQL -> “create view ____ as select * from ____”

* Creating a join query
  * MYSQL -> “select ____ from ____ inner join ___ on ____...”

* Creating a join query to show what is in each classroom
  * MYSQL -> “select classes.roomID, classes.className from classes inner join rooms on classes.roomID = rooms.ID”

* Creating a join query to show what each professor is doing
  * MYSQL -> “select classes.profID, professors.name from classes inner join professors on classes.profID = professors.ID”

##Functions
* The perl code that Eckroth helped me develop is called “parseCourseLookup.pl” and “courseLookup.sh”
  * It is run with “bash courseLookup.sh”







































