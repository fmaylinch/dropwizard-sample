
const apiRootUrl = '/dw/api';

setupForm();

loadBooks().then(displayBooks);

// Equivalent to: loadBooks().then(displayBooks);
//
// let booksPromise = loadBooks();
// booksPromise.then(books => {
//   displayBooks(books);
// });


function setupForm() {

	$('form').submit(event => {
		console.log("Form submitted");
		event.preventDefault();

		const book = {
			title: $('#title').val(),
			author: $('#author').val(),
			numPages: parseInt($('#pages').val())
		};

		axios
			.post(apiRootUrl + '/books', book)
			.then(book => console.log(book))
			.catch(error => console.error("Error adding book!", error));
	});
}


/** Gets books from API and returns a promise of books */
function loadBooks() {

	let url = apiRootUrl + '/books';

	// We return the promise that fetch() gives us
	return fetch(url)
		.then(response => response.json())
		.catch(error => {
			console.log("AJAX request finished with an error :(");
			console.error(error);
		});
}

/** Displays books on the HTML */
function displayBooks(books) {

	let html = "<ul>";

	for (const book of books) {
		html += "<li>" + book.title + "</li>";
	}

	html += "</ul>";

	const resultDiv = document.getElementById("result");
	resultDiv.innerHTML = html;
}
