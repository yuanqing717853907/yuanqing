<%@ page language="java" import="java.util.*,com.web.util.*,java.lang.Math" pageEncoding="UTF-8"%>
<%
	//http://www.w2bc.com/Article/5271
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>魔界身份证</title>
<style type="text/css">
/*CSS源代码*/
* {
	margin: 0;
	font-size: 15px;
}
.wrap {
  	margin:0 auto;
  	padding: 10px 0 0 10px;
  	width: 100%;
  	overflow: hidden;
  	border:1px solid red;
}
.content {
  	margin:0 auto;
}
canvas {
  	margin:0 auto;
	width: 50%;
	height: 50%;
}
#moj {
	padding: 6px;
	color: #999;
}
.btn {
	height: 32px;
	background: pink;
	margin-left: 10px;
}
.sm {
	padding-top: 1rem;
	line-height: 1.8;
}
</style>
</head>
<body>
	<div class="wrap">
		<canvas></canvas>
		<br/>
		<div class="content"> 
			<input type="text" id="moj" onclick="select(this)" value="Magic code" onKeyPress="return ent();" /> 
			<input type="button" class="btn" onclick="ast()" value="我的神秘图片" /><input type="button" value="游戏中心" class="btn" onclick="tohome();"/>
			<h1 class="sm">1、每个名字对应唯一张图片；2、每个幸运数也对应一个唯一图片，试试呗~~</h1>
		</div>
	</div>
</body>
<script type="text/javascript">
	//回到首页
	function tohome(){
		var purl="../../";
		location.href=purl;
	}
	/*Javascript代码片段*/
	var canvas, ctx, xors;
	canvas = document.getElementsByTagName('canvas')[0];
	ctx = canvas.getContext('2d');
	canvas.width = canvas.height = 400;
	ctx.fillRect(0, 0, 400, 400);
	ast();

	function ent(e) {
		if (!e)
			e = window.event;
		if (e.keyCode == 13) {
			ast();
			return false;
		}
	}

	function tekitou() {
		var a = "";
		for ( var b = 0; b < 5; b++)
			a += String.fromCharCode(65 + (58 * Math.random()) | 0);
		document.getElementById('moj').value = a;
		ast();
	}

	function rand() {
		var t = xors.x ^ (xors.x << 11);
		xors.x = xors.y;
		xors.y = xors.z;
		xors.z = xors.w;
		xors.w = (xors.w ^ (xors.w >>> 19)) ^ (t ^ (t >>> 8));
		return xors.w / 4294967296 + 0.5;
	}

	function ast() {
		var a, b, c, d, e, f, g, h, i, n, p, q, r, s, x, y, pt, size, step, ki, gu, pr, N;
		a = document.getElementById('moj').value;
		c = [ 123456789, 362436069, 521288629, 0 ];
		for (b = 0; b < a.length; b++)
			c[(b + 3) % 4] ^= (a.charCodeAt(b) << ((b * 11) % 16));
		xors = {
			x : c[0],
			y : c[1],
			z : c[2],
			w : c[3]
		};
		for (a = 0; a < 52; a++)
			rand();

		N = fi() ? 7 : 11;
		size = 4620;
		ctx.globalCompositeOperation = "source-over";
		ctx.fillStyle = "rgb(0,0,0)";
		ctx.fillRect(0, 0, 400, 400);
		ctx.globalCompositeOperation = "lighter";

		h = [];
		h[2] = 0.3 + rand() * 0.2;
		h[3] = 0.1 + rand() * 0.1;
		h[5] = 1 + rand() * 4;
		h[6] = 1 + rand();
		h[7] = 1 + rand();
		h[0] = 0.4 + rand() * 0.2;
		for (a = 2; a < 8; a++)
			if (fi())
				h[a] *= -1;

		ki = [ 1, 3, 5, 7, 9, 11 ];
		gu = [ 0, 0, 2, 4, 6, 8, 10 ];
		s = [];
		q = [];
		pr = (1 + rand() * (N - 1) | 0) / N;

		for (a = 0; a < 2; a++) {
			if (fi()) {
				s[a] = [ Math.cos, Math.sin ];
				q[a] = rg(ki) - pr;
			} else {
				s[a] = [ Math.sin, Math.cos ];
				q[a] = rg(gu) + pr;
			}
		}
		for (a = 2; a < 8; a++) {
			b = fi();
			if (!ki.length)
				b = 0;
			if (!gu.length)
				b = 1;
			q[a] = b ? rg(ki) : rg(gu);
			if (fi())
				q[a] *= -1;
			if (a > 5)
				b = !b;
			s[a] = b ? Math.cos : Math.sin;
		}

		n = [];
		p = [];
		for (a = 0; a < 3; a++)
			n[a] = fi() ? 1 : -1;
		step = Math.PI * 2 / size * N;
		r = 0;
		for (f = 0; f < size; f++) {
			b = s[6](r * q[6] + s[3](r * q[3]) * h[5]) * n[0];
			a = 1 + b * h[0];
			d = s[7](r * q[7]);
			e = -d;
			d *= (2 - a) * n[1];
			e *= (2 - a) * n[2];
			c = s[4](r * q[4] + s[5](r * q[5]) * h[7]) / 4 * h[6] * (a - (1 - h[0]));
			x = Math.sin(r * pr + c) * a + s[0][0](r * q[0]) * h[2] * d + s[1][0](r * q[1]) * h[3] * e;
			y = Math.cos(r * pr + c) * a + s[0][1](r * q[0]) * h[2] * d + s[1][1](r * q[1]) * h[3] * e;
			p[f] = [ x * 110 + 200, y * 110 + 200 ];
			r += step;
		}

		ctx.beginPath();
		h = 0;
		for (d = 0; d < 3; d++) {
			g = (rand() * 360) | 0;
			h += 1 + (rand() * 3) | 0;
			i = 50 + (rand() * 20) | 0;
			for (a = 0; a < p.length; a++) {
				ctx.beginPath();
				e = [];
				for (b = 0; b < 3; b++) {
					c = p[(a + b * ((d + 1) * h)) % p.length];
					e.push(c);
					ctx.lineTo(c[0], c[1]);
				}

				f = e[0][0] * (e[1][1] - e[2][1]);
				f += e[1][0] * (e[2][1] - e[0][1]);
				f += e[2][0] * (e[0][1] - e[1][1]);
				if (f > 45 && f < 8000) {
					ctx.fillStyle = "hsla(" + g + "," + i + "%,50%," + (45 / f) + ")";
					ctx.fill();
				}
			}
		}

		function fi() {
			return (rand() < 0.5);
		}

		function rg(ha) {
			var a, b;
			a = (ha.length * rand()) | 0;
			b = ha[a];
			ha[a] = ha[ha.length - 1];
			ha.pop();
			return b;
		}
	}
</script>
</html>