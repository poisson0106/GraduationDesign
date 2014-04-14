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

$(function(){
	var pagenum=$("#allpagenum").val();
	var thispagenum=$("#page").val();
	var frompage=$("li.active").attr("id");
	
	$(".pagination").children().click(function(){
		var thispage=$(this).children().html();
		if(parseInt(thispage)>0)
			window.location="listOnePageEmail?page="+thispage+"&from="+frompage;
	});
	
	$("#previous").click(function(){
		var page='';
		var i;
		var url=window.location.search;
		if(url.indexOf("?")!=-1)   
		{   
		  var str=url.substr(1);   
		  strs = str.split("&");   
		  for(i=0;i<strs.length;i++)   
		  {   
		    if([strs[i].split("=")[0]]=='page')
		    	page=unescape(strs[i].split("=")[1]);
		  }
		  page--;
		  if(page<1)
				alert("This is the first page");
			else
				window.location="listOnePageEmail?page="+page+"&from="+frompage;
		}
		else
		{
			alert("This is the first page");
		}
	});
	
	$("#next").click(function(){
		var page='';
		var i;
		var url=window.location.search;
		if(url.indexOf("?")!=-1)   
		{   
		  var str=url.substr(1);   
		  strs = str.split("&");   
		  for(i=0;i<strs.length;i++)   
		  {   
		    if([strs[i].split("=")[0]]=='page')
		    	page=unescape(strs[i].split("=")[1]);
		  }
		  page++;
		  if(page>parseInt($("#allpagenum").val()))
				alert("This is the last page");
			else
				window.location="listOnePageEmail?page="+page+"&from="+frompage;
		}
		else
		{
			if(pagenum>2)
				window.location="listOnePageEmail?page=2"+"&from="+frompage;
			else
				alert("This is the last page");
		}
	});
	
	$("#first").click(function(){
		window.location="listOnePageEmail?page=1"+"&from="+frompage;
	});
	
	$("#last").click(function(){
		window.location="listOnePageEmail?page="+pagenum+"&from="+frompage;
	});
	
	$('#go').click(function(){
		var gopagenum=$("#gopagenum").val();
		if(gopagenum>pagenum||gopagenum<1){
			alert("You input the wrong page number!");
			$("#gopagenum").val("");
			$("#gopagenum").focus();
		}
		else
			window.location="listOnePageEmail?page="+gopagenum+"&from="+frompage;
		
	});
});