$(function(){
	$("#contactmenu").addClass("active");
	
	$(".panel div.in").collapse();
	$("#contactsystem").collapse();
	
	$("#selectall").change(function(){
		if($("#selectall").prop("checked")==true){
			$(".contactselected").prop("checked",true);
		}
		else{
			$(".contactselected").prop("checked",false);
		}
	});
	
	$("#refresh").click(function(){
		window.location="initialContact";
	});
	
	$("#delete").click(function(){
		if(confirm("你确定要删除这些记录么？")){
			var selected=null;
			$(".contactselected:checked").each(function(){
				if(selected==null)
					selected=$(this).parent().parent().children("td:eq(1)").html()+",";
				else
					selected=selected+$(this).parent().parent().children("td:eq(1)").children().val()+",";
			});
			if(selected!=null&&selected!=""){
				$.ajax({
					type : "POST",
					url : "deleteSelectedContact",
					data : {
						selected : selected
					},
					dataType : "text",
					success : function(data){
						$.unblockUI();
						$.blockUI({
							message : $('#deletesuccess')
						});
						window.location="initialContact";
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