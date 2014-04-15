<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/operation/contactmanagement.js"></script>
<script type="text/javascript" src="js/operation/paginationforcontact.js"></script>
<script type="text/javascript" src="js/tools/jquery.blockUI.js"></script>
<div class="row">
	<div class="col-sm-12">
		<ol class="breadcrumb">
			<li class="active">联系人管理</li>
		</ol>
	</div>
</div>
<c:if test="${not empty contacts }">
<div class="row">
	<div class="col-sm-12">
		<div class="btn-group">
			<button type="button" class="btn btn-default" id="delete"><span class="fa fa-trash-o"></span></button>
		</div>
		<div class="btn-group">
			<button type="button" class="btn btn-default" id="refresh"><span class="fa fa-refresh"></span></button>
		</div>
	</div>
</div>
</c:if>
<div class="row">
	<div class="col-sm-12">
		<table class="table table-hover" id="contacts">
		<c:if test="${not empty contacts }">
			<thead>
				<tr>
					<th><input type="checkbox" id="selectall"></th>
					<th>联系人昵称</th>
					<th>联系人邮箱</th>
				</tr>
			</thead>
		</c:if>
		<c:forEach var="item" items="${contacts }">
		<tr>
			<td><input type="checkbox" class="contactselected" value=${item.username }></td>
			<td>${item.nickname }</td>
			<td>${item.username }@usstemail.com</td>
		</tr>
		</c:forEach>
	</table>
	</div>
</div>
<c:if test="${not empty contacts }">
<div class="row">
	<div class="col-sm-7 text-right">
		<ul class="pagination">
		
		</ul>
	</div>
	<div class="col-sm-5 text-left">
		<div class="col-sm-3">
			<div class='input-group' style='width:100px;margin-top:20px;'>
				<input type='text' class='form-control' id='gopagenum'>
				<span class='input-group-btn'>
					<button class='btn btn-default' type='button' id='go'>Go!</button>
				</span>
			</div>
		</div>
		<div class="col-sm-9 text-left" style="margin-top:25px;">
			<strong>总页面数:</strong>
			<span class="text-muted">${allpagenum}</span>
		</div>
	</div>
</div>
</c:if>
<c:if test="${empty contacts }"><h1 class="text-center">暂时没有联系人，当你发送邮件时联系人会自动添加！</h1></c:if>
<div id="deletesuccess" style="display: none; align: center;">删除成功</div>
<input type="hidden" value="${allpagenum}" id="allpagenum" />
<input type="hidden" value="${page }" id="page" />