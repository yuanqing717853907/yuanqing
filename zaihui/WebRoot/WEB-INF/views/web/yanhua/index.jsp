<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>......|||烟花游戏分发中心|||......</title>
	<script type="text/javascript">
		function playVideo(pNumber){
			var purl="playyanhua?pNum="+pNumber;
			location.href=purl;
		}
		//回到上一页重新选择烟花形式
		function changevio(){
			var purl="../../";
			location.href=purl;
		}
		//回到首页
		function tohome(){
			var purl="../../";
			location.href=purl;
		}
	</script>
	<style type="text/css">
		a{
			color:#fff;
			display:block; 
			line-height:100px;
			text-align:center;
		}
		a:link{
			text-decoration:none;
		}
		a:visited{
			text-decoration:none;
		}
		a:hover{
			text-decoration:none;
		}
		a:active{
			text-decoration:none;
		}
		body 
		{ 
	      	background-color:#E7EAEB;
	      	font-family:"微软雅黑","黑体","宋体";
	      	height:100%;
	      	font-weight: 900;
	      	font-size:26px
		}  
		.wrap {
		  	margin:0 auto;
		  	padding: 10px 0 0 10px;
		  	width: 80%;
		  	overflow: hidden;
		}
		.wrap .item {
		  	position: relative;
		  	float: left;
		  	margin-left: -10px;
		  	width: 30%;
		  	height: 120px;
		  	line-height: 80px;
		  	text-align: center;
		  	border: 10px solid #ccc;
		}
		.wrap .item:hover {
		  	z-index: 2;
		  	border-color: #f00;
		}
		.btndiv{
			margin-top:20px;
			font-size:14px;
			color:#669933;
			margin:0 auto;
		  	text-align: center;
		}
		.btn2{
			margin-top:20px;
			width:20%;
			height:60px;
			font-size:14px;
			background-color:#00CC99;
			color:#fff;
		}
		.btn3{
			margin-top:20px;
			width:20%;
			height:60px;
			font-size:14px;
			background-color:#00CC99;
			color:#fff;
		}
	</style> 
</head>
<body> 
	<div class="wrap">
	  <div class="item" onclick="playVideo('1');">
	  		<img src="<%=basePath %>static/yanhua/img/1.png" border="0" width="100%" height="100%"/>
	  </div>
	  <div class="item" onclick="playVideo('2');">
	  		<img src="<%=basePath %>static/yanhua/img/2.png" border="0" width="100%" height="100%"/>
	  </div>
	  <div class="item" onclick="playVideo('3');">
	  		<img src="<%=basePath %>static/yanhua/img/3.png" border="0" width="100%" height="100%"/>
	  </div>
	  <div class="item" onclick="playVideo('4');">
	  		<img src="<%=basePath %>static/yanhua/img/4.png" border="0" width="100%" height="100%"/>
	  </div>
	  <div class="item" onclick="playVideo('5');">
	  		<img src="<%=basePath %>static/yanhua/img/5.png" border="0" width="100%" height="100%"/>
	  </div>
	  <div class="item" onclick="playVideo('6');">
	  		<img src="<%=basePath %>static/yanhua/img/6.jpg" border="0" width="100%" height="100%"/>
	  </div>
	  <div class="item" onclick="playVideo('7');">
	  		<img src="<%=basePath %>static/yanhua/img/7.png" border="0" width="100%" height="100%"/>
	  </div>
	  <div class="item" onclick="playVideo('8');">
	  		<img src="<%=basePath %>static/yanhua/img/8.png" border="0" width="100%" height="100%"/>
	  </div>
	  <div class="item" onclick="playVideo('9');">
	  		<img src="<%=basePath %>static/yanhua/img/9.png" border="0" width="100%" height="100%"/>
	  </div>
	</div>
     <div class="btndiv">
  		选择你喜欢的烟花在大屏上进行同步燃烧~~~~
  		<br/>
  		<input type="button" value="上一步" class="btn2" onclick="changevio();"/>
  		<input type="button" value="游戏中心" class="btn3" onclick="tohome();"/>
    </div>
</body>
</html>