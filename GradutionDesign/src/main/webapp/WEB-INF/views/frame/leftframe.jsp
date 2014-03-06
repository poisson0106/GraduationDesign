<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="panel panel-default">
  	<div class="panel-heading text-center">Main Menu</div>
 	<div class="panel-body">
   		<ul class="nav nav-pills nav-stacked">
  			<li class="active" id="homemenu"><a style="cursor:pointer" id="home">欢迎</a></li>
  			<li id="sendemailmenu"><a style="cursor:pointer" id="sendemail">写信</a></li>
  			<li id="inboxmenu"><a style="cursor:pointer" id="inbox"><c:if test="${nummail ne 0}"><span class="badge pull-right" id="inboxbage">${nummail }</span></c:if>收件箱</a></li>
  			<li id="delboxmenu"><a style="cursor:pointer" id="delbox">已删除</a></li>
  			<li id="draftboxmenu"><a style="cursor:pointer" id="draftbox">草稿箱</a></li>
  			<li id="sentboxmenu"><a style="cursor:pointer" id="sentbox">发件箱</a></li>
		</ul>
		<input type="hidden" value=${chosed } id="chosed">
  	</div>
</div>