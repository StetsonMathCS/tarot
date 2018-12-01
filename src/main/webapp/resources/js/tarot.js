const courses = [
  {
    courseId: 1,
    courseCode: 'CSCI 111',
    courseTitle: 'Introduction to Computing',
    capacity: 15,
    sections:3,
  },
  {
    courseId: 2,
    courseCode: 'CSCI 141',
    courseTitle: 'Introduction to Computer Science I',
    capacity: 15,
    sections:3,
  },
  {
    courseId: 3,
    courseCode: 'CSCI 190',
    courseTitle: 'Special Topics in Computer Science',
    capacity: 15,
    sections:3,
  },
];

const rooms = [
  {
    roomId: 1,
    roomName: 'Elizabeth 205',
    capacity: 20,
  },
  {
    roomId: 2,
    roomName: 'Elizabeth 210',
    capacity: 20,
  },
];

const faculty = [
  {
    facultyId: 1,
    name: 'Hala ElAarag',
    maxClasses: 3,
  },
  {
    facultyId: 2,
    name: 'Daniel Plante',
    maxClasses: 4,
  },
  {
    facultyId: 3,
    name: 'Joshua Eckroth',
    maxClasses: 3,
  },
  {
    facultyId: 4,
    name: 'Basar Koc',
    maxClasses: 2,
  },
]

/**
 * Method to render the rooms with given data to the DOM.
 */
function renderRooms() {
  rooms.map((room) => {
    const root = document.createElement("tr");

    let roomLabel = document.createElement("td");
    roomLabel.appendChild(document.createTextNode(`${room.roomName}`));

    let roomCapacity = document.createElement("td");
    let formGroup = document.createElement("div");
    formGroup.class = "form-group";

    let roomCapacityInput = document.createElement("input");
    roomCapacityInput.type  = "text";
    roomCapacityInput.class = "form-control";
    roomCapacityInput.id    = room.roomId;
    roomCapacityInput.value = room.capacity;
    roomCapacityInput.name  = `courseSections[${room.roomId}]`;
    roomCapacity.appendChild(formGroup.appendChild(roomCapacityInput));

    root.appendChild(roomLabel);
    root.appendChild(roomCapacity);

    (document.getElementById("roomParameters")).appendChild(root);
  });
}

/**
 * Method to render the courses with given data to the DOM.
 */
function renderCourses() {
  courses.map((course) => {
    const root = document.createElement("tr");

    let courseLabel = document.createElement("td");
    courseLabel.appendChild(document.createTextNode(`${course.courseCode} ${course.courseTitle}`));

    let courseCapacity = document.createElement("td");
    let formGroup = document.createElement("div");
    formGroup.class = "form-group";

    let courseCapacityInput = document.createElement("input");
    courseCapacityInput.type  = "text";
    courseCapacityInput.class = "form-control";
    courseCapacityInput.id    = course.courseId;
    courseCapacityInput.value = course.capacity;
    courseCapacityInput.name  = `courseSections[${course.courseId}]`;
    courseCapacity.appendChild(formGroup.appendChild(courseCapacityInput));

    let courseSections = document.createElement("td");
    formGroup = document.createElement("div");
    formGroup.class = "form-group";

    let courseSectionsInput = document.createElement("input");
    courseSectionsInput.type  = "text";
    courseSectionsInput.class = "form-control";
    courseSectionsInput.id    = course.courseId;
    courseSectionsInput.value = course.sections;
    courseSectionsInput.name  = `courseSections[${course.courseId}]`;
    courseSections.appendChild(formGroup.appendChild(courseSectionsInput));

    root.appendChild(courseLabel);
    root.appendChild(courseCapacity);
    root.appendChild(courseSections);

    (document.getElementById("courseParameters")).appendChild(root);
  });
}

/**
 * Method to render the faculty with given data to the DOM.
 */
function renderFaculty() {
  faculty.map((faculty) => {
    const root = document.createElement("tr");

    let facultyLabel = document.createElement("td");
    facultyLabel.appendChild(document.createTextNode(`${faculty.name}`));

    let facultyMaxClasses = document.createElement("td");
    let formGroup = document.createElement("div");
    formGroup.class = "form-group";

    let facultyMaxClassesInput = document.createElement("input");
    facultyMaxClassesInput.type  = "text";
    facultyMaxClassesInput.class = "form-control";
    facultyMaxClassesInput.id    = faculty.facultyId;
    facultyMaxClassesInput.value = faculty.maxClasses;
    facultyMaxClassesInput.name  = `facultyMaxClasses[${faculty.facultyId}]`;
    facultyMaxClasses.appendChild(formGroup.appendChild(facultyMaxClassesInput));

    root.appendChild(facultyLabel);
    root.appendChild(facultyMaxClasses);

    (document.getElementById("facultyParameters")).appendChild(root);
  });
}

function loadGenerator() {
  renderCourses();
  renderRooms();
  renderFaculty();
}


// // Optional: Clear all existing options first:
// select.innerHTML = "";
// // Populate list with options:
// for(var i = 0; i < options.length; i++) {
//     var opt = options[i];
//     select.innerHTML += "<option value=\"" + opt + "\">" + opt + "</option>";
// }

function submitForm() {
	const form = document.getElementById("myForm");
	let jsonObject = {};
	for(let field of form.elements) {
	  if (field.name) {
	      jsonObject[field.name] = field.value;
	  }
	}

	console.log(jsonObject);
	console.log(courses)
}

