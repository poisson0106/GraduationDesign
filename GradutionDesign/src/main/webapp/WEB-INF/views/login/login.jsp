<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="js/tools/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/tools/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
<style>
body{
        background-color: #eee;
}
</style>
<title>Insert title here</title>
</head>
<body>
<div class="row">
        <div class="col-sm-offset-1 col-sm-11">
                <h1>Operating Management System</h1>
        </div>
</div>

<div class="row">
        <div class="col-sm-offset-1 col-sm-6">
                <div id="myCarousel" class="carousel slide">
                        <ol class="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class=""></li>
                                <li data-target="#myCarousel" data-slide-to="1" class=""></li>
                                <li data-target="#myCarousel" data-slide-to="2" class="active"></li>
                        </ol>
                        <div class="carousel-inner">
                                <div class="item">
                                        <img src="http://www.see-source.com/bootstrap/demo/images/bootstrap-mdo-sfmoma-01.jpg" alt="">
                                        <div class="carousel-caption">
                                                <h4>First Thumbnail label</h4>
                                                <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                        </div>
                                </div>
                                <div class="item">
                                        <img src="http://www.see-source.com/bootstrap/demo/images/bootstrap-mdo-sfmoma-02.jpg" alt="">
                                        <div class="carousel-caption">
                                                <h4>Second Thumbnail label</h4>
                                                <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                        </div>
                                </div>
                                <div class="item active">
                                        <img src="http://www.see-source.com/bootstrap/demo/images/bootstrap-mdo-sfmoma-03.jpg" alt="">
                                        <div class="carousel-caption">
                                                <h4>Third Thumbnail label</h4>
                                                <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                        </div>
                                </div>
                        </div>
                        <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
                        <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
                </div>
        </div>
        <div class="col-sm-4" style="height:100%;">
                <div class="panel panel-primary" style="height:100%">
                        <div class="panel-heading">Login:</div>
                        <div class="panel-body">
                                <c:if test="${not empty flag&&flag=='false' }">
                                        <div class="row">
                                                <div class="col-sm-12">
                                                        <p class="text-left text-danger">Password Error!</p>
                                                </div>
                                        </div>
                                </c:if>
                                <form class="form-horizontal" role="form" id="login_info" method="post" action="loginUser">
                                        <div class="form-group">
                                                <label for="username" class="col-sm-3 control-label">Username:</label>
                                                <div class="col-sm-8">
                                                        <input type="text" class="form-control" name="username" id="username"  data-content="It can't be blank." data-placement="left">
                                                </div>
                                        </div>
                                        <div class="form-group">
                                                <label for="password" class="col-sm-3 control-label">Password:</label>
                                                <div class="col-sm-8">
                                                        <input type="password" class="form-control" name="password" id="password" data-content="It can't be blank." data-placement="left">
                                                </div>
                                        </div>
                                        <div class="form-group">
                                                 <div class="col-sm-offset-3 col-sm-8">
                                                         <input type="button" class="btn btn-primary" value="login" id="login_button">
                                                         <input type="reset" class="col-sm-offset-1 btn btn-warning" value="reset"/>
                                                 </div>
                                        </div>
                                        <div class="form-group">
                                                <div class="col-sm-12">
                                                        <span class="glyphicon glyphicon-log-in"> </span>
                                                        <a href="forgetPassword">Forget password? Please click here</a>
                                                </div>
                                        </div>
                                        <div class="form-group">
                                                <div class="col-sm-12">
                                                        <span class="glyphicon glyphicon-log-in"> </span>
                                                        <a href="applyLogin">Haven't authority? Click this to apply</a>
                                                </div>
                                        </div>
                                </form>
                        </div>
                </div>
        </div>
</div>
</body>
<script type="text/javascript">
$(function(){
	 $('.carousel').carousel();
                
        $('#myCarousel').carousel('next');
        
        $('#username').change(function(){
                $("#username").popover('hide');
        });
        $('#login_button').click(function(){
                if($('#username').val()==''){
                        $("#username").popover('toggle');
                        $("#username").focus();
                        return false;
                }
                else if($('#password').val()==''){
                        $("#password").popover('toggle');
                        $("#password").focus();
                        return false;
                }
                $('#login_info').submit();
        });
});
</script>
</html>