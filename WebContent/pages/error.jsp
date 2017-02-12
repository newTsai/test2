<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>公告欄異常</title>
<link rel="shortcut icon" href="/forssh/img/board.ico">
</head>
<body>
	<%
		session.removeAttribute("id");
		session.removeAttribute("nowPage");
		session.removeAttribute("totalPages");
		session.removeAttribute("boards");
		session.removeAttribute("name");
	%>
	<div class="center">
		<center>
			<h1>網頁發生一些錯誤，請重新登入</h1>
			<a href="<%=basePath%>">重新登入</a>
		</center>
	</div>
</body>
<style type="text/css">
a {
	color: black;
	text-decoration: none;
}

a:hover {
	color: #c4cdd1;
	text-decoration: none;
}
</style>
<style>
.center {
	margin-top: 7.5%
}
</style>
</html>