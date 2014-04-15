//定时保存草稿任务
$(function(){
	setInterval("saveDraftAuto()",60000*3); //每3分钟保存一遍草稿
});

//启用所见即所得编辑器
$(function(){
	$("#editor").wysiwyg();
	
	$("li.active").removeClass();
	$("#sendemailmenu").addClass("active");
	
	$(".panel div.in").collapse();
	
	$("#file_upload").uploadify({
        'buttonText'    : 'Add Attach',  
        'swf'           : getRootPath()+'swf/uploadify.swf',  
        'uploader'      : getRootPath()+'/uploadAttachment',  
        'auto'          : false, 
        'fileSizeLimit' : '30720KB', 
        'fileTypeExts'  : '*.doc; *.xls; *.rar; *.ppt', 
        'cancelImg' :  getRootPath()+'/img/uploadify-cancel.png',
        'uploadLimit' : 5, 
        'formData'      : {'foldername':$("#loginname").html()},
        'onUploadStart' : function(file) {
            $("#filenamelist").val($("#filenamelist").val()+file.name+",");
        },
        'onUploadComplete':function(){  
        	$(this).parent().parent().removeClass("dropdown open");
        },
        'onUploadSuccess':function(file){
        	
        }
    });  
	
});

//页面动作处理
$(function(){
	$("#send").click(function(){
		var content=$("#editor").html();
		content="<html><body><div>"+content+"</div></body></html>";
		$("#content").val(content);
		var sender=$("#loginname").html();
		$("#sender").val(sender);
		var filenamelist=null;
		$("#fnamelist li").each(function(){
			if(filenamelist==null)
				filenamelist=$(this).html()+",";
			else
				filenamelist=filenamelist+$(this).html()+",";
		});
		if(filenamelist !=''&&filenamelist!=null)
			$("#filenamelist").val(filenamelist);
		$(".form-horizontal").attr("action","sendOneEmail");
		$(".form-horizontal").submit();
	});
	
	$("#cancel").click(function(){
		window.location="mainframework";
	});
	
	$("#save").click(function(){
		var content=$("#editor").html();
		content="<html><body><div>"+content+"</div></body></html>";
		$("#content").val(content);
		var sender=$("#loginname").html();
		$("#sender").val(sender);
		var filenamelist=null;
		$("#fnamelist li").each(function(){
			if(filenamelist==null)
				filenamelist=$(this).html()+",";
			else
				filenamelist=filenamelist+$(this).html()+",";
		});
		if(filenamelist !=''&&filenamelist!=null)
			$("#filenamelist").val(filenamelist);
		$(".form-horizontal").attr("action","saveOneEmail");
		$(".form-horizontal").submit();
	});
	
	$("#hyperlink").click(function(){
		if($(this).parent().prop("class")=="btn-group")
			$("#grouphyperlink").addClass("dropdown open");
		else
			$("#grouphyperlink").removeClass("dropdown open");
	});
	
	$("#addhyperlink").click(function(){
		var link=$("#hyperlinktext").val();
		link="<a href='"+link+"'>"+link+"</a>";
		$("#editor").append(link);
		$("#grouphyperlink").removeClass("dropdown open");
	});
	
	//图片上传按钮点击
	$("#pictureBtn").click(function(){
		$("#picup").click();
	});
	
	$("#addattch").click(function(){
		//$("#attach").click();
		if($(this).parent().prop("class")=="btn-group")
			$(this).parent().addClass("dropdown open");
		else
			$(this).parent().removeClass("dropdown open");
	});
	
	$("#uploadbtn").click(function(){
		$('#file_upload').uploadify('upload','*');
	});
	
	//需要添加关于输入即搜索的功能，ajax实现
	$("#receiver").keyup(function(event){
		if((event.which>=48&&event.which<=57)||(event.which>=65&&event.which<=90)){
			$.ajax({
				type : "POST",
				url : "findReceivers",
				data : {
					words : $("#receiver").val(),
					username : $("#loginname").html()
				},
				dataType : "text",
				success : function(data){
					$("#receiversaddress").empty();
					if(data==""){
						$("#receiversaddress").append("<li role='presentation menuitem' style='margin-left:20px;'>你没有该联系人，你可以重新输入寻找或者继续输入。</li>");
					}
					else{
						var json_s=JSON.parse(data);
						for(i=0;i<json_s.length;i++){
							var add="<li style='cursor:pointer'><a class='selectedaddress' onclick=addaddress('"+json_s[i].username+"@usstemail.com')>"+json_s[i].nickname+"["+json_s[i].username+"@usstemail.com]</a></li>";
							$("#receiversaddress").append(add);
						}
					}
				},
				beforeSend : function(){
					$("#receiversaddress").empty();
					$("#receiversaddress").append("<li role='presentation menuitem' style='margin-left:20px;'>正在查询.....</li>");
				}
			});
		}
	});
	
	/*$("#receiversaddress li a").click(function(){
			$("#receiver").val($(this).html());
	});*/
});

function addaddress(address){
	$("#receiver").val(address);
}

function saveDraftAuto(){
	$("#fnamelist li").each(function(){
		if(filenamelist==null)
			filenamelist=$(this).html()+",";
		else
			filenamelist=filenamelist+$(this).html()+",";
	});
	if(filenamelist !=''&&filenamelist!=null)
		$("#filenamelist").val(filenamelist);
	$.ajax({
		type : "POST",
		url : "saveDraftAuto",
		data : {
			receiver : $("#receiver").val(),
			cc : $("#cc").val(),
			subject : $("#subject").val(),
			content : $("#edit").val(),
			sender : $("#loginname").html(),
			filenamelist : $("#filenamelist").val(),
			messagenum : $("#messagenum").val()
		},
		dataType : "text",
		beforeSend : function(){
			$("#saved").css("display","none");
			$("#savefail").css("display","none");
			$("#saving").css("display","inherit");
		},
		success : function(data){
			var json_s=JSON.parse(data);
			if(json_s[0].flag=="true"){
				$("#saving").css("display","none");
				$("#saved").css("display","inherit");
			}
			else{
				$("#saving").css("display","none");
				$("#savefail").css("display","inherit");
			}
			
		}
	});
}

function getRootPath(){
    //��ȡ��ǰ��ַ���磺 http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //��ȡ�����ַ֮���Ŀ¼���磺 uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //��ȡ�����ַ���磺 http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //��ȡ��"/"����Ŀ���磺/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    projectName = "/design/";
    return(localhostPaht+projectName);
};