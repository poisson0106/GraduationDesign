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
});

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