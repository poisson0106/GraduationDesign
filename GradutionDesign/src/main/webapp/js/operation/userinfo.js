$(function(){
	$(".ischangepwd").change(function(){
		if($(this).val()=="yes")
			$("#opassword").removeAttr("disabled");
		else if($(this).val()=="no"){
			$("#opassword").attr("disabled","disabled");
			$("#opassword").val("");
		}
	});
	
	$("#checkpwd").click(function(){
		$.ajax({
			type : "POST",
			url : "checkPasswordOnChange",
			data : {
				opassword : $("#opassword").val()
			},
			dataType : "text",
			beforeSend : function(){
				$(".fa-check-circle").css("display","none");
				$(".fa-times-circle").css("display","none");
				$(".fa-spinner").css("display","");
			},
			success : function(data){
				//待改，需要增加对于返回json的判断
				if(data!=""){
					$(".fa-spinner").css("display","none");
					$(".fa-check-circle").css("display","inherit");
					$("#npassword").removeAttr("disabled");
					$("#rpassword").removeAttr("disabled");
					$("#nquestion").removeAttr("disabled");
					$("#nanswer").removeAttr("disabled");
				}
				else{
					$(".fa-spinner").css("display","none");
					$(".fa-times-circle").css("display","inherit");
					$("#npassword").val("");
					$("#rpassword").val("");
					$("#npassword").attr("disabled","disabled");
					$("#rpassword").attr("disabled","disabled");
					$("#nquestion").removeAttr("disabled","disabled");
					$("#nanswer").removeAttr("disabled","disabled");
				}
			},
		});
	});
	
	$("#submituserinfo").click(function(){
		if($(":checked").val()=="yes"){
			if(($("#npassword").val()==$("#rpassword").val())&&$("#npassword").val()!=""){
				if("#nquestion".val()!=""&&$("#nanswer").val()!="")
					$("#changeuserinfo").submit();
			}
			else{
				alert("两次输入密码不一致，请重新输入！");
				return false;
			}
		}
		else
			if($("#nickname").val()!="")
				$("#changeuserinfo").submit();
			else
				alert("昵称不能为空!");
	});
	
});

$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$(".panel div.in").collapse();
	$("#contactsystem").collapse();
	
	$("li.active").removeClass();
	$("#userinfomenu").addClass("active");
});