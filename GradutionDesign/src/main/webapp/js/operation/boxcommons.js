$(function(){
	$(".mailcontent").click(function(){
		var messagenum=$(this).parent().children("td:eq(4)").children().val();
		window.location="showMailContent?thismessagenum="+messagenum+"&from="+$("li.active").attr("id");
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
	
	$("#readed").click(function(){
		var selected=null;
		$(".mailselected:checked").each(function(){
			if(selected==null)
				selected=$(this).parent().parent().children("td:eq(4)").children().val()+",";
			else
				selected=selected+$(this).parent().parent().children("td:eq(4)").children().val()+",";
		});
		$.ajax({
			type:"POST",
			url:"setMailSeen",
			data:{
				selected:selected,
				from:$("li.active").attr("id")
			},
			dataType:"text",
			success:function(data){
				$.unblockUI();
				$(".mailselected:checked").each(function(){
					selected=$(this).parent().parent().css("font-weight","normal");
				});
				$(".mailselected").prop("checked",false);
			},
			error:function(e) {
				window.location="onerror";
			},
			beforeSend : function(){
				$.unblockUI();
				$.blockUI({
					css:{
						border : 'none',
						padding : '15px',
						backgroundColor : '#000',
						'-webkit-border-radius' : '10px',
						'-moz-border-radius' : '10px',
						opacity : .5,
						color : '#fff'
					}
				});
			}
		});
	});
	
	$("#unreaded").click(function(){
		var selected=null;
		$(".mailselected:checked").each(function(){
			if(selected==null)
				selected=$(this).parent().parent().children("td:eq(4)").children().val()+",";
			else
				selected=selected+$(this).parent().parent().children("td:eq(4)").children().val()+",";
		});
		$.ajax({
			type:"POST",
			url:"setMailUnSeen",
			data:{
				selected:selected,
				from:$("li.active").attr("id")
			},
			dataType:"text",
			success:function(data){
				$.unblockUI();
				$(".mailselected:checked").each(function(){
					selected=$(this).parent().parent().css("font-weight","bold");
				});
				$(".mailselected").prop("checked",false);
			},
			error:function(e) {
				window.location="onerror";
			},
			beforeSend : function(){
				$.unblockUI();
				$.blockUI({
					css:{
						border : 'none',
						padding : '15px',
						backgroundColor : '#000',
						'-webkit-border-radius' : '10px',
						'-moz-border-radius' : '10px',
						opacity : .5,
						color : '#fff'
					}
				});
			}
		});
	});
});