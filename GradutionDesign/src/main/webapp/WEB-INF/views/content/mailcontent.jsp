<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/inbox.js"></script>
<div class="row">
	<div class="col-sm-11">
		<ol class="breadcrumb">
			<li><a style="cursor:pointer" id="thispage">Inbox</a></li>
			<li class="active">Content</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-sm-11">
		<div class="panel panel-default col-sm-12">
  			<div class="panel-body">
   				${content }
  			</div>
		</div>
	</div>
</div>
<input type="hidden" value="${page }" id="page" />