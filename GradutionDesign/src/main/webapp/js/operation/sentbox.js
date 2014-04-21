$(function(){
	$("#refresh").click(function(){
		window.location="initialMailSent";
	});
});

$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$(".panel div.in").collapse();
	$("#mailsystem").collapse();
	
	$("li.active").removeClass();
	$("#sentboxmenu").addClass("active");
	
});