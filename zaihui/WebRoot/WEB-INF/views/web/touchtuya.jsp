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
<!DOCTYPE HTML>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="zh"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="zh"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="zh"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="zh">  <!--<![endif]-->
<head>
<title>涂鸦游戏</title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="<%=basePath %>static/touchtuya/js/jquery-3.2.1.min.js"></script>
<script src="<%=basePath %>static/touchtuya/js/jq-signature.js"></script>
<script>
	var mousePressed = false;
	var lastX, lastY;
	var ctx;
	function InitThis() {
		ctx = document.getElementById('myCanvas').getContext("2d");
		/*
		$('#myCanvas').mousedown(function(e) {
			mousePressed = true;
			Draw(e.pageX - $(this).offset().left, e.pageY - $(this).offset().top, false);
		});
		*/

		$('#myCanvas').on('mousedown touchstart',function(e) {
			mousePressed = true;
			Draw(e.pageX - $(this).offset().left, e.pageY - $(this).offset().top, false);
		});
		
		$('#myCanvas').on('mousemove touchmove',function(e) {
			if (mousePressed) {
				Draw(e.pageX - $(this).offset().left, e.pageY - $(this).offset().top, true);
			}
		});

		$('#myCanvas').on('mouseup touchend',function(e) {
			mousePressed = false;
		});
		$('#myCanvas').on('mouseleave touchend',function(e) {
			mousePressed = false;
		});
	}
	function Draw(x, y, isDown) {
		var pointcolor="black";
	    var pointsize="9"; 
		if (isDown) {
			ctx.beginPath();
			ctx.strokeStyle = $('#selColor').val();
			ctx.lineWidth = $('#selWidth').val();
			ctx.lineJoin = "round";
			ctx.moveTo(lastX, lastY);
			ctx.lineTo(x, y);
			ctx.closePath();
			ctx.stroke();
			pointcolor=$('#selColor').val();
		    pointsize=$('#selWidth').val();
		}
		lastX = x;
		lastY = y;
		$("#xyinfo").html("x=" + x + ",y=" + y);
		
		var sessionflag=document.getElementById("sessionflag").value;
		//document.write("x="+x+",y="+y)
		$("#pointxy").val(sessionflag+"\/"+lastX+"\/"+lastY+"\/"+pointcolor+"\/"+pointsize+","+$("#pointxy").val());
	}

	function clearArea() {
		// Use the identity matrix while clearing the canvas
		ctx.setTransform(1, 0, 0, 1, 0, 0);
		ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
	}
	
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
      function cleanplan(){
      	  //clear plan
	      clearArea();
	      //clear form value
	      $("#pointxy").val("");
	      return false;
      }
</script>
</head>
<body onload="InitThis()">
	<div align="center">
		<canvas id="myCanvas" width="1000px" height="600px" style="border:2px solid #DEDEDE;background-color:#333333"></canvas>
		<div class="control-ops" style="font-size:12px;"><br/>
			画笔粗细 :
			<select id="selWidth" style="height:30px;width:120px;">
				<option value="1">1磅</option>
				<option value="3">3磅</option>
				<option value="5">5磅</option>
				<option value="7">7磅</option>
				<option value="9" selected="selected">9磅</option>
				<option value="11">11磅</option>
			</select>
			画笔颜色 :
			<select id="selColor" style="height:30px;width:120px;">
				<option value="black">黑色</option>
				<option value="blue" selected="selected">蓝色</option>
				<option value="red">红色</option>
				<option value="green">绿色</option>
				<option value="yellow">黄色</option>
				<option value="gray">灰色</option>
			</select><br/><br/>
			<button type="button" class="btn btn-primary" onclick="javascript:cleanplan();" style="width:120px;height:40px;background-color:#D65863;color:#fff;">清空画板</button>
		</div>
	</div>  
	<div id="xyinfo"></div><a href="<%=basePath %>static/stan1.html">....</a>
	<div id="paramValue">
			<input type="hidden" name="sessionflag" id="sessionflag" value="<%=request.getSession().getAttribute("tuyaId")%>"/>
			<input type="text" name="tuyaId" id="tuyaId" value="<%=tuyaId%> 123"/>
			<form action="commitform/commitxy" name="xyinfo" id="xyinfo" method="post">
	        	<textarea rows="5" cols="60" name="pointxy" id="pointxy" style="display:none;"></textarea>
	        </form>
		</div>
</body>
</html>
