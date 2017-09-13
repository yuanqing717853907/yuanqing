<%@   page contentType="image/jpeg " 
		   import="java.io.IOException,javax.imageio.ImageIO,java.awt.*,java.awt.image.BufferedImage,java.util.Random,com.sun.image.codec.jpeg.* "%>
<%@   page import="java.io.* "%>
<%
		FileOutputStream myout=null;
	try {
		//设置页面不缓存 
		response.setHeader("Pragma ", "No-cache ");
		response.setHeader("Cache-Control ", "no-cache ");
		response.setDateHeader("Expires ", 0);
		//读入文件 
		File _file = new File("C:/Users/vetech/Desktop/temp/tp/zb.png ");
		//构造Image对象 
		Image src = javax.imageio.ImageIO.read(_file);
		//得到源图宽 
		int wideth = src.getWidth(null);
		//得到源图长 
		int height = src.getHeight(null);
		//获得内存 
		BufferedImage tag = new BufferedImage(wideth / 2, height / 2, BufferedImage.TYPE_INT_RGB);
		//得到图片上下文 
		Graphics2D g = tag.createGraphics();
		//设置图片背景色 
		g.setBackground(new Color(200, 250, 200));
		//画图片的边框使用背景色 
		g.clearRect(0, 0, wideth, height);
		//设置画笔的颜色 
		g.setColor(Color.RED);
		//向图片画另一个图片上去 
		g.drawImage(src, 0, 0, wideth / 2, height / 2, null); //绘制缩小后的图 
		for (int i = 10; i < wideth; i += 200) {
			for (int j = 10; j < height; j += 100) {
				g.drawString("( " + i + "   ,   " + j + ") ", i, j);
			}
		}
		//向图片画一条线 
		//g.drawLine(0,   0,   99,   199); 
		//结束图片 
		g.dispose();
		tag.flush();
		tag=null;  
		response.flushBuffer();  
		out.clear();  
		out = pageContext.pushBody();  
	} catch (Exception e) {
		e.printStackTrace();
	}
	response.flushBuffer();  
	out.clear();
	out = pageContext.pushBody();
	//如果生成新文件还需要out.close();
%>
