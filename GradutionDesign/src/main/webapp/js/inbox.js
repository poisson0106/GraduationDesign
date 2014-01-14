$(function(){
	$("tr").click(function(){
		var messagenum=$(this).children("td:eq(3)").children().val();
		window.location="showMailContent?thismessagenum="+messagenum;
	});
	
	$("#thispage").click(function(){
		window.history.back(-1);
	});
});

$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$("li.active").removeClass();
	$("#inboxmenu").addClass("active");
});