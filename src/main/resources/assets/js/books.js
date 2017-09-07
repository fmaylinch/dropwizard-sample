
const apiRootUrl = '/dw/api';

let booksPromise = loadBooks();

booksPromise.then(books => {
	displayBooks(books);
});

// Equivalent to the code above
// loadBooks().then(displayBooks);



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

// We're not using this function.
// It's ok to do it this way,
// although the way we use above is a bit more elegant.

/** Gets books from API and displays them */
function loadBooksAndDisplayThem() {

	let url = apiRootUrl + '/books';

	fetch(url)
		.then(response => response.json())
		.then(books => {
			console.log("AJAX request finished correctly :)");
			const result = `Found ${books.length}`;
			console.log(result);
			displayBooks(books);
		})
		.catch(error => {
			console.log("AJAX request finished with an error :(");
			console.error("ERROR:", error);
		});
}
