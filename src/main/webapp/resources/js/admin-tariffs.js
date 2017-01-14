/**
 *
 */
/**
 *
 */

$(function(){
	$(".edit-button").click(function(){
		showEditModal($(this));
	})

	$(".delete-button").click(function(){
		deleteTariff($(this));
	})

	$(".create-button").click(function(){
		showCreateModal();
	})

});

function showEditModal(button){
	var id = button.data("tariff-id");
	var mod = $("#edit-tariff-mod-" + id );
	mod.modal("show");
}

function showCreateModal(){
	var mod = $("#create-tariff-mod");
	mod.modal("show");

}

function deleteTariff(button){
	var id = button.data("tariff-id");
	var title = button.data("tariff-title");
	var res = confirm("Are you sure you want to delete tariff " + title + "?");

	if(res){
		if(res){
			$.post("/admin/tariffs", {"id" : id, action : "delete-tariff"}, function(data){
				if(data == "fail"){
					alert("Tariff deletion failed");
				}
				else{
					location.reload();
				}
			})
		}
	}
}
