$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$(".panel div.in").collapse();
	
	$("li.active").removeClass();
	$("#homemenu").addClass("active");
	
	$(".mailselected").prop("checked",false);
});