<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/operation/register.js"></script>
<div class="row">
	<div class="col-sm-12">
		<img src="img/banner.gif" width="100%" />
	</div>
</div>
<div class="row">
	<div class="col-sm-offset-1">
		<h1>用户注册</h1>
	</div>
</div>
<form class="form-horizontal" action="registerOneUser" method="post"
	role="form" id="registeruser">
	<div class="form-group">
		<label for="username" class="col-sm-2 control-label">用户名:</label>
		<div class="col-sm-5">
			<input type="text" class="form-control" id="username" name="username"
				placeholder="输入用户名" />
		</div>
		<div class="col-sm-2">
			<strong>@usstemail.com</strong>
		</div>
		<div class="col-sm-1">
			<span class="fa fa-check-circle fa-2x" style="display:none"></span>
			<span class="fa fa-times-circle fa-2x" style="display:none"></span> 
			<span class="fa fa-spinner fa-spin fa-2x"style="display:none"></span>
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">密码:</label>
		<div class="col-sm-9">
			<input type="password" class="form-control" id="password"
				name="password" placeholder="输入密码" />
		</div>
	</div>
	<div class="form-group">
		<label for="repassword" class="col-sm-2 control-label">重复密码:</label>
		<div class="col-sm-9">
			<input type="password" class="form-control" id="repassword"
				name="repassword" placeholder="再次输入密码" />
		</div>
	</div>
	<div class="form-group">
		<label for="question" class="col-sm-2 control-label">密保问题:</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="question" name="question"
				placeholder="输入问题" />
		</div>
	</div>
	<div class="form-group">
		<label for="answer" class="col-sm-2 control-label">答案:</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="answer" name="answer"
				placeholder="输入答案" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-2 text-right">
			<button type="button" class="btn btn-primary" id="submit">提交</button>

		</div>
		<div class="col-sm-1">
			<button type="reset" class="btn btn-danger">重置</button>
		</div>
	</div>
</form>