$(document).ready(function(){
	$(".choose-button").click(function(){
		if(checkBalance($(this).data("cost"), $("body").data("balance"))){
			choose($(this));
		}
	});

	$("#additional-number-button").click(getAdditionalNumber)
	$("#choose-ok-button").click(addService);
	$("#exclusive-number-button").click(getExclusiveNumber);
	$(".remove-service-button").click(function(){
		removeService($(this));
	});


});

function checkBalance(cost, balance){
	if(parseInt(cost, 10) > parseInt(balance, 10)){
		alert("You have not enogh money on your balance, your balance : " + balance +", cost : " + cost);
		return false;
	}
	else{
		return true;
	}
}

function choose(button){
	var action = button.data("action");
	var id = button.data("serviceid");

	if(action == "Additional number"){
		additionalNumberAction(id);
	}

	else if(action == "Exclusive number"){
		exclusiveNumberAction(id);
	}
	else{
		anyServiceAction(action, id);
	}

}

function anyServiceAction(service, id){
	var mod = $("#service-mod");
	console.log(service);
	$("#choose-ok-button").data("serviceid", id);
	$("#service-title").html(service);
	mod.modal("show");
}

function addService(){
	var id = $("#choose-ok-button").data("serviceid");
	console.log(id);
	$.post("/services", {serviceid : id, action : "add-service"}, function(){
		location.reload();
	});
}


function additionalNumberAction(id){
	var mod = $("#additional-number-mod");
	$("#additional-number-button").data("serviceid", id);
	mod.modal("show");
}


function getAdditionalNumber(){
	var id = $("#additional-number-button").data("serviceid");
	var addnumber = $("#additional-number");
	var mod = $("#additional-number-mod");

	if(! addnumber.get(0).checkValidity()){
		addnumber.get(0).setCustomValidity("Wrong phone number");
		addnumber.keyup(function(){
			addnumber.get(0).setCustomValidity('');
		});
	}
	else{
		$.post("/services", {number : addnumber.val(), action : "additional-number", serviceid : id }, function(data){
			if(data == "failed"){
				addnumber.get(0).setCustomValidity("There is already such phone");
				addnumber.keyup(function(){
					addnumber.get(0).setCustomValidity('');
				});
			}
			else{
				mod.modal("hide");
				alert("You have added additional phone!");
			}
		});
	}
}


function exclusiveNumberAction(id){
	var mod = $("#exclusive-number-mod");
	$("#exclusive-number-button").data("serviceid", id);
	mod.modal("show");
}


function getExclusiveNumber(){
	var id = $("#exclusive-number-button").data("serviceid");
	var exnumber = $("#exclusive-number");
	var mod = $("#exclusive-number-mod");

	if(! exnumber.get(0).checkValidity()){
		exnumber.get(0).setCustomValidity("Wrong phone number");
		exnumber.keyup(function(){
			exnumber.get(0).setCustomValidity('');
		});
	}
	else{
		$.post("/services", {number : exnumber.val(), action : "exclusive-number", serviceid : id}, function(data){
			if(data == "failed"){
				exnumber.get(0).setCustomValidity("There is already such phone");
				exnumber.keyup(function(){
					exnumber.get(0).setCustomValidity('');
				});
			}
			else{
				mod.modal("hide");
				alert("You have set your exclusive phone number!");
			}
		});
	}
}

function removeService(button){
	var title = button.data("title");
	var id = button.data("serviceid");
	var toProceed = confirm("Are you sure you want to remove service: " + title + " from your services");

	if(toProceed){
		$.post("/services", {serviceid : id, action : "remove-service"}, function(){
			location.reload();
		});
	}
}