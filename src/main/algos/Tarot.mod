
  
//**************************** Data **************************************
int totalPeriods = ...; // Total # of Periods in a day
int NbPeriods = ...;    // Total # Periods a person should have class in a day
int totalStudents = ...; // Total # of students
int maxStudents = ...; // Max # of students per class
int numRooms = 8;

range Periods = 1..totalPeriods; 
range Students = 1..totalStudents;
{string} Professors = ...;     // Set of Professors
{string} Weekdays = ...;   // Set of work days 
{string} Rooms = ...;     // Set of Rooms
{string} Courses = ...;    // Set of Courses
float CoursePrefs[j in Professors][c in Courses] = ...;
float PeriodPrefs[p in Periods][c in Courses] = ...;


// Data Structure
tuple LCourse {
	string LC;
  {string} LDay;

}


tuple Sign
{
int PA;
{string} WA;
string PrA;
string RA;
string CA;
}

{Sign} HardSign = ...;
{LCourse} LongCourses = ...; //  List of Professors for each course
int Penalty = card(Weekdays)*NbPeriods+1; // Penalty for an unfilled slot
//********************************* Variables **********************************
        
dvar boolean Assign[Weekdays][Periods][Rooms][Professors][Courses];   // Indicates a course assignment
dvar boolean Q[Students][Courses][Weekdays][Periods];
dvar boolean I[Students][Weekdays][Periods];
dvar boolean J[Courses][Weekdays][Periods];
dvar int Unfilled[Courses][Weekdays][Periods][Rooms] in 0..25;  // # of empty seats in given room on day/time
dvar int Pmin           in 0..card(Weekdays)*NbPeriods;     // Minimal # of Periods assigned 
dvar int Pmax           in 0..card(Weekdays)*NbPeriods;     // Maximal # of Periods assigned
dvar boolean W[Students][Courses];
dvar boolean R[Courses][Rooms];
dvar boolean Enroll [Students][Courses][Periods];		//student has course at period
dvar boolean Occupy [Courses][Rooms][Periods];		//Course in room at period
dvar boolean X[Professors][Courses];
dvar boolean Y[Courses][Periods];
dvar boolean Avail[Weekdays][Periods][Rooms][Courses];     // Indicates the availability of a room
dvar boolean P[Periods];
// the total # of slots unfilled in a week
dexpr int TotUnfilled  =
  sum(c in Courses,w in Weekdays,p in Periods, r in Rooms) Unfilled[c][w][p][r];
/************************************* Model *********************************/

//minimize TotUnfilled*Penalty + (Pmax-Pmin);
//minimize sum (p in Periods) P[p];

//maximize sum (c in Courses, p in Periods, r in Rooms) Occupy[c][r][p];
maximize sum(j in Professors, c in Courses, r in Rooms, w in Weekdays, p in Periods) Assign[w,p,r,j,c]*(CoursePrefs[j,c])*PeriodPrefs[p,c];


subject to {
// To assign a professor to teach a course in a specific time and room
	forall(h in HardSign)
		forall (w in h.WA) Assign[w][h.PA][h.RA][h.PrA][h.CA] ==1;
	  
   forall(w in Weekdays, p in Periods, r in Rooms, c in Courses)  //only 1 professor per room/period 
       sum(j in Professors)
        Assign[w][p][r][j][c] <= 1;
//number of instruction-units assigned to an instructor must not exceed the limit
/* forall(j in Professors) 
	sum(c in Courses) X[j, c] <= NbPeriods;
// each instruction-units must be assigned to exactly 1 instructor
forall(c in Courses) 
	sum(j in Professors) X[j, c] == 1;
	*/
	
	
	
	
	  
	forall(w in Weekdays, p in Periods, r in Rooms)
   sum(j in Professors, c in Courses) Assign[w][p][r][j][c] <= 1;
//One prof/course combo per timeslot
  forall (c in LongCourses)
    forall (w in c.LDay)
    	sum (p in Periods, j in Professors, r in Rooms) Assign[w][p][r][j][c.LC] == 1;
  
  forall (w in Weekdays, c in Courses, j in Professors, r in Rooms)
    sum (p in Periods) Assign[w][p][r][j][c]<=1;

  forall (w in Weekdays, p in Periods, r in Rooms)
    sum (c in Courses) Avail[w][p][r][c] <= 1;
  
  //forall (c in Courses, j in Professors)
   //  sum (w in Weekdays, r in Rooms) Assign[w][5][r][j][c] <= 1;
     
  forall (w in Weekdays, p in Periods, r in Rooms, c in Courses)
    sum (j in Professors) Assign[w][p][r][j][c] <= Avail[w][p][r][c];
    
 forall(j in Professors, w in Weekdays, p in Periods)
   	sum (c in Courses, r in Rooms) Assign[w][p][r][j][c] <= 1;
   	
   	 forall (c in Courses, p in 1..5, r in Rooms, j in Professors)
	    Assign["Mon"][p][r][j][c] == Assign["Wed"][p][r][j][c];
	    
	   forall (c in Courses, p in 1..4, r in Rooms, j in Professors) 
	    Assign["Fri"][p][r][j][c] == Assign["Mon"][p][r][j][c];
	    
	    forall(c in Courses, r in Rooms, j in Professors)
	  Assign["Fri"][5][r][j][c] == 0;
/*
forall(c in Courses) 
	sum(r in Rooms) R[c, r] == 1;
	
forall(c in Courses, r in Rooms)
	sum(p in Periods) Y[c,p] == 1;
	
//each lecture is assigned to exactly one time-slot
forall(c in Courses)
	sum(p in Periods) Y[c,p] == 1;
// number of lectures assigned to any time-slot must not exceed
// the number of available classrooms.
forall(p in Periods)
	sum(c in Courses) Y[c,p] <= numRooms;
	*/
////at least 1 time-slot gap between Lectures of the same instructor
//forall(c1, c2 in Courses: c1 != c2 && crsAssi[c1] == crsAssi[c2])
//	forall(t1, t2 in TimeSlots: abs(t1-t2) < 2)
//		x[c1,t1] + x[c2, t2] <= 1;
////lectures of same course (section) non-overlapped
//forall(g in CourseGroups, t in TimeSlots)
//	forall(L1,L2 in lecGrpOf2Day[g]: L1<>L2)
//		x[L1,t] + x[L2,t] <= 1;
	
//	forall(c in Courses)
//		sum (w in Weekdays, p in Periods) J[c][w][p] == 1;
//			
//	       
//   forall(s in Students, c in Courses)
//       W[s][c] == sum (p in Periods) Enroll[s][c][p];
//    

//         
//   forall(w in Weekdays, p in Periods, j in Professors, c in Courses)  //only 1 room per room/period 
//       sum(r in Rooms)
//         Assign[w][p][r][j][c] <= 1;
//	
	forall(w in Weekdays, p in Periods, r in Rooms)  //only 1 course per room/period 
      sum(c in Courses)
         Avail[w][p][r][c] <= 1;
//   
/*      
    forall(c in Courses, p in Periods) //one room per course per period
      sum (r in Rooms)
        Occupy[c][r][p] <= 1;
//        
    forall(r in Rooms, p in Periods) //each course only given once per period
      sum (c in Courses)
        Occupy[c][r][p] <= 1;
        */
//     
//    forall(p in Periods, s in Students) //one course per period
//      	sum (c in Courses)
//      	  Enroll[s][c][p]<=1;
//      	  
//    forall(s in Students, c in Courses, p in Periods)
//        Enroll[s][c][p] <= sum (r in Rooms) Occupy[c][r][p];  // can only enroll in offered classes
//        
//    forall (p in Periods, c in Courses)
//      sum (s in Students) Enroll[s][c][p]  <= sum (r in Rooms) Occupy[c][r][p]*maxStudents; 
//make sure students per class don't exceed maxstudents'
//
//   	forall (p in Periods, c in Courses, r in Rooms)
//   	  Occupy[c][r][p] <= P[p];
//		
//	
//	forall(s in Students, c in Courses)
//      W[s][c] <= sum(w in Weekdays, p in Periods) Q[s][c][w][p];
//      
//      
//    forall(s in Students, c in Courses, w in Weekdays, p in Periods)
//      Q[s][c][w][p]<=W[s][c];  
//   
//   	forall(s in Students, w in Weekdays, p in Periods)
//   	  I[s][w][p] <= sum(c in Courses) Q[s][c][w][p];
//   	  
//   	forall(s in Students, c in Courses, w in Weekdays, p in Periods)  
//   	  Q[s][c][w][p]<=I[s][w][p];
//   	  
//   	//forall(c in Courses, w in Weekdays, p in Periods)
//   	  
//   	
//   	forall(c in Courses)
//     sum (w in Weekdays, p in Periods, r in Rooms)
//     	Avail[w][p][r][c] == 1;
//     	
//  	forall(j in Professors)
//  	  teachCourse:
//     	sum (<j,j.value()> in ProfCourses) Teach[<j,c>] == 1;
 

}

main{
//	for(c in Courses, w in WeekDays, p in Periods, r in Rooms)
//		Unfilled[c][w][p][r] == 1;    
// 
	thisOplModel.generate();
	cplex.solve();     
 var f = new IloOplOutputFile("output.txt");
 //put output here : var/logs/tarot
  //f.writeln(thisOplModel.printExternalData());
  // f.writeln(thisOplModel.printInternalData());	
   f.writeln(thisOplModel.printSolution());
   	 // lazy open
  f.close();
       
}
