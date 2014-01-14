$(function(){
	$("#home").click(function(){
		window.location="mainframework";
	});
	
	$("#inbox").click(function(){
		$("#inboxbage").remove();
		window.location="initialMailReceive";
	});
	
	$("#sendbox").click(function(){
		window.location="initialMailSend";
	});
});

$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	var chosed=$("#chosed").val();
	$("li.active").removeClass();
	if(chosed=="1")
		$("#homemenu").addClass("active");
	else if(chosed=="2")
		$("#inboxmenu").addClass("active");
	else if(chosed=="3")
		$("#sendboxmenu").addClass("active");
});
