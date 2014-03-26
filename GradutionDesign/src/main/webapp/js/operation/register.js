$(function(){
	$("#UserContainer").css("height",$(document).height()-52-20-20);
	
	$("#submit").click(function(){
		if($("#password").val()==$("#repassword").val()){
			$("#registeruser").submit();
		}
		else{
			alert("两次输入密码不一致，请重新输入");
			$("#password").val("");
			$("#repassword").val("");
			$("#password").focus();
		}
	});
	
	$("#password").mousedown(function(){
		$.ajax({
			type : "POST",
			url : "checkUsernameRepeat",
			data : {
				username : $("#username").val()
			},
			dataType : "text",
			success : function(data){
				//待改，需要增加对于返回json的判断
				if(data==null){
					$(".fa fa-spinner fa-spin fa-2x").css("display","none");
					$(".fa fa-check-circle fa-2x").css("display","inherit");
				}
				else{
					$(".fa fa-spinner fa-spin fa-2x").css("display","none");
					$(".fa fa-times-circle fa-2x").css("display","inherit");
				}
				
			},
			beforeSend : function(){
				$(".fa fa-check-circle fa-2x").css("display","none");
				$(".fa fa-spinner fa-spin fa-2x").css("display","inherit");
			},
		});
	});
});