<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	errorPage="error.jsp"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link rel="shortcut icon" href="../img/board.ico">
<link href=/forssh/css/bootstrap.min.css type="text/css" rel=stylesheet>
<link href="/forssh/css/signin.css" rel="stylesheet">
<title>會員密碼修改</title>
<head></head>
<body>

	<div class=container>
		<form name="f" class=form-signin role=form method="post">
			<s:token>

			</s:token>
			<h2 class=form-signin-heading>請輸入新舊密碼</h2>
			<h6 class=error2>
				<s:property value="errors.alterUserError" />
			</h6>
			<label for="txt_oldPassword" class=sr-only>原密碼：</label> <label
				for="txt_password" class=sr-only>新密碼：</label> <label
				for="txt_password2" class=sr-only>再次確認新密碼：</label> <input
				type="password" name="user_oldPassword" id="txt_oldPassword" maxlength="30"
				class=form-control placeholder=OldPassword required /> <input maxlength="30"
				type="password" name="user_password" id="txt_password"
				class=form-control placeholder=NewPassword required /> <input maxlength="30"
				type="password" name="user_password2" id="txt_password2"
				class=form-control placeholder=ConfrimNewPassword required /> <input
				type="button" value="確認修改" class="btn btn-lg btn-primary btn-block"
				onclick="javascript:if(confirm('確認修改嗎？')){document.f.action='<%=basePath%>user/confirmalter';document.f.submit();}" />
			<input type="button" value="回前一頁"
				class="btn btn-lg btn-primary btn-block"
				onclick="javascript:document.f.action='<%=basePath%>user/lookuser';document.f.submit();" />
		</form>
	</div>
</body>
<script src="/forssh/js/bootstrap.min.v1.js"></script>
<script src="/forssh/js/jquery.min.js"></script>
<script language="javascript">
	$(document).ready(intoAlter);
	function intoAlter() {
		$('.error2').show();
	}
</script>
</html>