function checkPasswords(){
	console.log("here");
	var pass = $("#password");
	var repass = $("#repassword");

	if(pass.val() != repass.val()){
		console.log(pass.value);
		pass.get(0).setCustomValidity("Passwords must match");
		repass.get(0).setCustomValidity("Passwords must match");
	}
	else{
		pass.get(0).setCustomValidity('');
		repass.get(0).setCustomValidity('');
	}
}

function setInvalid(){
	var message = "There is already user with such username or phone";
	var phone = $("#phone");
	phone.get(0).setCustomValidity(message);
	var username = $("#username");
	username.get(0).setCustomValidity(message);

	phone.keyup(function(){
		phone.get(0).setCustomValidity('');
		username.get(0).setCustomValidity('');
	});

	username.keyup(function(){
		phone.get(0).setCustomValidity('');
		username.get(0).setCustomValidity('');
	});



}

$(document).ready(function(){
	$("#repassword").keyup(checkPasswords);
	$("#password").keyup(checkPasswords);
});