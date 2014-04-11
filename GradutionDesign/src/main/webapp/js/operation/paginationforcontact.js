$(function(){
	var pagenum=$("#allpagenum").val();
	var i=0;
	var thispagenum=parseInt($("#page").val());
	
	$(".pagination").append("<li><a style='cursor:pointer' id='first'>&laquo;</a></li>");
	$(".pagination").append("<li><a style='cursor:pointer' id='previous'>&lt;</a></li>");
	
	if(pagenum<=10){
		for(i=1;i<=pagenum;i++){
			$(".pagination").append("<li><a style='cursor:pointer'>"+i+"</a></li>");
		}
		thispagenum++;
		$(".pagination li:eq("+thispagenum+")").addClass("active");
	}
	else{
		if(pagenum-thispagenum<10){
			for(i=pagenum-9;i<=pagenum;i++){
				$(".pagination").append("<li><a style='cursor:pointer'>"+i+"</a></li>");
			}
			var active=10-(parseInt(pagenum)-parseInt(thispagenum))+1;
			$(".pagination li:eq("+active+")").addClass("active");
		}
		else{
			for(i=thispagenum;i<thispagenum+10;i++){
				$(".pagination").append("<li><a style='cursor:pointer'>"+i+"</a></li>");
			}
			$(".pagination li:eq(2)").addClass("active");
		}
	}
	
	$(".pagination").append("<li><a style='cursor:pointer;' id='next'>&gt;</a></li>");
	$(".pagination").append("<li><a style='cursor:pointer;border-top-right-radius:4px;border-bottom-right-radius:4px;' id='last'>&raquo;</a></li>");
	
	
});