# Front End [![Generic badge](https://img.shields.io/badge/Docker-Compatible-blue.svg)](https://www.docker.com/) ![Docker Build Status](https://img.shields.io/docker/build/wsdt/tarot.svg)
###### By: Bailey Granam

## What is it?
This document describes the frontend of Tarot made with the Java-framework 'Spring' and Javascript.

## Features
- List of all courses
- Select capacity and # of sections per course
- List of all rooms
- Set a room as active or inactive
- List of all faculty
- Set a faculty member as active or inactive
- Set max classes a faculty member can teach
- Set availability of a faculty member
- Form data saved to local storage upon submission

### List of all courses
> As a regular user of the application, I want to be able to see a list of all courses.

The list of courses can be hard coded or retrieved via an API request to a database. The courses must be in the following format to be properly parsed and rendered to the DOM. 
```
{
    courseId: 1,
    courseCode: 'CSCI 111',
    courseTitle: 'Introduction to Computing',
    capacity: 15,
    sections:3,
}
```

When the generator page is accessed this data is automatically rendered to the DOM via a function called renderCourses() located in the Tarot.js file. It works by mapping each element in the array to a table.

### Select capacity and # of sections per course
> As a regular user of the application, I want to be able to set the capacity and # of sections each course contains.

When courses are rendered an input for the capacity and number of sections are created. When the form is submitted these values are stored in 2D arrays such as,

```
courseCapacity[courseId][0]
courseSections[courseId][0]
```
where the value(s) can be accessed.

### List of all rooms
> As a regular user of the application, I want to be able to see a list of all rooms.

The list of rooms can be hard coded or retrieved via an API request to a database. The courses must be in the following format to be properly parsed and rendered to the DOM. 
```
{
    roomId: 1,
    roomName: 'Elizabeth 205',
    capacity: 20,
    isActive: true,
}
```

When the generator page is accessed this data is automatically rendered to the DOM via a function called renderRooms() located in the Tarot.js file. It works by mapping each element in the array to a table.

### Set a room as active or inactive
> As a regular user of the application, I want to be able to mark whether or not to include a room in generations.

When the rooms are rendered a checkbox is created that can be toggled to determine whether or not to include a room.

![alt text](https://github.com/StetsonMathCS/tarot/blob/master/docs/img/FrontEnd01 "Toggle room activity")

### List of all faculty.
> As a regular user of the application, I want to be able to see a list of all faculty.

The list of faculty can be hard coded or retrieved via an API request to a database. The faculty must be in the following format to be properly parsed and rendered to the DOM. 
```
{
    facultyId: 1,
    name: 'Hala ElAarag',
    maxClasses: 3,
    isActive: true,
    availability: [
    {
      id: 1,
      from: '09:00:00',
      to: '12:00:00',
    },
    {
      id: 2,
      from: '13:30:00',
      to: '15:30:00',
    },
   ],
}
```

When the generator page is accessed this data is automatically rendered to the DOM via a function called renderFaculty() located in the Tarot.js file. It works by mapping each element in the array to a table.

### Set a faculty member as active or inactive
> As a regular user of the application, I want to be able to mark whether or not to include a faculty member in generations.

When the rooms are rendered a checkbox is created that can be toggled to determine whether or not to include a faculty.

### Set max classes a faculty member can teach
> As a regular use of the application, I want to be able to set the max number of classes a faculty member could teach in a semester.

When the faculty are rendered an input is created to set the max classes a faculty can teach.

### Set availability of a faculty member
> As a regular user of the application, I want to be able to set the hours a faculty member is available.

When the faculty are rendered an input is created that can be used to add availability to a faculty. This is done via a function called 

```
addFacultyAvailability(facultyId, from, to)
```

the facultyId and from and to values are required. When the function is called the availability is dynamically rendered to the webpage and included in the form submission in the following format,

```
facultyAvailability[facultyId][availabilityId]['from']
facultyAvailability[facultyId][availabilityId]['to']
```

### Form data saved to local storage upon submission
> As a regular user of the application, I want to be able to save data for future user.

When the form is submitted all the data is compiled from the form and saved to local storage via the submitForm function.

```
function submitForm(e) {
  const form = document.getElementById("myForm");
    let jsonObject = {};
	for(let field of form.elements) {
	  if (field.name) {
	    if(field.type === "checkbox") {
	      jsonObject[field.name] = field.checked;
	    }
	    else {
	      jsonObject[field.name] = field.value;
	    }
	  }
	}

    localStorage.setItem('data', JSON.stringify(jsonObject));
	console.log(jsonObject);
}
```


