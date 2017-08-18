
const ENTER_KEY_CODE = 13;

setupSearchFormVanilla();


// With <form>
//
// These functions expect the submit event on the <form>

function setupSearchFormVanilla() {
	const form = document.querySelector("form.search-box");
	form.onsubmit = function(event) {
		event.preventDefault(); // by default, the page is refreshed when the form is submitted
		performSearchVanilla();
	};
}

function setupSearchFormJQuery() {
	const form = $("form.search-box");
	form.submit(function(event) {
		event.preventDefault(); // by default, the page is refreshed when the form is submitted
		performSearchJQuery();
	});
}


// Without <form>
//
// These functions won't work if the <button> is inside a <form> since it will be submitted automatically
// when the button is clicked.

function setupSearchButtonVanilla() {

	const button = document.querySelector(".search-box button");
	button.onclick = function() {
		performSearchVanilla();
	};

	const input = document.querySelector(".search-box input");
	input.onkeyup = function(event) {
		if (event.keyCode === ENTER_KEY_CODE) {
			performSearchVanilla();
		}
	};
}

function setupSearchButtonJQuery() {

	const button = $(".search-box button");
	button.click(function() {
		performSearchJQuery();
	});

	const input = $(".search-box input");
	input.keyup(function(event) {
		if (event.keyCode === ENTER_KEY_CODE) {
			performSearchJQuery();
		}
	});
}


// Common

function performSearchVanilla() {
	const input = document.querySelector(".search-box input");
	const searchTerm = input.value;
	location.href = '/books?search=' + searchTerm;
}

function performSearchJQuery() {
	const input = $(".search-box input");
	const searchTerm = input.val();
	location.href = '/books?search=' + searchTerm;
}

