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
<link rel="shortcut icon" href="/forssh/img/board.ico">
<link href=/forssh/css/bootstrap.min.css type="text/css" rel=stylesheet>
<link href="/forssh/css/signin.css" rel="stylesheet">
<title>會員資料修改</title>
<head></head>
<body>
	<div class=container>
		<form name="f" class=form-signin role=form method="post">
			<s:token>

			</s:token>
			<h2 class=form-signin-heading>請完整輸入資料</h2>
			<h6 class=error2>
				<s:property value="errors.alterUserError" />
			</h6>
			<label for="txt_name" class=sr-only>會員名稱：</label> <label
				for="txt_mail" class=sr-only>會員信箱：</label> <label for="txt_phone"
				class=sr-only>會員手機：</label> <input type="text" name="user_name"
				name="id" class=form-control placeholder="UserName" required
				autofocus maxlength="30"
				value="<s:property value="user.user_name"/>" id="txt_name" /> <input
				type="text" name="user_mail" name="id" class=form-control
				placeholder="UserMail" required autofocus maxlength="30"
				value="<s:property value="user.user_mail"/>" id="txt_mail" /> <input
				type="text" name="user_phone" name="id" class=form-control
				placeholder="UserPhone" required autofocus maxlength="30"
				value="<s:property value="user.user_phone"/>" id="txt_phone" /> <input
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