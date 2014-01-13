<script type="text/javascript" src="js/inbox.js"></script>
<div class="col-sm-12">
	<ol class="breadcrumb">
		<li><a style="cursor:pointer" id="thispage">Inbox</a></li>
		<li class="active">Content</li>
	</ol>
</div>
<div class="col-sm-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title"><span class="glyphicon glyphicon-envelope">&nbsp</span>Subject: ${mail.subject }</h3>
			<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-user">&nbsp</span>Sender: ${mail.sender }</h3>
    		<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-user">&nbsp</span>Receivers: ${mail.receivers }</h3>
    		<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-time">&nbsp</span>Date: ${mail.date }</h3>
  		</div>
  		<div class="panel-body">
   			${mail.content }
  		</div>
	</div>
</div>
<input type="hidden" value="${page }" id="page" />