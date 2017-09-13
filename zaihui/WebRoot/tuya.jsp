<%@ page language="java" import="java.util.*,com.web.util.*,java.lang.Math" pageEncoding="UTF-8"%>
<%
	//http://www.w2bc.com/Article/5271
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	//设置参数
	session.setAttribute("geme_tuya",  Math.random()*30000);
	List sessions_tuya = SessionCountListenter.getTuYaSessions();
	out.println("涂鸦数："+sessions_tuya.size());
	List sessions_yinpin = SessionCountListenter.getYinPinSessions();
	out.println("音频数  ："+sessions_yinpin.size());
%>
<!DOCTYPE html>
<html> 
	<head>
	    <title>涂鸦</title>
	    <script type="text/javascript" src="<%=basePath %>static/canvas/jquery-3.2.1.min.js"></script>
	    <!--[if IE]><script type="text/javascript" src="/static/canvas/excanvas.js"></script><![endif]-->
	    <script type="text/javascript" src="<%=basePath %>static/canvas/html5-canvas-drawing-app.js"></script>
	</head>
	<body>
	    <div style="width: 530px; margin: 10px auto">
	        <div id="canvasDiv">
	        </div>
	    </div>
	    <script type="text/javascript">        
			$(document).ready(function () {
	            prepareCanvas();
	        });</script>
		<div id="paramValue">
			<input name="pointcolor" id="pointcolor" />
			<input name="pointsize" id="pointsize" />
			<input name="pointtool" id="pointtool" />
	        <div id="pointxy">
	        </div>
	
			<div id="movexy">
	        </div>
		</div>
	</body>
</html>
