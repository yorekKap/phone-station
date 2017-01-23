/**
 *
 */

$(document).ready(function(){
	$(".form-group").keyup(function(){
		validate($(this));
	});
	isSuccessful();

});

function isSuccessful(){
	var hidden = $("#successful");
	var successful = hidden.data("successful");
	var sum = hidden.data("sum");
	console.log(successful);
	if(successful){
		alert("You have successfully refill your balance for " + sum + " UAH");
	}
}

function validate(element){
	console.log("here");
	if(! element.find(".form-control").get(0).checkValidity()){
		console.log(1);
		element.addClass("has-error");
	}
	else{
		console.log(2);
		element.removeClass("has-error");
	}
}


