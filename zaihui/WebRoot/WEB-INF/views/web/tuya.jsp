<%@ page language="java" import="java.util.*,com.web.util.*,java.lang.Math" pageEncoding="UTF-8"%>
<%
	//http://www.w2bc.com/Article/5271
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	//设置参数
	/* session.setAttribute("geme_tuya",  Math.random()*30000); */
	String tuyaId=(String)session.getAttribute("tuyaId");
	List sessions_tuya = SessionCountListenter.getTuYaSessions();
	out.println("涂鸦数："+sessions_tuya.size());
	List sessions_yinpin = SessionCountListenter.getYinPinSessions();
	out.println("音频数  ："+sessions_yinpin.size());
%>
<!DOCTYPE html>
<html> 
	<head>
	    <title>T....ya</title> 
	    <script type="text/javascript" src="<%=basePath %>static/canvas/jquery-3.2.1.min.js"></script>
	    <!--[if IE]><script type="text/javascript" src="<%=basePath %>static/canvas/excanvas.js"></script><![endif]-->
	    <script type="text/javascript" src="<%=basePath %>static/canvas/html5-canvas-drawing-app.js"></script>
	</head>
	<body>
	    <div style="width: 100%; margin: 10px auto">
	        <div id="canvasDiv">
	        </div>
	    </div>
	    <script type="text/javascript">        
			$(document).ready(function () {
	            //初始化画笔
	            prepareCanvas();
	        });
	        function commitPointXY(){
	           //alert(formParam);  
	           //异步提交坐标信息
	           var data = $("#pointxy").val();//序列化表格内容为字符串
			   var url="<%=basePath %>commitform/commitxy";
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
	        setInterval("commitPointXY();",10000);
	        
	        function doSubfm(){
	        	$("#xyinfo").submit();
	        }
	    </script>
		<div id="paramValue">
			<input type="hidden" name="canvasimgpathRoot" id="canvasimgpathRoot" value="<%=basePath%>"/>
			<input type="hidden" name="sessionflag" id="sessionflag" value="<%=session.getId()%>"/>
			<input type="text" name="tuyaId" id="tuyaId" value="<%=tuyaId%> 123"/>
			
			<input type="hidden" name="pointcolor" id="pointcolor" />
			<input type="hidden" name="pointsize" id="pointsize" />
			<input type="hidden" name="pointtool" id="pointtool" />
			<form action="commitform/commitxy" name="xyinfo" id="xyinfo" method="post">
	        	<textarea rows="5" cols="60" name="pointxy" id="pointxy" style="display:none;"></textarea>
	        	<!-- <input type="button" value="点" onclick="doSubfm();"/> -->
	        </form>
		</div>
	</body>
</html>
