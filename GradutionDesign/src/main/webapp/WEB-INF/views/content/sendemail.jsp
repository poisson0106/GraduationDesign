<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/tools/jquery.hotkeys.js"></script>
<script type="text/javascript" src="js/tools/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="js/tools/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="js/operation/sendemail.js"></script>
<link rel="stylesheet" type="text/css" href="css/uploadify.css" />
<div class="row">
	<div class="col-sm-12">
		<ol class="breadcrumb">
			<li class="active">写信</li>
		</ol>
	</div>
</div>
<form class="form-horizontal" role="form" method="post" action="sendOneEmail" enctype="multipart/form-data">
	<div class="form-group">
		<div class="col-sm-3">
			<input type="button" class="btn btn-success" id="send" value="发送" />
		</div>
	</div>
	<div class="form-group">
		<label for="receiver" class="col-sm-1 control-label" style="text-align:left;">收件人:</label>
		<div class="col-sm-11">
			<input type="text" class="form-control" id="receiver" placeholder="Receiver" name="receiver" value="${receiver }">
		</div>
	</div>
	<div class="form-group">
		<label for="subject" class="col-sm-1 control-label" style="text-align:left;">主题:</label>
		<div class="col-sm-11">
			<input type="text" class="form-control" id="subject" placeholder="Subject" name="subject" value="${subject }">
		</div>
	</div>
	<div class="form-group">
		<input type="hidden" class="form-control" id="content" name="content">
		<input type="hidden" class="form-control" id="sender" name="sender">
		<input type="hidden" class="form-control" id="filenamelist" name="filenamelist">
	</div>
</form>
<div class="row">
	<div class="col-sm-12">
			<div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
				<div class="btn-group">
        			<button class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Font"><span class="fa fa-font"></span><span class="fa fa-sort-asc"></span></button>
          			<ul class="dropdown-menu">
            			<li><a data-edit="fontName Serif" style="font-family:'Serif'">Serif</a></li>
            			<li><a data-edit="fontName Sans" style="font-family:'Sans'">Sans</a></li>
            			<li><a data-edit="fontName Arial" style="font-family:'Arial'">Arial</a></li>
            			<li><a data-edit="fontName Arial Black" style="font-family:'Arial Black'">Arial Black</a></li>
            			<li><a data-edit="fontName Courier" style="font-family:'Courier'">Courier</a></li>
            			<li><a data-edit="fontName Courier New" style="font-family:'Courier New'">Courier New</a></li>
            			<li><a data-edit="fontName Comic Sans MS" style="font-family:'Comic Sans MS'">Comic Sans MS</a></li>
            			<li><a data-edit="fontName Helvetica" style="font-family:'Helvetica'">Helvetica</a></li>
            			<li><a data-edit="fontName Impact" style="font-family:'Impact'">Impact</a></li>
            			<li><a data-edit="fontName Lucida Grande" style="font-family:'Lucida Grande'">Lucida Grande</a></li>
            			<li><a data-edit="fontName Lucida Sans" style="font-family:'Lucida Sans'">Lucida Sans</a></li>
            			<li><a data-edit="fontName Tahoma" style="font-family:'Tahoma'">Tahoma</a></li>
            			<li><a data-edit="fontName Times" style="font-family:'Times'">Times</a></li>
            			<li><a data-edit="fontName Times New Roman" style="font-family:'Times New Roman'">Times New Roman</a></li>
            			<li><a data-edit="fontName Verdana" style="font-family:'Verdana'">Verdana</a></li>
          			</ul>
          		</div>
				<div class="btn-group">
        			<button class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Font Size"><span class="fa fa-text-height"></span><span class="fa fa-sort-asc"></span></button>
          				<ul class="dropdown-menu">
          					<li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
          					<li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
          					<li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
          				</ul>
      			</div>
			
				<div class="btn-group">
					<button type="button" class="btn btn-default" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><span class="fa fa-bold"></span></button>
        			<button type="button" class="btn btn-default" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><span class="fa fa-italic"></span></button>
        			<button type="button" class="btn btn-default" data-edit="strikethrough" title="Strikethrough"><span class="fa fa-strikethrough"></span></button>
        			<button type="button" class="btn btn-default" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><span class="fa fa-underline"></span></button>
				</div>
				
				<div class="btn-group">
        			<button type="button" class="btn btn-default" data-edit="insertunorderedlist" title="Bullet list"><span class="fa fa-list-ul"></span></button>
        			<button type="button" class="btn btn-default" data-edit="insertorderedlist" title="Number list"><span class="fa fa-list-ol"></span></button>
        			<button type="button" class="btn btn-default" data-edit="outdent" title="Reduce indent (Shift+Tab)"><span class="fa fa-indent"></span></button>
        			<button type="button" class="btn btn-default" data-edit="indent" title="Indent (Tab)"><span class="fa fa-outdent"></span></button>
     			</div>
				
				<div class="btn-group">
        			<button type="button" class="btn btn-default" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><span class="fa fa-align-left"></span></button>
        			<button type="button" class="btn btn-default" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><span class="fa fa-align-center"></span></button>
        			<button type="button" class="btn btn-default" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><span class="fa fa-align-right"></span></button>
        			<button type="button" class="btn btn-default" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><span class="fa fa-align-justify"></span></button>
      			</div>

				<div class="btn-group" id="grouphyperlink">
					<button type="button" class="btn btn-default" title="Hyperlink" id="hyperlink"><span class="fa fa-link"></span></button>
					<ul class="dropdown-menu">
						<li>
							<input class="col-sm-9" placeholder="URL" type="text" id="hyperlinktext" />
							<button class="btn btn-default btn-xs" type="button" id="addhyperlink">添加</button>
						</li>
					</ul>
					<button type="button" class="btn btn-default" data-edit="unlink" title="Remove Hyperlink"><span class="fa fa-cut"></span></button>
				</div>

				<div class="btn-group">
					<a type="button" class="btn btn-default" title="Insert picture (or just drag & drop)" id="pictureBtn"><span class="fa fa-picture-o"></span></a>
					<input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" style="display:none" id="picup"/>
				</div>

				<div class="btn-group">
        			<button type="button" class="btn btn-default" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><span class="fa fa-undo"></span></button>
        			<button type="button" class="btn btn-default" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><span class="fa fa-repeat"></span></button>
      			</div>
      			
      			<div class="btn-group">
      				<button type="button" class="btn btn-default" title="Attachment" id="addattch"><span class="fa fa-paperclip"></span></button>
      				<ul class="dropdown-menu text-center">
      					<li class="col-sm-12">附件数请小于5个</li>
      					<li class="col-sm-12">
      						<input type="file" name="uploadify" id="file_upload"/>
      					</li>
      					<li class="col-sm-12">
      							<input type="button" id="uploadbtn" class="btn btn-primary" value="upload"/>
      					</li>
      				</ul>
      			</div>
      			
      			<c:if test="${mail.withattach }">
      				<div class="btn-group">
      					<!-- need to add a filenamelist dropdown -->
      					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Attachment List"><span class="fa fa-bars"></span></button>
      					<c:if test="${not empty mail.attachnames }">
    						<ul class="dropdown-menu" id="fnamelist">
    							<c:forEach var="item" items="${mail.attachnames }">
    								<li>${item }</li>
    							</c:forEach>
    						</ul>
    					</c:if>
      				</div>
      			</c:if>
			</div>
			
			
			<div id="editor" style="border: 1px solid rgb(204, 204, 204);height:300px;overflow:auto"><c:if test="${not empty content }"></br><p>--------forward---------</p>${content }</c:if></div>
	</div>
</div>