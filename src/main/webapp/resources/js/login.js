function invalid(element, message) {
	console.log("here");
	element.get(0).setCustomValidity(message);

	element.keyup(function(){
		element.get(0).setCustomValidity('');
	})
}