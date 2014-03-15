<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="js/tools/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/tools/bootstrap.min.js"></script>
<script type="text/javascript" src="js/operation/login.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
<title>Insert title here</title>
<style type="text/css">
body{
	background-image:url(img/background3.jpg);
}
</style>
</head>
<body>
<div class=row>
	<div class="col-sm-12 text-center">
		<img src="img/banner.gif" />
	</div>
</div>
<div class="row">
	<div class="col-sm-6 col-sm-offset-3" style="background-color:white;">
		<h1>用户注册</h1>
		<form class="form-horizontal" action="registerOneUser" method="post" role="form">
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">用户名:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="username" name="username" placeholder="输入用户名" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">密码:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password" name="password" placeholder="输入密码" />
				</div>
			</div>
			<div class="form-group">
				<label for="repassword" class="col-sm-2 control-label">重复密码:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="repassword" name="repassword" placeholder="再次输入密码" />
				</div>
			</div>
			<div class="form-group">
				<label for="question" class="col-sm-2 control-label">密保问题:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="question" name="question" placeholder="输入问题" />
				</div>
			</div>
			<div class="form-group">
				<label for="answer" class="col-sm-2 control-label">答案:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="answer" name="answer" placeholder="输入答案" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-2">
					<button type="submit" class="btn btn-primary">提交</button>
					
				</div>
				<div class="col-sm-1">
					<button type="reset" class="btn btn-danger">重置</button>
				</div>
			</div>
		</form>
	</div>
</div>
</body>
</html>