/**     
 * @Title: ChartGraphics.java
 * @Package com.utils
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author vetech
 * @date 2017-7-11 上午9:07:36  
 */
package com.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * <p>
 * Title: ChartGraphics
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * @authorvetech
 * @date 2017-7-11 上午9:07:36
 */
public class ChartGraphics {
	private BufferedImage image;
	private int imageWidth = 400; // 图片的宽度
	private int imageHeight = 200; // 图片的高度

	// 生成图片文件

	@SuppressWarnings("restriction")
	public void createImage(String fileLocation) {
		BufferedOutputStream bos = null;
		if (image != null) {
			try {
				FileOutputStream fos = new FileOutputStream(fileLocation);
				bos = new BufferedOutputStream(fos);

				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
				encoder.encode(image);
				bos.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {// 关闭输出流
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void graphicsGeneration(String titleTip, String info[], String imgurl) {
		int H_title = 30; // 头部高度
		int H_mainPic = 0; // 轮播广告高度
		int H_tip = 140; // 上网提示框高度
		int tip_2_top = (H_title + H_mainPic);
		int btns_2_top = tip_2_top + H_tip + 20;

		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		// 设置图片的背景色
		Graphics2D main = image.createGraphics();
		main.setColor(Color.ORANGE);
		main.fillRect(0, 0, imageWidth, imageHeight);

		// ***********************页面头部
		Graphics title = image.createGraphics();
		// 设置区域颜色
		title.setColor(new Color(143, 0, 0));
		// 填充区域并确定区域大小位置
		title.fillRect(0, 0, imageWidth, H_title);
		// 设置字体颜色，先设置颜色，再填充内容
		title.setColor(Color.white);
		// 设置字体
		Font titleFont = new Font("宋体", Font.BOLD, 14);
		title.setFont(titleFont);
		title.drawString(titleTip, 10, (H_title) / 2 + 5);

		// ***********************插入中间广告图
		/*
		Graphics mainPic = image.getGraphics();
		BufferedImage bimg = null;
		try {
			bimg = javax.imageio.ImageIO.read(new java.io.File(imgurl));
		} catch (Exception e) {
		}

		if (bimg != null) {
			mainPic.drawImage(bimg, 0, H_title, imageWidth, H_mainPic, null);
			mainPic.dispose();
		}*/
		// ***********************设置下面的提示框
		Graphics2D tip = image.createGraphics();
		// 设置区域颜色
		tip.setColor(new Color(204, 153, 0));
		// 填充区域并确定区域大小位置
		tip.fillRect(0, tip_2_top, imageWidth, H_tip);
		// 设置字体颜色，先设置颜色，再填充内容
		tip.setColor(Color.white);
		// 设置字体
		Font tipFont = new Font("宋体", Font.BOLD, 16);
		tip.setFont(tipFont);
		tip.drawString(info[0], 60, tip_2_top + (H_tip) / 2 - 30);
		tip.drawString(info[1], 60, tip_2_top + (H_tip) / 2 + 10);
		Font tip_address = new Font("宋体", Font.BOLD, 12);
		tip.setFont(tip_address);
		tip.drawString(info[2], 60, tip_2_top + (H_tip) / 2 + 35);
		
		Font tipFont3 = new Font("宋体", Font.PLAIN, 12);
		tip.setFont(tipFont3);
		tip.setColor(Color.LIGHT_GRAY);
		tip.drawString(info[3], 120, tip_2_top + (H_tip) / 2 + 60);
		// ***********************设置下面的按钮块
		// 设置字体颜色，先设置颜色，再填充内容
		Font bottomFont = new Font("宋体", Font.ITALIC, 12);
		tip.setFont(bottomFont);
		tip.setColor(Color.black);
		tip.drawString(info[4], 100, btns_2_top); 
		createImage("c:\\hehe.jpg");
	}

	public static void main(String[] args) {
		ChartGraphics cg = new ChartGraphics();
		try {
			String info[]={"服务预约码  6658-8821-054","服务厅电话  027-88585587","天河机场A区安检区二楼12-01号","卡很丑，号码很珍贵，请妥善保管!","※天河机场贵宾厅保留最终解释权"};
			cg.graphicsGeneration("天河机场贵宾厅单次VIP卡", info, "C:\\Users\\vetech\\Desktop\\temp\\tp\\zb.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
