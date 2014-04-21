$(function(){
	$("#refresh").click(function(){
		window.location="initialDraftBox";
	});
});


$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$(".panel div.in").collapse();
	$("#mailsystem").collapse();
	
	$("li.active").removeClass();
	$("#draftboxmenu").addClass("active");
});