<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>......|||分发处理中|||......</title>
	<script type="text/javascript">
		/*sessionStroage是基于会话级的
		    浏览器窗口关闭就会自动销毁*/	
		if (sessionStorage.pagecount) {
		  sessionStorage.pagecount=Number(sessionStorage.pagecount) +1;
		} else {
		  sessionStorage.pagecount=1;
		}
		//document.write("Visits "+ sessionStorage.pagecount + " time(s).");
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
		      margin:0px;
		      padding:0px;
		      background-color:#E7EAEB;
		      font-family:"微软雅黑","黑体","宋体";
		      height:100%;
		      font-weight: 900;
		      font-size:26px
		}
		.tuyacss {  
		    float:left;  
		    display:inline;
		    width:40%;
		    height:100px;
		    background-color:#02A202;
		}  
		.yanhuacss{  
		    float:left;  
		    display:inline;
		    width:40%;  
		    height:100px;
		    margin-left:2%;
		    background-color:#A200AD;
		}
		.yinpincss{  
		    float:left;  
		    display:inline;
		    width:10%;  
		    height:100px;
		    margin-left:2%;
		    background-color:#E7EAEB;
		}
		
	</style> 
</head>
<body>
	<div style="height:100%;width:100%;border:0px solid red;padding-top:40%">
		<div style="width:60%;margin:0 auto;border:1px solid #E7EAEB;">
			<div id="tuyadiv" class="tuyacss">
				<a href="main/tuya/-">涂鸦</a>
			</div>
			<div id="yanhuadiv" class="yanhuacss">	
				<a href="main/yanhua/-">烟花</a> 
			</div>
			<a href="main/SMTP/-">
				<div id="yinpindiv" class="yinpincss">
				</div>
			</a>
			<!-- 
			<div id="yinpindiv" class="yinpincss">
				<a href="main/yinpin/-">音频</a>
			</div>
			 -->
		</div>
	</div>
</body>
</html>