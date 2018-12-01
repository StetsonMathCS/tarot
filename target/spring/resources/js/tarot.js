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
]

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

function loadGenerator() {
    renderCourses();
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

