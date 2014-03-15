<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="js/tools/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/tools/bootstrap.min.js"></script>
<script type="text/javascript" src="js/operation/login.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-theme.min.css">
<title>登陆</title>
<style type="text/css">
body{
	background-image:url(img/background3.jpg);
}
</style>
</head>
<body>
	<div class="row">
		<nav class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">上海理工大学邮件系统</a>
		</nav>
	</div>
	<div class="row">
		<div class="col-sm-offset-4 col-sm-4" style="height: 100%;">
			<div class="panel panel-primary" style="height: 100%">
				<div class="panel-heading">Login:</div>
				<div class="panel-body">
					<c:if test="${not empty flag&&flag=='false' }">
						<div class="row">
							<div class="col-sm-12">
								<p class="text-left text-danger">Password Error!</p>
							</div>
						</div>
					</c:if>
					<form class="form-horizontal" role="form" id="login_info"
						method="post" action="loginOneUser">
						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">Username:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="username"
									id="username" data-content="It can't be blank."
									data-placement="left">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">Password:</label>
							<div class="col-sm-8">
								<input type="password" class="form-control" name="password"
									id="password" data-content="It can't be blank."
									data-placement="left">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-8">
								<input type="button" class="btn btn-primary" value="login"
									id="login_button"> <input type="reset"
									class="col-sm-offset-1 btn btn-warning" value="reset" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<span class="glyphicon glyphicon-log-in"> </span> <a
									href="forgetPassword">Forget password? Please click here</a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<span class="glyphicon glyphicon-log-in"> </span> <a
									href="initialUserRegister">Haven't account? Click this to apply</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>