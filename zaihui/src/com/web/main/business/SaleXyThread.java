package com.web.main.business;
import java.util.concurrent.Callable;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;

import com.web.main.Config;
import com.web.util.HttpUtils;
/**     
 * @Title: MakeXyThread.java
 * @Description: 消费坐标数据(推送坐标到PC端)
 * @date 2017-8-14 下午3:13:53  
 */ 
public class SaleXyThread implements Callable<Object> {

	/**PC数据解析机地址**/
	public static String pcUrl="http://192.168.22.34:8866/zaihui/topc/comminxy";
	public static void saleXy() throws InterruptedException{
		while(true){
			Thread.sleep(1000); 
			System.out.println("当前待消费数据数量::"+Config.xyLinkList.size());  
			for(int w=0;w<Config.xyLinkList.size();w++){
				if(w<0)break;
				System.out.println("消费掉>>>>"+Config.xyLinkList.get(w)+",当前还剩下"+Config.xyLinkList.size());
				String data = Config.xyLinkList.get(w);
		        NameValuePair[] params = new NameValuePair[1];
		        params[0] = new NameValuePair("xyinfo", data);
		        String sendResult="OK";
				try {
					sendResult = HttpUtils.sendHttpClient(pcUrl, params);
					if(StringUtils.equals("OK", sendResult)){		
						Config.xyLinkList.remove(w);
						--w;
					}
			        //System.out.println("推送给PC数据解析机结果="+sendResult);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	@Override
	public Object call() throws Exception {
		saleXy();
		return null;
	}
}
