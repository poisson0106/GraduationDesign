$(function(){
	$("#editor").wysiwyg();
	
	$("li.active").removeClass();
	$("#sendemailmenu").addClass("active");
	
});

$(function(){
	$("#send").click(function(){
		var content=$("#editor").html();
		content="<html><body><div>"+content+"</div></body></html>";
		$("#content").val(content);
		var sender=$("#loginname").html();
		$("#sender").val(sender);
		$(".form-horizontal").submit();
	});
	
	$("#hyperlink").click(function(){
		$("#grouphyperlink").addClass("dropdown open");
	});
	
	$("#addhyperlink").click(function(){
		var link=$("#hyperlinktext").val();
		link="<a href='"+link+"'>"+link+"</a>";
		$("#editor").append(link);
		$("#grouphyperlink").removeClass("dropdown open");
	});
	
	//用图片打开上传窗口
	$("#pictureBtn").click(function(){
		$("#picup").click();
	});
});