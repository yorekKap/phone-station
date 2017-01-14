/**
 *
 */

$(document).ready(function(){
	$(".form-group").keyup(function(){
		validate($(this));
	});


});

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


