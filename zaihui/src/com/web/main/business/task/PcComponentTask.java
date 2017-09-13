package com.web.main.business.task;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.web.util.HttpUtils;

@Component
public class PcComponentTask {
	/**阿里云游戏服务器地址**///123.56.71.172    192.168.22.34
	public static String serverUrl="http://123.56.71.172:8866/zaihui/topc/catchServerGameData";
	// 间隔2秒执行
	@Scheduled(cron = "0/200 * * * * ? ")
	public void getXyInfoFromGameServer() {
		System.out.println("即将向云服务器[ "+serverUrl+" ]发送坐标抓取请求");  
        NameValuePair[] params = new NameValuePair[1];
        params[0] = new NameValuePair("gamedatatype", "tuya");
		try {
			//String getGameData = HttpUtils.sendHttpClient(serverUrl, params);
			String getGameData=HttpUtils.sendGet(serverUrl+"?gamedatatype=tuya","UTF-8"); 
			//String getGameData=HttpUtils.sendUrl(serverUrl+"?gamedatatype=tuya");
			System.out.println("从服务器拿到数据："+getGameData);
			if(StringUtils.isNotBlank(getGameData)){		
				writeXyToTxt(getGameData);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void writeXyToTxt(String content) {
		try {
			String txtfilepath="d:/xypoint";
			String filename=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+".txt";
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(txtfilepath+"/"+filename, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content+"\r\n");
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
