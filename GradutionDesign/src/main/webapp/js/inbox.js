$(function(){
	var pagenum=$("#allpagenum").val();
	var i=0;
	$(".pagination").append("<li><a style='cursor:pointer' id='previous'>&laquo;</a></li>");
	for(i=0;i<pagenum;i++){
		var pg=i+1;
		$(".pagination").append("<li><a style='cursor:pointer'>"+pg+"</a></li>");
	}
	$(".pagination").append("<li><a style='cursor:pointer' id='next'>&raquo;</a></li>");
	
	$(".pagination").children().click(function(){
		var thispage=$(this).children().html();
		if(parseInt(thispage)>0)
			window.location="listOnePageEmail?page="+thispage;
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
				window.location="listOnePageEmail?page="+page;
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
				window.location="listOnePageEmail?page="+page;
		}
		else
		{
			window.location="listOnePageEmail?page=2";
		}
	});
	
	$("tr").click(function(){
		var messagenum=$(this).children("td:eq(3)").children().val();
		window.location="showMailContent?thismessagenum="+messagenum;
	});
});