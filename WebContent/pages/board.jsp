<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	errorPage="error.jsp"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link rel="shortcut icon" href="/forssh/img/board.ico">
<link href=/forssh/css/bootstrap.min.css type="text/css" rel=stylesheet>
<link href="/forssh/css/dashboard.css" rel="stylesheet">
<link href="/forssh/css/board.css" rel="stylesheet">
<head>
<base href="<%=basePath%>" />
<title>瀏覽最新公告事項</title>
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
				<li><a style="color: #9d9d9d;">歡迎!<s:property
							value="#session.name" /></a></li>
				<li><a href="<%=basePath%>user/lookuser">會員資訊</a></li>
				<li><a href="<%=basePath%>user/logout">登出</a></li>
			</ul>
			<form name="searchf" class="navbar-form navbar-right" method="post">
				<input id="searchTitle" class="form-control" name="search"
					value="<%=session.getAttribute("search")%>" maxlength="30"
					placeholder="Search Title..."> <input id="confirmSearch"
					type="hidden" value="<%=basePath%>board/changepage?page=search">
			</form>
		</div>
	</div>
	</nav>
	<form name="f" action="ChangePageAction" method="post">
		<input id="confirm" type="hidden"
			value="<%=basePath%>board/changepage?page=selectedPage"> <input
			id="selectedPage" name="selectedPage" type="hidden"
			value="<%=session.getAttribute("nowPage")%>">
		<div class="container-fluid">
			<div class="row">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th align="center">公布者</th>
								<th align="center">標題</th>
								<th align="center">發布日期</th>
								<th align="center">截止日期</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#session.boards">
								<tr>
									<td><s:property value="author_name" /></td>
									<td><a
										href="<%=basePath%>board/looknews?id=<s:property value="id"/>"><s:property
												value="title" /></a></td>
									<td><s:date format="yyyy-MM-dd HH:mm:ss" name="createTime" /></td>
									<td><s:date format="yyyy-MM-dd HH:mm:ss" name="deadline" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
	<%!int[] pages;%>
	<%
		pages = (int[]) session.getAttribute("totalPages");
	%>
	<div class=area>
		<div class=addnews>
			<input type="button" value="發布新公告"
				class="btn btn-lg btn-primary btn-block"
				onclick="javascript:document.f.action='<%=basePath%>board/addnews';document.f.submit();" />
		</div>
		<div class=changepage>
			<ul>
				<li><input type="button" value="上一頁"
					class="btn btn-lg btn-primary btn-block"
					onclick="javascript:document.f.action='<%=basePath%>board/changepage?page=down';document.f.submit();" /></li>
				<li><s:select name="select" id="select"
						onchange="javascript:changed(this);" list="#session.totalPages"
						value="#session.nowPage" /><label>/<%=pages.length%>頁
				</label></li>
				<li><input type="button" value="下一頁"
					class="btn btn-lg btn-primary btn-block"
					onclick="javascript:document.f.action='<%=basePath%>board/changepage?page=up';document.f.submit();" /></li>
			</ul>
		</div>
	</div>
</body>
<script src="/forssh/js/bootstrap.min.v1.js"></script>
<script src="/forssh/js/jquery.min.js"></script>
<script language="javascript">
	function changed(theselect) {
		document.getElementById("selectedPage").value = theselect.value;
		document.f.action = document.getElementById("confirm").value;
		document.f.submit();
	}

	$('#searchTitle').keypress(
			function(event) {
				var str ='<%=session.getAttribute("search")%>';
				if (event.which === 13) {
					var str2 = document.getElementById("searchTitle").value;
					if (str !== str2) {
						document.searchf.action = document
								.getElementById("confirmSearch").value;
						document.searchf.submit();
					}
				}
			});
</script>
</html>
