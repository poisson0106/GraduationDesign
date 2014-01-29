$(function(){
	$("#editor").wysiwyg();
	
	$("li.active").removeClass();
	$("#sendemailmenu").addClass("active");
	
	$("#file_upload").uploadify({
        'buttonText'    : 'Add Attach',  
        'swf'           : getRootPath()+'swf/uploadify.swf',  
        'uploader'      : getRootPath()+'/uploadAttachment',  
        'auto'          : false, 
        'fileSizeLimit' : '30720KB', 
        'fileTypeExts'  : '*.doc; *.jpg; *.rar; *.zip', 
        'cancelImg' :  getRootPath()+'/img/uploadify-cancel.png',
        'uploadLimit' : 5, 
        'formData'      : {'foldername':$("#loginname").html()},
        'onUploadComplete':function(){  
        	$(this).parent().parent().removeClass("dropdown open");
        }
    });  
	
});

$(function(){
	$("#send").click(function(){
		var content=$("#editor").html();
		content="<html><body><div>"+content+"</div></body></html>";
		$("#content").val(content);
		var sender=$("#loginname").html();
		$("#sender").val(sender);
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
	
	//用图片打开上传窗口
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
});

function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    projectName = "/design/";
    return(localhostPaht+projectName);
};