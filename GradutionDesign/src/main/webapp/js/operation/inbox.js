//�¼�����
$(function(){
	$("#refresh").click(function(){
		window.location="initialMailReceive";
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