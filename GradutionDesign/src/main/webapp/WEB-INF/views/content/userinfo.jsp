<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/operation/userinfo.js"></script>
<div class="col-sm-12">
	<div class="col-sm-8 col-sm-offset-2">
		<h3>用户信息更改 <small>仅修改需要修改部分，不需要修改部分请保留勿删除</small></h3>
	</div>
</div>
<form role="form" class="form-horizontal" method="post" action="changeUserInfo" id="changeuserinfo">
	<div class="form-group">
		<label class="control-label col-sm-2" for="username">用户名:</label>
		<div class="col-sm-3">
			<input type="text" class="form-control" id="username" name="username" value="${username }" disabled="disabled" />
		</div>
		<label class="control-label col-sm-2" for="nickname">昵称：</label>
		<div class="col-sm-3">
			<input type="text" class="form-control" id="nickname" name="nickname" value="${nickname }"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="ischangepwd">是否要更改密码:</label>
		<div class="col-sm-10">
			<label class="radio-inline">
				<input type="radio" name="ischangepwd" class="ischangepwd" value="yes" checked/>是
			</label>
			<label class="radio-inline">
				<input type="radio" name="ischangepwd" class="ischangepwd" value="no" />否
			</label>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="opassword">请输入原密码：</label>
		<div class="col-sm-3">
			<div class="input-group">
				<input type="password" class="form-control" id="opassword" name="opassword" value="">
				<span class="input-group-btn">
        			<button class="btn btn-default" type="button" id="checkpwd">校验密码</button>
      			</span>
			</div>
		</div>
		<div class="col-sm-7">
			<span class="fa fa-check-circle fa-2x" style="display:none"></span>
			<span class="fa fa-times-circle fa-2x" style="display:none"></span> 
			<span class="fa fa-spinner fa-spin fa-2x" style="display:none"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="npassword">请输入新密码：</label>
		<div class="col-sm-3">
			<input type="password" class="form-control" id="npassword" name="npassword" value="" disabled="disabled">
		</div>
		<label class="control-label col-sm-2" for="rpassword">请再输入新密码：</label>
		<div class="col-sm-3">
			<input type="password" class="form-control" id="rpassword" name="rpassword" value="" disabled="disabled">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="npassword">请输入新问题：</label>
		<div class="col-sm-3">
			<input type="text" class="form-control" id="nquestion" name="nquestion" value="${question }">
		</div>
		<label class="control-label col-sm-2" for="nanswer">请再输入新答案：</label>
		<div class="col-sm-3">
			<input type="text" class="form-control" id="nanswer" name="nanswer" value="${answer }">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-3 col-sm-offset-7 text-right">
			<button class="btn btn-primary" id="submit">提交</button>
			<button class="btn btn-danger" id="discard">放弃</button>
		</div>
	</div>
</form>