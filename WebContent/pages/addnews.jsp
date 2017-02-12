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
<link rel="shortcut icon" href="../img/board.ico">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../css/plugins/summernote/summernote.css" rel="stylesheet">
<link href="../css/plugins/summernote/summernote-bs3.css"
	rel="stylesheet">
<link href="../css/animate.min.css" rel="stylesheet">
<link href="../css/style.min.css" rel="stylesheet">
<head>
</head>
<title>發布新公告</title>
<body class="gray-bg">
	<div class="row">
		<div class="mail-box-header">
			<h2>新公告</h2>
		</div>
		<form name="f" action="AddNewsAction" class="form-horizontal"
			method="post">
			<s:token>

			</s:token>
			<input id="confirm" type="hidden" name="confirm"
				value="<%=basePath%>board/confirmnews"> <input id="con"
				type="hidden" name="newContentHTML" value=""> <input
				id="con2" type="hidden" name="newContentText" value="">
			<div class="mail-box">
				<div class="mail-body">

					<div class="form-group">
						<label class="col-sm-2 control-label">標題：</label>

						<div class="col-sm-10">
							<input id="tit" type="text" class="form-control" name="newTitle"
								maxlength="30" value="">
						</div>
					</div>
				</div>
				<div id="getContent" type="hidden"></div>
				<div class="mail-text h-200">
					<div class="note-editor">
						<textarea class="note-codable"></textarea>
						<div id="summernote"></div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="mail-body text-center tooltip-demo">
					<a href="javascript:if(confirm('確認發布嗎？'))getContent()"
						class="btn btn-sm btn-primary" data-toggle="tooltip"
						data-placement="top" title="" data-original-title="Send"><i
						class="fa fa-reply"></i> 發布</a> <a
						href="javascript:document.f.action='<%=basePath%>board/changepage?page=selectedPage';document.f.submit();"
						class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="top" title=""
						data-original-title="return lastPage"><i class="fa fa-times"></i>
						放棄</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
	</div>
	<script src="/forssh/js/jquery.min.js"></script>
	<script src="/forssh/js/bootstrap.min.v1.js"></script>
	<script src="/forssh/js/content.min_v2.js"></script>
	<script src="/forssh/js/plugins/summernote/summernote.js"></script>
	<script src="/forssh/js/plugins/summernote/summernote-zh-TW.v1.js"></script>
	<script language="javascript">
		function getContent() {
			var newTitle = document.getElementById("tit").value;

			$('#getContent').html(d.code());
			var text = document.getElementById("getContent").innerText;
			var html = document.getElementById("getContent").innerHTML;
			$('#getContent').hide();
			var newTitle2 = newTitle.trim().replace(" ", "");
			var str = text.replace(/\r\n|\n/g,"");
			str=str.replace(/\s+/g, "");
			if (newTitle2 == null || newTitle2 == "") {
				alert("標題不得為空");
			} else if (str == null || str.length < 30) {
				alert("內容不能少於30字");
			} else {
				document.getElementById("con").value = html;
				document.getElementById("con2").value = text;
				document.f.action = document.getElementById("confirm").value
				document.f.submit();
				alert("發布成功");
			}

		}
	</script>
	<script>
		$(document).ready(function() {
			d = $("#summernote").summernote({
				lang : "zh-TW",
				height:450,
				 focus:true,
				 maxHeight:600,
				 minHeight:450,
			});
		
		});
		
	</script>
</body>
</html>