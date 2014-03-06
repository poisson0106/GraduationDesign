//�¼�����
$(function(){
	$("#refresh").click(function(){
		window.location="initialMailDeleted";
	});
	
	$("#delete").click(function(){
		if(confirm("Are you sure to delete these emails")){
			var selected=null;
			$(".mailselected:checked").each(function(){
				if(selected==null)
					selected=$(this).parent().parent().children("td:eq(4)").children().val()+",";
				else
					selected=selected+$(this).parent().parent().children("td:eq(4)").children().val()+",";
			});
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
		};
	});
});

//��ʼ��
$(function(){
	var width=window.screen.width;
	$("#maincontainer").css("width",width);
	
	$("li.active").removeClass();
	$("#delboxmenu").addClass("active");
});