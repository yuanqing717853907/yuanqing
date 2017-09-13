<%@ page language="java" import="java.util.*,com.web.util.*,java.lang.Math" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>音频-Game</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="/views/bstp/css/bootstrap.min.css">
		<script src="/views/common/js/jquery-3.2.1.min.js"></script>
		<script src="/views/bstp/js/bootstrap.min.js"></script>
	</head>
	<body>
		<h1>比嗓门啦!</h1>
		<span>该游戏正在制作中，感谢关注。</span>
		<%
			session.setAttribute("geme_yinpin", Math.random()*10000);
			List sessions_tuya = SessionCountListenter.getTuYaSessions();
			out.println("涂鸦数："+sessions_tuya.size());
			List sessions_yinpin = SessionCountListenter.getYinPinSessions();
			out.println("音频数  ："+sessions_yinpin.size());
		 %>
	</body>
</html>