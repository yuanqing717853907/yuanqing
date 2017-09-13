<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>前台首页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="/views/bstp/css/bootstrap.min.css">
	</head>
	<body>
		<h1>Hello, world!</h1>
		<a href="<%=basePath %>main/toRegUser" target="_self">注册</a>
		<script src="/views/common/js/jquery-3.2.1.min.js"></script>
		<script src="/views/bstp/js/bootstrap.min.js"></script>
	</body>
</html>