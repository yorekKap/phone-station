/**
 *
 */

$(function(){
	$(".edit-button").click(function(){
		showEditModal($(this));
	})

	$(".delete-button").click(function(){
		deleteService($(this));
	})

	$(".create-button").click(function(){
		showCreateModal();
	})

});

function showEditModal(button){
	var id = button.data("service-id");
	var mod = $("#edit-service-mod-" + id );
	mod.modal("show");
}

function showCreateModal(){
	var mod = $("#create-service-mod");
	mod.modal("show");
}

function deleteService(button){
	var id = button.data("service-id");
	var title = button.data("service-title");
	var res = confirm("Are you sure you want to delete service " + title);

	if(res){
		if(res){
			$.post("/admin/services", {"id" : id, action : "delete-service"}, function(data){
				if(data == "fail"){
					alert("Service deletion failed");
				}
				else{
					location.reload();
				}
			})
		}
	}
}
