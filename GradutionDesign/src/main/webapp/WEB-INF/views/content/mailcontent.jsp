<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/operation/boxcommons.js"></script>
<script type="text/javascript" src="js/operation/mailcontent.js"></script>
<div class="col-sm-12">
	<ol class="breadcrumb">
		<li><a style="cursor:pointer" id="thispage">后退</a></li>
		<li class="active">邮件正文</li>
	</ol>
</div>
<div class="col-sm-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title"><span class="glyphicon glyphicon-envelope">&nbsp</span>主题: <span id="subject">${mail.subject }</span></h3>
			<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-user">&nbsp</span>发件人: <span id="sender">${mail.sender }</span></h3>
    		<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-user">&nbsp</span>收件人: ${mail.receivers }</h3>
    		<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-user">&nbsp</span>抄送： <c:forEach var="item" items="${mail.cc }"><c:if test="${not empty item }">${item}</c:if></c:forEach></h3>
    		<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-time">&nbsp</span>收件日期: ${mail.date }</h3>
    		<p> </p>
    		<div class="btn-group">
    			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Forward"><span class="fa fa-share"></span><span class="fa fa-sort-asc"></span></button>
    			<ul class="dropdown-menu">
    				<c:if test="${mail.withattach }">
						<li><a style="cursor:pointer;" id="withatch">Forward with attachment</a></li>
					</c:if>
					<li><a style="cursor:pointer;" id="withoutatch">Forward without attachment</a></li>
				</ul>
			</div>
			<div class="btn-group">
				<button type="button" class="btn btn-default" title="Reply" id="reply"><span class="fa fa-reply"></span></button>
    		</div>
    		<c:if test="${mail.withattach }">
    			<div class="btn-group">
    				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" title="Attachment"><span class="fa fa-paperclip"></span></button>
    				<c:if test="${not empty mail.attachnames }">
    					<ul class="dropdown-menu" id="attach">
    						<c:forEach var="item" items="${mail.attachnames }">
    							<li><a style="cursor:pointer;">${item }</a></li>
    						</c:forEach>
    					</ul>
    				</c:if>
    			</div>
    		</c:if>
    	</div>
  		<div class="panel-body" id="mailcontent">
   			${mail.content }
  		</div>
	</div>
</div>
<input type="hidden" value="${page }" id="page" />
<input type="hidden" value="${mail.messagenum }" id="messagenum" />
<input type="hidden" value="${mail.frompage }" id="from" />
<form id="forwardwithoutatch" method="post" action="forwardWithoutAttachment" style="display:none">
	<input type="hidden" id="content" name="content" />
	<input type="hidden" id="fwsubject" name="subject" />
</form>
<form id="forwardwithatch" method="post" action="forwardWithAttachment" style="display:none">
	<input type="hidden" id="acontent" name="acontent" />
	<input type="hidden" id="afwsubject" name="asubject" />
	<input type="hidden" id="fnamelist" name="fnamelist" />
</form>