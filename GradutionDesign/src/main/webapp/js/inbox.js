$(function(){
	$(".mailcontent").click(function(){
		var messagenum=$(this).parent().children("td:eq(4)").children().val();
		window.location="showMailContent?thismessagenum="+messagenum;
	});
	
	$("#thispage").click(function(){
		window.history.back(-1);
	});
	
	$("#selectall").change(function(){
		if($("#selectall").prop("checked")==true){
			$(".mailselected").prop("checked",true);
		}
		else{
			$(".mailselected").prop("checked",false);
		}
	});
	
	$("#refresh").click(function(){
		window.location="initialMailReceive";
	});
	
	$("#delete").click(function(){
		if(confirm("Are you sure to delete these emails")){
			alert("Yes");
		}
		else{
			alert("No");
		}
	});
});

$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$("li.active").removeClass();
	$("#inboxmenu").addClass("active");
});