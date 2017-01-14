/**
 *
 */

function chooseTariffModal(tariffName, tariffId){
		var mod = $("#choose-modal");
		var chosen = $("#chosen-tariff");
		console.log(tariffName + "name");
		chosen.html(tariffName);
		mod.data("choosen", tariffId)
		mod.modal("show");
}

function chooseTariff(){
	var mod = $("#choose-modal");
	var tariffId = mod.data("choosen");
	$.post("/tariffs", {id : tariffId}, function(){
		location.reload();
	});
}

function cancelTariffModal(){
	var mod = $("#disconnect-modal");
	mod.modal("show");

}

function cancelTariff(){
	$.get("/tariff-disconnect");
	location.reload();

}

$(document).ready(function(){
	$(".choose").click(function(){
		chooseTariffModal($(this).data("tariff"), $(this).data("tariff-id"));
	});

	$(".disconnect").click(cancelTariffModal);
	$("#disconnect-ok-button").click(cancelTariff);
	$("#ok-button").click(chooseTariff);

});