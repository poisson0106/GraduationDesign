$(function(){
	$("#home").click(function(){
		window.location="mainframework";
	});
	
	$("#inbox").click(function(){
		window.location="initialMailReceive";
	});
	
	$("#sendemail").click(function(){
		window.location="initialMailSend";
	});
	
	$("#delbox").click(function(){
		window.location="initialMailDeleted";
	});
	
	$("#draftbox").click(function(){
		window.location="initialDraftBox";
	});
	
	$("#sentbox").click(function(){
		window.location="initialMailSent";
	});
	
	$("#contact").click(function(){
		window.location="initialContact";
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
		$("#sendemail").addClass("active");
});
