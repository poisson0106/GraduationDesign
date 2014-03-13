$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$("li.active").removeClass();
	$("#homemenu").addClass("active");
	
	$(".mailselected").prop("checked",false);
});