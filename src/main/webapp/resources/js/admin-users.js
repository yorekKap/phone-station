/**
 *
 */
$(function(){
	$(".disconnect-button").click(function(){
		disconnectUser($(this));
	});

});

function disconnectUser(button){
	var id = button.data("uid");
	var username = button.data("username");
	var res = confirm("Are you sure you want to disconnect user " + username + "?");

	if(res){
		$.post("/admin/users", {userId : id, action : "disconnect-user"}, function(data){
			if(data == "fail"){
				alert("User disconnection failed");
			}
			else{
				location.reload();
			}
		})
	}

}
