<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/operation/findpwd.js"></script>
<div class="row">
	<div class="col-sm-12">
		<img src="img/banner.gif" width="100%" />
	</div>
</div>
<div class="row">
	<div class="col-sm-offset-1">
		<h1>重置密码</h1>
	</div>
</div>
<form class="form-horizontal" action="findOnePassword" method="post" role="form">
	<div class="form-group">
		<label for="username" class="col-sm-2 control-label">请输入用户名：</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="username" placeholder="输入用户名" />
		</div>
		<div class="col-sm-3">
			<h4>@usstemail.com</h4>
		</div>
	</div>
	<div class="form-group">
		<label for="question" class="col-sm-2 control-label">密保问题:</label>
		<div class="col-sm-9">
			<input type="text" disabled="disabled" class="form-control" id="question" placeholder="请填完用户名后将光标移至答案框稍作等待，问题将自动读出" />
		</div>
	</div>
	<div class="form-group">
		<label for="answer" class="col-sm-2 control-label">答案:</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="answer" placeholder="请输入答案" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">更改密码:</label>
		<div class="col-sm-9">
			<input type="password" class="form-control" id="password" placeholder="请输入密码" />
		</div>
	</div>
	<div class="form-group">
		<label for="repassword" class="col-sm-2 control-label">确认密码:</label>
		<div class="col-sm-9">
			<input type="password" class="form-control" id="repassword" placeholder="请再次输入密码" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-2 text-right">
			<button type="submit" class="btn btn-primary">提交</button>

		</div>
		<div class="col-sm-1">
			<button type="reset" class="btn btn-danger">重置</button>
		</div>
	</div>
</form>