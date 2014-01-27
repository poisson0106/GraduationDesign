$(function(){
	$("#reply").click(function(){
		var receiver=$("#sender").html();
		var subject="Re: "+$("#subject").html();
		window.location="replyOneEmail?receiver="+receiver+"&subject="+subject;
	});
	
	$("#withoutatch").click(function(){
		$("#content").val($("#mailcontent").html());
		$("#fwsubject").val("Fw: "+$("#subject").html());
		$("#forwardwithoutatch").submit();
	});
	
	$("#attach").children().children().click(function(){
		var messagenum=$("#messagenum").val();
		var selected=$(this).html();
		window.open("downloadSelectedAttachment?selected="+selected+"&messagenum="+messagenum);
	});
});