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
    isActive: true,
  },
  {
    roomId: 2,
    roomName: 'Elizabeth 210',
    capacity: 20,
    isActive: true,
  },
];

const faculty = [
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
  },
  {
    facultyId: 2,
    name: 'Daniel Plante',
    maxClasses: 4,
    isActive: true,
    availability: [
      {
        id: 1,
        from: '09:00:00',
        to: '12:00:00',
      },
      {
        id: 2,
        from: '03:30:00',
        to: '17:30:00',
      },
     ],
  },
  {
    facultyId: 3,
    name: 'Joshua Eckroth',
    maxClasses: 3,
    isActive: true,
    availability: [
      {
        id: 1,
        from: '10:00:00',
        to: '12:00:00',
      },
      {
        id: 2,
        from: '13:30:00',
        to: '15:30:00',
      },
     ],
  },
  {
    facultyId: 4,
    name: 'Basar Koc',
    maxClasses: 2,
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
        to: '17:30:00',
      },
     ],
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
    roomCapacityInput.name  = `roomCapacity[${room.roomId}]`;
    roomCapacity.appendChild(formGroup.appendChild(roomCapacityInput));

    let roomActive = document.createElement("td");
    formGroup = document.createElement("div");
    formGroup.class = "form-group";

    let roomActiveInput = document.createElement("input");
    roomActiveInput.type  = "checkbox";
    roomActiveInput.class = "form-check-input";
    roomActiveInput.id    = room.roomId;
    roomActiveInput.checked = room.isActive;
    roomActiveInput.name  = `roomActive[${room.roomId}]`;
    roomActive.appendChild(formGroup.appendChild(roomActiveInput));

    root.appendChild(roomLabel);
    root.appendChild(roomCapacity);
    root.appendChild(roomActive);

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
    courseCapacityInput.name  = `courseCapacity[${course.courseId}]`;
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

    let facultyActive = document.createElement("td");
    formGroup = document.createElement("div");
    formGroup.class = "form-group";

    let facultyActiveInput = document.createElement("input");
    facultyActiveInput.type  = "checkbox";
    facultyActiveInput.class = "form-check-input";
    facultyActiveInput.id    = faculty.facultyId;
    facultyActiveInput.checked = faculty.isActive;
    facultyActiveInput.name  = `facultyActive[${faculty.facultyId}]`;
    facultyActive.appendChild(formGroup.appendChild(facultyActiveInput));

    let facultyAvailability = document.createElement("td");

    facultyAvailability.id = `facultyAvailabilityList${faculty.facultyId}`;

    faculty.availability.map((availability) => {
      formGroup = document.createElement("div");
      formGroup.class = "form-group";

      let facultyAvailabilityFrom = document.createElement("input");
      facultyAvailabilityFrom.type  = "text";
      facultyAvailabilityFrom.class = "form-check-input";
      facultyAvailabilityFrom.id    = faculty.facultyId;
      facultyAvailabilityFrom.value = availability.from;
      facultyAvailabilityFrom.name  = `facultyAvailability[${faculty.facultyId}][${availability.id}]['from']`;

      let facultyAvailabilityTo = document.createElement("input");
      facultyAvailabilityTo.type  = "text";
      facultyAvailabilityTo.class = "form-check-input";
      facultyAvailabilityTo.id    = faculty.facultyId;
      facultyAvailabilityTo.value = availability.to;
      facultyAvailabilityTo.name  = `facultyAvailability[${faculty.facultyId}][${availability.id}]['to']`;

      formGroup.appendChild(facultyAvailabilityFrom);
      formGroup.appendChild(facultyAvailabilityTo);

      facultyAvailability.appendChild(formGroup);
     });

 let formGroup2 = document.createElement("div");
      formGroup2.class = "form-group";

      let addFacultyAvailabilityFrom = document.createElement("input");
      addFacultyAvailabilityFrom.type  = "text";
      addFacultyAvailabilityFrom.class = "form-check-input";
      addFacultyAvailabilityFrom.value = "00:00:00";
      addFacultyAvailabilityFrom.name  = "addFacultyAvailabilityFrom";

      let addFacultyAvailabilityTo = document.createElement("input");
      addFacultyAvailabilityTo.type  = "text";
      addFacultyAvailabilityTo.class = "form-check-input";
      addFacultyAvailabilityTo.value = "00:00:00";
      addFacultyAvailabilityTo.name  = "addFacultyAvailabilityTo";

      let facultyId = document.createElement("input");
      facultyId.type  = "hidden";
      facultyId.value = faculty.facultyId;
      facultyId.name  = "facultyId";

      let addFacultyAvailabilityButton   = document.createElement("input");
      addFacultyAvailabilityButton.type  = "submit";
      addFacultyAvailabilityButton.class = "btn btn-primary";
      addFacultyAvailabilityButton.value = "Add";

      formGroup2.appendChild(addFacultyAvailabilityFrom);
      formGroup2.appendChild(addFacultyAvailabilityTo);
      formGroup2.appendChild(facultyId);
      formGroup2.appendChild(addFacultyAvailabilityButton);

      let form = document.createElement("form");
      form.id = `addFacultyAvailabilityForm${faculty.facultyId}`;

      form.onsubmit = function(event) {
        event.preventDefault();
        addFacultyAvailability(
          event.target[2].value,
          event.target[0].value,
          event.target[1].value
        );
      }

      form.appendChild(formGroup2);
    facultyAvailability.appendChild(form);

    root.appendChild(facultyLabel);
    root.appendChild(facultyMaxClasses);
    root.appendChild(facultyActive);
    root.appendChild(facultyAvailability);

    (document.getElementById("facultyParameters")).appendChild(root);
  });
}

function addFacultyAvailability(facultyId, from, to) {
  let formGroup = document.getElementById(`facultyAvailabilityList${facultyId}`);
  let length = (faculty[facultyId].availability.length);


  let formGroup2 = document.createElement("div");
  formGroup2.class = "form-group";

  let facultyAvailabilityFrom = document.createElement("input");
  facultyAvailabilityFrom.type  = "text";
  facultyAvailabilityFrom.class = "form-check-input";
  facultyAvailabilityFrom.id    = facultyId;
  facultyAvailabilityFrom.value = from;
  facultyAvailabilityFrom.name  = `facultyAvailability[${facultyId}][${length+1}]['from']`;

  let facultyAvailabilityTo = document.createElement("input");
  facultyAvailabilityTo.type  = "text";
  facultyAvailabilityTo.class = "form-check-input";
  facultyAvailabilityTo.id    = facultyId;
  facultyAvailabilityTo.value = to;
  facultyAvailabilityTo.name  = `facultyAvailability[${facultyId}][${length+1}]['to']`;

  formGroup2.appendChild(facultyAvailabilityFrom);
  formGroup2.appendChild(facultyAvailabilityTo);
  formGroup.insertBefore(formGroup2, document.getElementById(`addFacultyAvailabilityForm${facultyId}`));

  faculty[facultyId].availability.push(
  {
    id: length,
    from: from,
    to: to,
  }
  );
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

