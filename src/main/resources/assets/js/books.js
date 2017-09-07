
const apiRootUrl = '/dw/api';
const apiBooksUrl = apiRootUrl + '/books';


setupBookForm();
loadAndDisplayBooks();


/** Loads books from API and displays them */
function loadAndDisplayBooks() {

	loadBooks().then(books => {
		displayBooks(books);
	});
}


/** Setup of form to add a book */
function setupBookForm() {

	$('form').submit(event => {
		
		console.log("Form submitted");
		event.preventDefault(); // prevents default form behaviour (reloads the page)

		const book = {
			title: $('#title').val(),
			author: $('#author').val(),
			numPages: parseInt($('#pages').val())
		};

		axios
			.post(apiBooksUrl, book)
			.then(response => response.data) // turns to a promise of book
			.then(addedBook => {
				console.log("Added book", addedBook);
				loadAndDisplayBooks(); // to refresh list
			})
			.catch(error => console.error("Error adding book!", error));
	});
}


/** Gets books from API and returns a promise of books */
function loadBooks() {

	// We return the promise that axios gives us
	return axios.get(apiBooksUrl)
		.then(response => response.data) // turns to a promise of books
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
