$(function(){
	$("#reply").click(function(){
		var receiver=$("#sender").html();
		var subject="Re: "+$("#subject").html();
		window.location="replyOneEmail?receiver="+receiver+"&subject="+subject;
	});
});