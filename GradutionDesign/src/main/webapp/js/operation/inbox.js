//�¼�����
$(function(){
	$("#refresh").click(function(){
		window.location="initialMailReceive";
	});
	
	$("#delete").click(function(){
		if(confirm("你确认要删除这些邮件么？")){
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
					url : "deleteSelectedEmail",
					data : {
						selected : selected
					},
					dataType : "text",
					success : function(data){
						$.unblockUI();
						$.blockUI({
							message : $('#deletesuccess')
						});
						window.location="initialMailReceive";
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
});

//��ʼ��
$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$("li.active").removeClass();
	$("#inboxmenu").addClass("active");
	
	$(".panel div.in").collapse();
	$("#mailsystem").collapse();
	
	$(".mailselected").prop("checked",false);
});