package com.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 打点日志
 * 
 * @author vetech 2016-12-1 CPS_EDV
 */
public class StepLogUtil {
	private static final String LOG_PATH = "mylog"; 
 

	public static void loginfo(String logStr) {
		addLog(LOG_PATH, VeDate.dateToStr(new Date()), ">>" + logStr);
	}

	/**
	 * 日志写入方法
	 * 
	 * @param path
	 * @param fileName
	 * @param content
	 */
	private static void addLog(String path, String fileName, String content) {
		String ml = System.getProperty("catalina.home");
		if (StringUtils.isBlank(ml) || "null".equals(ml)) {
			return;
		}
		path = ml + "/logs/" + path;
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path + "/" + fileName, true);
			fileWriter.write(VeDate.getStringDate() + "==" + content + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
