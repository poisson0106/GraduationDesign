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
	
	$("#withatch").click(function(){
		var filenamelist=null;
		$("#acontent").val($("#mailcontent").html());
		$("#afwsubject").val("Fw: "+$("#subject").html());
		$("#attach li").each(function(){
			if(filenamelist==null)
				filenamelist=$(this).children().html()+",";
			else
				filenamelist=filenamelist+$(this).children().html()+",";
		});
		$("#fnamelist").val(filenamelist);
		$("#forwardwithatch").submit();
	});
	
	$("#attach").children().children().click(function(){
		var messagenum=$("#messagenum").val();
		var selected=$(this).html();
		var from=$("#from").val();
		window.open("downloadSelectedAttachment?selected="+selected+"&messagenum="+messagenum+"&from="+from);
	});
	
	$("#mailcontent").css("height",$(document).height()-52-20-20-56-184-20);
	$("#mailcontent").css("overflow","auto");
});

$(function(){
	$(".panel div.in").collapse();
})