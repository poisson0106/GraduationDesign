$(function(){
	$("#UserContainer").css("height",$(document).height()-52-20-20);
	
	$("#answer").mousedown(function(){
		$.ajax({
			type : "POST",
			url : "getPwdQuestion",
			data : {
				username : $("#username").val()
			},
			dataType : "text",
			success : function(data){
				if(data!=""){
					var json_s=JSON.parse(data);
					$("#question").prop("placeholder",json_s[0].question);
				}
				else
					$("#question").prop("placeholder","该用户不存在，请重新检查用户名输入是否正确");
				
			},
			beforeSend : function(){
				$("#question").prop("placeholder","正在查找问题，请耐心等待.....");
			},
		});
	});
	
	$("#submit").click(function(){
		if($("#password").val()==$("#repassword").val()){
			$("#findpassword").submit();
		}
		else{
			alert("两次输入密码不一致，请重新输入");
			$("#password").val("");
			$("#repassword").val("");
			$("#password").focus();
		}
	});
});