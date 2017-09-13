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
	<link href="<%=basePath %>static/touchtuya/css/tuya.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath %>static/touchtuya/js/jquery-3.2.1.min.js"></script>
	<script src="<%=basePath %>static/touchtuya/js/jq-signature.js"></script>
	<script>
	var mousePressed = false;
	var lastX, lastY;
	var ctx;
	function InitThis() { 
		if ($('.js-signature').length) {
			$('.js-signature').jqSignature({lineColor:"#000",lineWidth:"8"});
		}
	}
      function commitPointXY(){
         //alert(formParam);  
         //异步提交坐标信息
         var data = $("#pointxy").val();//序列化表格内容为字符串
         //序列化数据后清空坐标信息避免数据重复
         $("#pointxy").val("");
         if(data!=null&&data!=""){
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
      }
      setInterval("commitPointXY();",10000);
      function cleanplan(){
      	 //清空画板区域
  		 $('.js-signature').jqSignature('clearCanvas');
  		 //清空表单数据
	     $("#pointxy").val("");
      }
	
	function InitThis() { 
		if ($('.js-signature').length) {
		    // 可以通过div设置 下面的属性来 初始化画笔颜色 和画笔粗细data-line-color="blue" data_line_width="1"
			$('.js-signature').jqSignature({lineColor:"red",lineWidth:"1"});
		}
	}

	//回到首页
	function tohome(){
		var purl="../../";
		location.href=purl;
	}
</script>
</head>
<body onload="InitThis()">
	<div align="center"> 
		<div class="js-signature" data-width="100%" data-height="600" data-border="1px solid black" data-auto-fit="true"></div>
	</div>		
	<button type="button" class="btn btn-primary" onclick="javascript:cleanplan();" style="width:120px;height:40px;background-color:#D65863;color:#fff;">清空画板</button>
	<button type="button" class="btn btn-primary" onclick="javascript:tohome();" style="width:120px;height:40px;background-color:#D65863;color:#fff;">返回游戏中心</button>
	<div id="xyinfo"></div>
	<div id="paramValue">
			<input type="hidden" name="sessionflag" id="sessionflag" value="<%=tuyaId%>"/l>
			<input type="hidden" name="colorCount" id="colorCount" value="3"/>
			<!-- <input type="hidden" name="lineWidth" id="lineWidth" value="1"/> -->
			<form action="commitform/commitxy" name="xyinfo" id="xyinfo" method="post">
	        	<textarea rows="5" cols="60" name="pointxy" id="pointxy" style="display:none;"></textarea>
	        </form>
		</div>
		<div id="canvas-color">
       		<h5>画笔颜色</h5>
        	<ul>
            	<li value="1" style="background:#fef4ac"></li>
                <li value="2" style="background:#0018ba"></li>
                <li value="3" style="background:#ffc200"></li>
                <li value="4" style="background:#f32f15"></li>
                <li value="5" style="background:#5ab639"></li>
            </ul>
        </div>
        <div id="canvas-brush">
        	<h5>画笔大小</h5>
        	<span class="small-brush"></span>
            <span class="middle-brush"></span>
            <span class="big-brush"></span>
        </div>
</body>
<script type="text/javascript">
$(function(){
	var canvas = document.getElementById('myCanvas');
	var ctx = canvas.getContext('2d');
	$('li').on('click',function(){
		var color = $(this).css('background-color');
		var colorCount=$(this).val();
		    ctx.beginPath();
		    ctx.strokeStyle=color;
		    ctx.fill();
		    //设置颜色值
		    $("#colorCount").val(colorCount);
		    
	})
	
	
	$('#canvas-brush').find('span').on('click',function(){
		var size=1;
		var thisClass = $(this).attr('class');
		if(thisClass.indexOf('small')>-1){
			size = 1;	
		}else if(thisClass.indexOf('middle')>-1){
			size = 5;
		}else if(thisClass.indexOf('big')>-1){
			size = 10;
		}
		  ctx.beginPath();
		    ctx.lineWidth = size;
		    ctx.fill();
		    //设置画笔大小数值
		  //$("#lineWidth").val(ctx.lineWidth);  
	});
	
})
</script>
</html>
