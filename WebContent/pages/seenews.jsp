<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	errorPage="error.jsp"%>
<%@ page import="com.opensymphony.xwork2.util.*"%>
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
<link href="/forssh/css/dashboard.css" rel="stylesheet">
<link href="/forssh/css/board.css" rel="stylesheet">
<head>
<title>查看公告</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div id="navbar" class="navbar-collapse collapse">
			<div class="navbar-header">
				<a class="navbar-brand"
					href="<%=basePath%>board/changepage?page=home">首頁</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a style="color:#9d9d9d;">歡迎!<s:property value="#session.name" /></a></li>
				<li><a href="<%=basePath%>user/lookuser">會員資訊</a></li>
				<li><a href="<%=basePath%>user/logout">登出</a></li>
			</ul>
			
		</div>
	</div>
	</nav>
	<form name="f" action="back" method="post">
		<s:token>

		</s:token>
		<table class="table table-bordered">
			<thead>
				<tr>
					<td><label for="title">標題</label></td>
					<td><s:property value="title" /></td>
				</tr>
				<tr>
					<td><label for="modifyTime">修改時間</label></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss" name="modifyTime" /></td>
				</tr>
				<tr>
					<td><label for="deadline">截止日期</label></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss" name="deadline" /></td>
				</tr>
				<tr>
					<td><label for="author_name">發布者</label></td>
					<td><s:property value="author_name" /></td>
				</tr>
			</thead>
			<%
				ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");
				String content = (String) vs.findValue("content");
			%>

			<tbody>
		<tr>
				<td colspan="2"><div><%=content%></div></td>
			</tr>
			</tbody>
			</table>
	</form>
	<div class=area>
		<div class=changepage>
			<ul>
				<s:if test="#session.id!=author_id">
					<li><input type="button" value="回前一頁"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:document.f.action='<%=basePath%>board/changepage?page=selectedPage';document.f.submit();" />
					</li>
				</s:if>
				<s:elseif test="#session.id==user_id">
					<li><input type="button" value="回前一頁"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:document.f.action='<%=basePath%>user/lookuser';document.f.submit();" />
					</li>
				</s:elseif>
				<s:else>
					<li><input type="button" value="回前一頁"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:document.f.action='<%=basePath%>board/changepage?page=selectedPage';document.f.submit();" />
					</li>
				</s:else>
				<s:if test="#session.id==user_id">
					<li><input type="button" value="修改公告"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:document.f.action='<%=basePath%>board/alternews?id=<s:property value="id"/>&user_id=<s:property value="user_id"/>';document.f.submit();" />
					</li>
				</s:if>
				<s:elseif test="#session.id==author_id">
					<li><input type="button" value="修改公告"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:document.f.action='<%=basePath%>board/alternews?id=<s:property value="id"/>';document.f.submit();" />
					</li>
				</s:elseif>
				<s:if test="#session.id==author_id&&author_id!=user_id">
					<li><input type="button" value="刪除公告"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:if(confirm('確認刪除嗎？')){document.f.action='<%=basePath%>board/deletenews?id=<s:property value="id"/>';document.f.submit();}" />
					</li>
				</s:if>
				<s:elseif test="#session.id==user_id&&author_id==user_id">
					<li><input type="button" value="刪除公告"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:if(confirm('確認刪除嗎？')){document.f.action='<%=basePath%>board/deletemynews?id=<s:property value="id"/>';document.f.submit();}" />
					</li>
				</s:elseif>
			</ul>
		</div>
	</div>
</body>
<script src="/forssh/js/bootstrap.min.v1.js"></script>
<script src="/forssh/js/jquery.min.js"></script>
</html>