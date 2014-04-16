$(function(){
	$("#UserContainer").css("height",$(document).height()-52-20-20);
	
	$("#submit").click(function(){
		if($("#username").val()==""){
			alert("用户名不能为空!");
			return false;
		}
		if($("#password").val()==""){
			alert("密码不能为空！");
			return false;
		}
		else{
			if($("#question").val()==""){
				alert("问题不能为空，否则无法找回密码！");
				return false;
			}
			if($("#answer").val()==""){
				alert("答案不能为空，否则无法找回密码！");
				return false;
			}
			if($("#password").val()==$("#repassword").val()){
				$("#registeruser").submit();
			}
			else{
				alert("两次输入密码不一致，请重新输入");
				$("#password").val("");
				$("#repassword").val("");
				$("#password").focus();
			}
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
				if(data==""){
					$(".fa-spinner").css("display","none");
					$(".fa-check-circle").css("display","inherit");
				}
				else{
					$(".fa-spinner").css("display","none");
					$(".fa-times-circle").css("display","inherit");
				}
				
			},
			beforeSend : function(){
				$(".fa-check-circle").css("display","none");
				$(".fa-times-circle").css("display","none");
				$(".fa-spinner").css("display","");
			},
		});
	});
});