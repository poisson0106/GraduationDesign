<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="js/tools/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/tools/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
<title>用户注册</title>
<style type="text/css">
body{
	background-image:url(img/pic10.jpg);
	background-size:cover;
	overflow:hidden;
}
</style>
</head>
<body>
<!-- <div class=row>
	<div class="col-sm-12 text-center">
		<img src="img/banner.gif" />
	</div>
</div> -->
<div class="row">
	<nav class="navbar navbar-inverse" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">上海理工大学邮件系统</a>
	</nav>
</div>
<div class="row">
	<div class="col-sm-10 col-sm-offset-1" style="background-color:white;" id="UserContainer">
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</div>
</div>

</body>
</html>