<script type="text/javascript" src="js/operation/inbox.js"></script>
<script type="text/javascript" src="js/operation/mailcontent.js"></script>
<div class="col-sm-12">
	<ol class="breadcrumb">
		<li><a style="cursor:pointer" id="thispage">Inbox</a></li>
		<li class="active">Content</li>
	</ol>
</div>
<div class="col-sm-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title"><span class="glyphicon glyphicon-envelope">&nbsp</span>Subject: <span id="subject">${mail.subject }</span></h3>
			<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-user">&nbsp</span>Sender: <span id="sender">${mail.sender }</span></h3>
    		<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-user">&nbsp</span>Receivers: ${mail.receivers }</h3>
    		<p> </p>
    		<h3 class="panel-title"><span class="glyphicon glyphicon-time">&nbsp</span>Date: ${mail.date }</h3>
    		<p> </p>
    		<div class="btn-group">
    			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Forward"><span class="fa fa-share"></span><span class="fa fa-sort-asc"></span></button>
    			<ul class="dropdown-menu">
					<li><a style="cursor:pointer;" id="withatch">Forward with attachment</a></li>
					<li><a style="cursor:pointer;" id="withoutatch">Forward without attachment</a></li>
				</ul>
				<button type="button" class="btn btn-default" title="Reply" id="reply"><span class="fa fa-reply"></span></button>
    		</div>
    	</div>
  		<div class="panel-body" id="mailcontent">
   			${mail.content }
  		</div>
	</div>
</div>
<input type="hidden" value="${page }" id="page" />
<form id="forwardwithoutatch" method="post" action="forwardWithoutAttachment" style="display:none">
	<input type="hidden" id="content" name="content" />
	<input type="hidden" id="fwsubject" name="subject" />
</form>