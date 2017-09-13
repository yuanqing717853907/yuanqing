/**     
 * @Title: CommitFormController.java
 * @Package com.web.main.controller
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author vetech
 * @date 2017-7-24 下午8:40:31  
 */
package com.web.main.controller;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.main.Config;

/**
 * 表单提交数据都到这里
 * 
 * @date 2017-7-24 下午8:40:31
 */
@Controller
@RequestMapping(value = "/commitform")
public class CommitFormController {

	/** 临时存储用户涂鸦的坐标数据的容器 */
	public static LinkedHashMap xyMap = new LinkedHashMap<String, String>();

	@RequestMapping(value = "/commitxy", method = RequestMethod.POST)
	public ModelAndView jumpCenter(@RequestBody String xyinfo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		final ExecutorService exec = Executors.newFixedThreadPool(1);
		// 获取session
		HttpSession session = request.getSession();

		if (!xyMap.containsKey(xyinfo)) {
			// 做缓存，进行数据比对
			// xyMap.put(xyinfo, xyinfo);
			// 放入链表容器中
			// Config.xyLinkList.add(xyinfo);
			// 将数据存储在云服务器上
			writeXyToTxt(xyinfo, session);
			// 放入启动生产线程
			// exec.submit(new MakeXyThread(xyinfo));
		}
		// 启动消费线程(推送坐标数据到PC端)
		// exec.submit(new SaleXyThread());
		return mv;
	}

	@RequestMapping(value = "/commitYanHua", method = RequestMethod.POST)
	public ModelAndView yanHuaCommit(@RequestBody String pNum, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		final ExecutorService exec = Executors.newFixedThreadPool(1);
		// 获取session
		HttpSession session = request.getSession();
		writeYanhuaToTxt(pNum, session);
		return mv;
	}

	/***
	 * 将用户的数据存储在云服务器上
	 * 
	 * @param content
	 */
	public static void writeXyToTxt(String content, HttpSession session) {
		try {
			// String txtfilepath = "C:/Users/admin/Downloads/WeiXin";
			String txtfilepath = "C:/WeiXin/TuYa";
			String tuyaId = (String) session.getAttribute("tuyaId");
			StringBuffer sb = new StringBuffer();
			sb.append(String.valueOf(Config.count));
			if (Config.tuyaIdOldList != null && Config.tuyaIdOldList.size() > 0) {
				// 如果这个session里面tuyaId存在于oldList 则返回1 说明存在 返回0说明不存在
				int i = Collections.frequency(Config.tuyaIdOldList, tuyaId);
				if (i == 1) {
					sb.append("o.txt");
				} else {
					sb.append("n.txt");
					Config.tuyaIdOldList.add(tuyaId);
				}
			} else {
				Config.tuyaIdOldList.add(tuyaId);
				sb.append("n.txt");
			}
			// String filename=new
			// SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+".txt";
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(txtfilepath + "/" + sb.toString(), "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content + "\r\n");
			randomFile.close();
			if (Config.count == 1500) {
				Config.count = 1;
			} else {
				Config.count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeYanhuaToTxt(String content, HttpSession session) {
		try {
			// String txtfilepath = "C:/Users/admin/Downloads/WeiXin/YanHua";
			String txtfilepath = "C:/WeiXin/YanHua";
			StringBuffer sb = new StringBuffer();
			sb.append(String.valueOf(Config.count));
			sb.append(".txt");
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(txtfilepath + "/" + sb.toString(), "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content + "\r\n");
			randomFile.close();
			if (Config.count == 1500) {
				Config.count = 1;
			} else {
				Config.count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
