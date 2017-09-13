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
	    <title>pen....</title> 
	    <script type="text/javascript" src="<%=basePath %>static/canvas/jquery-3.2.1.min.js"></script>
	    <script type="text/javascript" src="<%=basePath %>static/canvas/html5-canvas-drawing-app.js"></script>
	<style>
* {
	padding:0;
	margin:0;
}
html,body {
	position:relative;
	width:100%;
	height:100%;
}
body {
	background:#eee;
}
canvas {
	background:black;
	display:block;
	position:absolute;
	left:50%;
	top:50%;
	transform:translate(-50%,-50%);
}
</style>

</head>
<body>

<canvas id="c"></canvas>

<script>
	;(function(main) {
		main();
	})(function() {

		'use strict';

		var c = document.getElementById('c');
		var ctx = c.getContext('2d');
		var W = c.width = window.innerWidth;
		var H = c.height = window.innerHeight;
		var CX = W / 2;
		var CY = H / 2;

////////////////////////////////////////////////////////////		

		var Particle = function(x, y, vx, vy) {
			this.x = x;
			this.y = y;
			this.ox = x;
			this.oy = y;
			this.vx = vx;
			this.vy = vy;
			this.alpha = Math.random();
			this.color = 25;
			this.lineWidth = Math.random() * 4;
		};

		Particle.prototype = {
			constructor: Particle,
			update: function() {

				this.vx += Math.random() * 0.5 - 0.25;
				this.vy += 0.8;
				this.vy *= 0.98;
				this.alpha *= 0.95;

				this.ox = this.x;
				this.oy = this.y;
				this.x += this.vx;
				this.y += this.vy;

				if(this.y < 0 || this.y > H || this.alpha < 0.1) {
					this.vx = Math.random() * 2 - 1;
					this.vy = Math.random() * -50;
					this.ox = this.x = CX;
					this.oy = this.y = H;
					this.alpha = Math.random();
				}

			},
			render: function(ctx) {
				ctx.save();
				ctx.globalAlpha = 0.98;
				ctx.lineWidth = this.lineWidth;
				ctx.strokeStyle = 'hsla(' + (this.color) + ', 100%, 50%, ' + this.alpha + ')';
				ctx.beginPath();
				ctx.moveTo(this.ox, this.oy);
				ctx.lineTo(this.x, this.y);
				ctx.stroke();
				ctx.restore();
			}
		};

////////////////////////////////////////////////////////////
		var particleCount = 500;
		var particle = null;
		var particles = [];
		var interval = 0;
		for(var i = 0; i < 250; i++) {
			particle = new Particle(
				CX,
				H,
				Math.random() * 2 - 1,
				Math.random() * -50
			);			
			particles.push(particle);			
		}
		requestAnimationFrame(function loop() {
			requestAnimationFrame(loop);
			ctx.globalCompositeOperation = 'source-over';
			ctx.fillStyle = 'rgba(0, 0, 0, 0.1)';
			ctx.fillRect(0, 0, W, H);
			ctx.globalCompositeOperation = 'lighter';
			if(particles.length < particleCount) {
				particle = new Particle(
					CX,
					H,
					Math.random() * 2 - 1,
					Math.random() * -50
				);			
				particles.push(particle);
			} 
			for(var i = 0, len = particles.length; i < len; i++) {
				particle = particles[i];
				particle.update();
				particle.render(ctx);				
			}
		});
	});
</script>
</body>
</html>
