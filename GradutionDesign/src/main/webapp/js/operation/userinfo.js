$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$(".panel div.in").collapse();
	$("#contactsystem").collapse();
	
	$("li.active").removeClass();
	$("#userinfomenu").addClass("active");
});