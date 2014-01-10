<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/inbox.js"></script>
<div class="row">
	<div class="col-sm-12">
		<ol class="breadcrumb">
			<li class="active">Inbox</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<table class="table" id="inbox">
		<c:if test="${not empty mail }">
			<thead>
				<tr>
					<th>Sender</th>
					<th>Subject</th>
					<th>Send Date</th>
				</tr>
			</thead>
		</c:if>
		<c:forEach var="item" items="${mail }">
			<c:choose>
				<c:when test="${item.flag }">
					<tr style="font-weight:bold;cursor:pointer">
				</c:when>
				<c:otherwise>
					<tr style="cursor:pointer;">
				</c:otherwise>
			</c:choose>
			<td>${item.sender }</td>
			<td>${item.subject }</td>
			<td>${item.date }</td>
			<td><input type="hidden" value=${item.messagenum } /></td>
			<%-- <td>${item.content }</td>
			<td>${item.messagenum }</td>
			<td>${item.flag }</td>
			<td>
				<c:forEach var="receiver" items="${item.receivers }">
					${receiver }
				</c:forEach>
			</td> --%>	
		</tr>
		</c:forEach>
	</table>
	</div>
</div>
<div class="row text-center">
	<ul class="pagination">
		
	</ul>
</div>
<input type="hidden" value="${allpagenum}" id="allpagenum" />
<input type="hidden" value="${page }" id="page" />