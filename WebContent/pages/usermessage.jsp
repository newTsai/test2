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
<link href="/forssh/css/dashboard.css" rel="stylesheet">
<link href="/forssh/css/board.css" rel="stylesheet">
<head>
<title>會員資訊</title>
</head>
<body>
	<form name="f" action="AlterUserAction" method="post">
	<s:token>

			</s:token>
		<table class="table">
			<tr>
				<th>會員帳號</th>
				<th><s:property value="user.user_id" /></th>
			</tr>
			<tr>
				<th>會員名稱</th>
				<th><s:property value="user.user_name" /></th>
			</tr>
			<tr>
				<th>會員信箱</th>
				<th><s:property value="user.user_mail" /></th>
			</tr>
			<tr>
				<th>會員手機</th>
				<th><s:property value="user.user_phone" /></th>
			</tr>
			<tr>
				<th>加入時間</th>
				<th><s:date format="yyyy-MM-dd HH:mm:ss" name="user.createTime" /></th>
			</tr>
			<tr>
				<th>發布過的公告數</th>
				<th><s:property value="user.user_newsCounts" /></th>
			</tr>
		</table>
		<div class=area>
			<div class=changepage>
				<ul>
					<li><input type="button" value="修改用戶資訊"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:document.f.action='<%=basePath%>user/altermessage?id=<s:property value="user.user_id"/>';document.f.submit();" />
					</li>
					<li><input type="button" value="更改密碼"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:document.f.action='<%=basePath%>user/alterpassword?id=<s:property value="user.user_id"/>';document.f.submit();" />
					</li>
					<li><input type="button" value="回首頁"
						class="btn btn-lg btn-primary btn-block"
						onclick="javascript:document.f.action='<%=basePath%>board/changepage?page=home';document.f.submit();" />
					</li>
				</ul>
			</div>
		</div>
	<div class="container-fluid">
		<div class="row">
			<h3>正在發布的公告</h3>
			<input type="button" value="刪除公告"
				class="btn btn-primary btn-lg"
				onclick="javascript:deleteChecked()" /> <input id="confirm"
				type="hidden" name="confirm"
				value="<%=basePath%>board/deletecheckednews"> <input
				id="needDelete" type="hidden" name="id" value="">
				
			<table class="table table-striped">
				<thead>
					<tr>
						<th><label> <input type="checkbox" id="0"
								name="clickAll" class="clickAll" value="0">全選
						</label></th>
						<th>標題</th>
						<th>發布時間</th>
						<th>修改時間</th>
						<th>結束時間</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="userBoards">
						<tr>
							<td><input type="checkbox" name="checksome"
								class="checksome" id="<s:property value="id"/>"
								value="<s:property value="id"/>"></td>
							<td><a
								href="<%=basePath%>board/looknews?id=<s:property value="id"/>&user_id=<s:property value="user.user_id"/>"><s:property
										value="title" /></a></td>
							<td><s:date format="yyyy-MM-dd HH:mm:ss" name="createTime" /></td>
							<td><s:date format="yyyy-MM-dd HH:mm:ss" name="modifyTime" /></td>
							<td><s:date format="yyyy-MM-dd HH:mm:ss" name="deadline" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	</form>
</body>
<script src="/forssh/js/bootstrap.min.v1.js"></script>
<script src="/forssh/js/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('.clickAll').click(function() {
			if ($('.clickAll').prop("checked")) {
				$('.checksome').each(function() {
					$(this).prop("checked", true);
				});
			} else {
				$('.checksome').each(function() {
					$(this).prop("checked", false);
				});
			}
		});
	});
	function deleteChecked() {
		var obj = document.getElementsByName("checksome");
		var len = obj.length;
		var list = [];
		for (i = 0; i < len; i++) {
			if (obj[i].checked == true) {
				list.push(obj[i].id)
			}
		}

		if (list.length == 0) {
		} else {
			if (confirm('確認刪除嗎？')) {
				document.getElementById("needDelete").value = list;
				document.f.action = document.getElementById("confirm").value;
				document.f.submit();
				alert("刪除成功");
			}
		}

	}
</script>
</html>