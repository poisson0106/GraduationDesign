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
	
	//用图片打开上传窗口
	$("#pictureBtn").click(function(){
		$("#picup").click();
	});
});