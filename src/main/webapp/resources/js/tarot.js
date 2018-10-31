const select = document.getElementById("exampleFormControlSelect1");

const courses = [
    {
        courseCode: 'CSCI 111',
        courseTitle: 'Introduction to Computing',
        capacity: 15,
        sections:3,
    },
    {
        courseCode: 'CSCI 141',
        courseTitle: 'Introduction to Computer Science I',
        capacity: 15,
        sections:3,
    },
    {
        courseCode: 'CSCI 190',
        courseTitle: 'Special Topics in Computer Science',
        capacity: 15,
        sections:3,
    },
]

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

