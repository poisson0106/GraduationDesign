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
	overflow:hidden;
}
.navbar{
	margin-bottom: 0px;
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
		<div class= "col-sm-12" style="height:150px;">
			 <div id="myCarousel" class="carousel slide">
                      <!--   <ol class="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class=""></li>
                                <li data-target="#myCarousel" data-slide-to="1" class=""></li>
                                <li data-target="#myCarousel" data-slide-to="2" class=""></li>
                                <li data-target="#myCarousel" data-slide-to="3" class="active"></li>
                        </ol> -->
                        <div class="carousel-inner">
                                <div class="item">
                                        <img src="img/pic10.jpg" alt="" width="100%">
                                </div>
                                <div class="item">
                                        <img src="img/pic6.jpg" alt="" width="100%">
                                </div>
                                <div class="item">
                                        <img src="img/pic7.jpg" alt="" width="100%">
                                </div>
                                <div class="item active">
                                        <img src="img/pic8.jpg" alt="" width="100%">
                                </div>
                        </div>
                        <a class="left carousel-control" href="#myCarousel" data-slide="prev"></a>
                        <a class="right carousel-control" href="#myCarousel" data-slide="next"></a>
                </div>
		</div>
		<div class="col-sm-offset-7 col-sm-4" style="height: 100%;z-index:100">
			<div class="panel panel-primary" style="height: 100%">
				<div class="panel-heading">用户登录:</div>
				<div class="panel-body">
					<c:if test="${not empty flag&&flag=='false' }">
						<div class="row">
							<div class="col-sm-12">
								<p class="text-left text-danger">密码错误！</p>
							</div>
						</div>
					</c:if>
					<form class="form-horizontal" role="form" id="login_info"
						method="post" action="loginOneUser">
						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">用户名:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="username"
									id="username" data-content="用户名不能为空"
									data-placement="left">
							</div>
							<div class="col-sm-3"><h5>@usstemail.com</h5></div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">密码:</label>
							<div class="col-sm-8">
								<input type="password" class="form-control" name="password"
									id="password" data-content="密码不能为空"
									data-placement="left">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-8">
								<input type="button" class="btn btn-primary" value="登陆"
									id="login_button"> <input type="reset"
									class="col-sm-offset-1 btn btn-warning" value="重置" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<span class="glyphicon glyphicon-log-in"> </span> <a
									href="initialFindPassword">忘记密码？</a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<span class="glyphicon glyphicon-log-in"> </span> <a
									href="initialUserRegister">没有账号？快点此来申请！</a>
							</div>
						</div>
					</form>
					<div class="col-sm-12">
						<strong class="text-danger">注意：请使用IE10及以上，firefox或者chrome浏览器访问本系统，否则部分功能无法使用！</strong>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>