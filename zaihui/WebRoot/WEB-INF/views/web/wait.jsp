<%@ page language="java" import="java.util.*,com.web.util.*,java.lang.Math" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>太火爆了，您稍等~~~~</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="/views/bstp/css/bootstrap.min.css">
		<script src="/views/common/js/jquery-3.2.1.min.js"></script>
		<script src="/views/bstp/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			//回到首页
			function tohome(){
				var purl="../../";
				location.href=purl;
			}
		</script>
	</head>
	<body>
		<h1>乖乖~~人数忒多了，客官请稍等！</h1>
		<span>感谢您的关注，请稍后再试~~~</span><br/>
		<button type="button" class="btn btn-primary" onclick="javascript:tohome();" style="width:120px;height:40px;background-color:#D65863;color:#fff;">返回游戏中心</button>
	</body>
</html>