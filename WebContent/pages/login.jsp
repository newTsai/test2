<%@ page language="java" contentType="text/html; charset=BIG5"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link rel="shortcut icon" href="/forssh/img/board.ico">
<link href=/forssh/css/bootstrap.min.css type="text/css" rel=stylesheet>
<link href=/forssh/css/signin.css type="text/css" rel=stylesheet>
<head></head>
<meta charset=utf-8>
<title>公告欄系統</title>
<body>
	<div class=container>
		<form name="f" class=form-signin role=form method="post">
			<s:token>
			</s:token>
			<input id="confirm" type="hidden" value="<%=basePath%>user/login">
			<input id="sid" type="hidden"
				value="<s:property value="#session.id"/>"> <input id="into"
				type="hidden"
				value="<%=basePath%>board/changepage?page=home" />
			<h2 class=form-signin-heading>請登入</h2>
			<h6 class=error>[帳號密碼不得有空]</h6>
			<h6 class=error2>
				<s:property value="errors.loginError" />
			</h6>
			<label for=txt_id class=sr-only>帳號</label> <input type=text id=txt_id maxlength="30"
				name="id" class=form-control placeholder="UserId" required autofocus>
			<label for=txt_password class=sr-only>密碼</label> <input type=password maxlength="30"
				id=txt_password name="password" class=form-control
				placeholder=Password required>
			<div class=checkbox>
				 <label> 
				</label>
				<a href="<%=basePath%>user/createaccount">創建帳號</a>
			</div>
			<input type="button" value="登入"
				class="btn btn-lg btn-primary btn-block"
				onclick="javascript:signin()">
		</form>
	</div>
	<script src="/forssh/js/bootstrap.min.v1.js"></script>
	<script src="/forssh/js/jquery.min.js"></script>
	<script language="javascript">
		$(document).ready(intoBoard);
		function intoBoard() {
			$('.error').hide();
			$('.error2').fadeIn('slow');
			var hasId = document.getElementById("sid").value;
			if (hasId != "") {
				document.f.action = document.getElementById("into").value;
				document.f.submit();
			} else {
			}
		}
		function signin() {
			var id = document.getElementById("txt_id").value;
			var password = document.getElementById("txt_password").value;
			if (id.length > 0 && password.length > 0) {
				document.f.action = document.getElementById("confirm").value;
				document.f.submit();
				$('.error').hide();
				$('.error2').fadeIn('slow');
			} else {
				$('.error2').hide();
				$('.error').fadeIn('slow');
			}
		}
		
	</script>
	</html>