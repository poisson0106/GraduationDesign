<html>
<script type="text/javascript" src="js/jquery.hotkeys.js"></script>
<script type="text/javascript" src="js/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="js/sendbox.js"></script>
<div class="row">
	<div class="col-sm-12">
		<ol class="breadcrumb">
			<li class="active">Send&nbspBox</li>
		</ol>
	</div>
</div>
<form class="form-horizontal" role="form">
	<div class="form-group">
		<div class="col-sm-3">
			<button type="submit" class="btn btn-success">Send</button>
		</div>
	</div>
	<div class="form-group">
		<label for="inputEmail3" class="col-sm-1 control-label" style="text-align:left;">Receiver:</label>
		<div class="col-sm-11">
			<input type="email" class="form-control" id="inputEmail3"
				placeholder="Receiver">
		</div>
	</div>
	<div class="form-group">
		<label for="inputPassword3" class="col-sm-1 control-label" style="text-align:left;">Subject:</label>
		<div class="col-sm-11">
			<input type="password" class="form-control" id="inputPassword3"
				placeholder="Subject">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-12">
			<div id="editor" style="border: 1px solid rgb(204, 204, 204);height:300px;overflow:auto"></div>
		</div>
	</div>
</form>
</html>