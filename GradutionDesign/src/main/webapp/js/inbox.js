$(function(){
	var pagenum=$("#allnum").val();
	var i=0;
	$(".pagination").append("<li><a style='cursor:pointer'>&laquo;</a></li>");
	for(i=0;i<pagenum;i++){
		var pg=i+1;
		$(".pagination").append("<li><a style='cursor:pointer'>"+pg+"</a></li>");
	}
	$(".pagination").append("<li><a style='cursor:pointer'>&raquo;</a></li>");
	
	$(".pagination").children().click(function(){
		var thispage=$(this).children().html();
		window.location="listOnePageEmail?page="+thispage;
	});
});