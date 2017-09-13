<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String yanhuaId=(String)session.getAttribute("yanhuaId");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>......|||燃烧播放|||......</title>
	<script src="<%=basePath %>static/touchtuya/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		function toCommitForm(pNumber){
			//提交到服务器，并写入到txt文件中，等待PC来抓取	
			//异步提交坐标信息
			//alert($("#sessionflag").val());
      	var data=$("#sessionflag").val()+"/"+pNumber;
   		 var url="<%=basePath %>commitform/commitYanHua";
         $.ajax({
             url: url,
             type: 'post',
             datatype: "json",
             contentType: "application/json",
             data: data,
             success: function (res) {
                 if (res.status != '1') {
                   alert("BAD");
                 } else {
                   alert("OK");  
                 }
             }
         });
			
		}
		//回到上一页重新选择烟花形式
		function changevio(){
			var purl="-";
			location.href=purl;
		}
		//回到首页
		function tohome(){
			var purl="../..";
			location.href=purl;
		}
	</script>
	<style type="text/css">
		body 
		{ 
	      	background-color:#999;
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
		  	margin-left: 0px;
		  	width: 90%;
		  	height: 100%;
		  	line-height: 80px;
		  	text-align: center;
		  	border: 10px solid #000;
		} 
		.btndiv{
			margin:0 auto;
		  	text-align: center;
		}
		.btn{
			margin-top:20px;
			width:30%;
			height:60px;
			font-size:14px;
			background-color:#330000;
			color:#fff;
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
	  <div class="item">
	 	 <input type="hidden" name="sessionflag" id="sessionflag" value="<%=yanhuaId%>"/>
		  <video src="<%=basePath %>static/yanhua/img/<%=request.getAttribute("pNum") %>.ogg" loop="loop" autoplay="autoplay" width="100%" height="40%">
		  	当前浏览器不支持视频预览 替换成下面图
		  </video>
	  </div>
	  <div class="btndiv">
	  		<input type="button" value="换一个" class="btn2" onclick="changevio();"/>
	  		<input type="button" value="大屏直播" class="btn" onclick="toCommitForm(<%=request.getAttribute("pNum") %>);"/>
	  		<input type="button" value="游戏中心" class="btn3" onclick="tohome();"/>
	  </div>
	</div>
</body>
</html>