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
<title>查看公告</title>
<link rel="shortcut icon" href="../img/board.ico">
</head>
<body>
	<div class="center">
		<center>
			<h1>此公告已不存在，回上一頁</h1>
			<a href="<%=basePath%>board/changepage?page=selectedPage">回上一頁</a>
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