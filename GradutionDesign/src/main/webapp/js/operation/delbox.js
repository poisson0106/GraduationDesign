//�¼�����
$(function(){
	$("#refresh").click(function(){
		window.location="initialMailDeleted";
	});
	
	$("#delete").click(function(){
		if(confirm("你确定要删除这些邮件么？")){
			var selected=null;
			$(".mailselected:checked").each(function(){
				if(selected==null)
					selected=$(this).parent().parent().children("td:eq(4)").children().val()+",";
				else
					selected=selected+$(this).parent().parent().children("td:eq(4)").children().val()+",";
			});
			if(selected!=null&&selected!=""){
			$.ajax({
				type : "POST",
				url : "deleteMailPavemently",
				data : {
					selected : selected
				},
				dataType : "text",
				success : function(data){
					$.unblockUI();
					$.blockUI({
						message : $('#deletesuccess')
					});
					window.location="initialMailDeleted";
				},
				error : function(e) {
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
			}
			else
				alert("没有选择任何记录，无法删除!");
		};
	});
	
	$("#redotoinbox").click(function(){
		if(confirm("你确定要还原这些邮件到发件箱么？")){
			redoMailByBox("inbox");
		}
	});
	$("#redotosentbox").click(function(){
		if(confirm("你确定要还原这些邮件到已发送么？")){
			redoMailByBox("sentbox");
		}
	});
	$("#redotodraftbox").click(function(){
		if(confirm("你确定要还原这些邮件到草稿箱么？")){
			redoMailByBox("draftbox");
		}
	});
});

//��ʼ��
$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$(".panel div.in").collapse();
	$("#mailsystem").collapse();
	
	$("li.active").removeClass();
	$("#delboxmenu").addClass("active");
});

function redoMailByBox(box){
	var selected=null;
	$(".mailselected:checked").each(function(){
		if(selected==null)
			selected=$(this).parent().parent().children("td:eq(4)").children().val()+",";
		else
			selected=selected+$(this).parent().parent().children("td:eq(4)").children().val()+",";
	});
	if(selected!=null&&selected!=""){
		$.ajax({
			type : "POST",
			url : "redoMailByBox",
			data : {
				selected : selected,
				to : box
			},
			dataType : "text",
			success : function(data){
				$.unblockUI();
				$.blockUI({
					message : $('#redosuccess')
				});
				window.location="initialMailDeleted";
			},
			error : function(e) {
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
	}
	else{
		alert("没有选中任何邮件！请重新选择！");
		return null;
	}
}