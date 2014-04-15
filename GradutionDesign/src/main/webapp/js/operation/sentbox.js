$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$(".panel div.in").collapse();
	
	$("li.active").removeClass();
	$("#sentboxmenu").addClass("active");
	
});