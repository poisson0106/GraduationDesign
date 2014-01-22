<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/operation/delbox.js"></script>
<script type="text/javascript" src="js/operation/pagination.js"></script>
<script type="text/javascript" src="js/tools/jquery.blockUI.js"></script>
<div class="row">
	<div class="col-sm-12">
		<ol class="breadcrumb">
			<li class="active">Deleted Box</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<table class="table table-hover" id="delbox">
		<c:if test="${not empty mail }">
			<thead>
				<tr>
					<th><input type="checkbox" id="selectall"></th>
					<th>Sender</th>
					<th>Subject</th>
					<th>Send Date</th>
				</tr>
			</thead>
		</c:if>
		<c:forEach var="item" items="${mail }">
			<c:choose>
				<c:when test="${item.flags }">
					<tr style="font-weight:bold;cursor:pointer">
				</c:when>
				<c:otherwise>
					<tr style="cursor:pointer;">
				</c:otherwise>
			</c:choose>
			<td><input type="checkbox" class="mailselected" value=${item.messagenum }></td>
			<td class="mailcontent">${item.sender }</td>
			<td class="mailcontent">${item.subject }</td>
			<td class="mailcontent">${item.date }</td>
			<td><input type="hidden" value=${item.messagenum } /></td>
		</tr>
		</c:forEach>
	</table>
	</div>
</div>
<div class="row">
	<div class="col-sm-6 text-right">
		<ul class="pagination">
		
		</ul>
	</div>
	<div class="col-sm-6 text-left">
		<div class="col-sm-3">
			<div class='input-group' style='width:100px;margin-top:20px;'>
				<input type='text' class='form-control' id='gopagenum'>
				<span class='input-group-btn'>
					<button class='btn btn-default' type='button' id='go'>Go!</button>
				</span>
			</div>
		</div>
		<div class="col-sm-9 text-left" style="margin-top:25px;">
			<strong>Total Page:</strong>
			<span class="text-muted">${allpagenum}</span>
		</div>
	</div>
</div>
<input type="hidden" value="${allpagenum}" id="allpagenum" />
<input type="hidden" value="${page }" id="page" />