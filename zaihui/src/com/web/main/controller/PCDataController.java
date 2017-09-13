/**     
* @Title: PCDataController.java
* @Package com.web.main.controller
* @Description: TODO(用一句话描述该文件做什么)   
* @author vetech
* @date 2017-7-25 下午10:23:52  
*/   
package com.web.main.controller;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.main.Config;

/**
 * PC数据解析机
 * @date 2017-7-25 下午10:23:52
 */
@Controller
@RequestMapping("/topc")
public class PCDataController {
	/**
	 * PC上的，处理推送过来的数据
	 * @param xyinfo
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="/catchServerGameData",method=RequestMethod.GET)
	public void jumpCenter(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String reqDatType=request.getParameter("gamedatatype");
		StringBuffer returnSb=new StringBuffer();
		System.out.println("PC发送过来了数据抓取请求，请求数据类型gamedatatype="+reqDatType);
		//是来服务器抓取涂鸦坐标数据的
		if(StringUtils.equalsIgnoreCase("tuya",reqDatType)){
			for(int w=0;w<Config.xyLinkList.size();w++){
				if(w<0)break;
				//System.out.println("消费掉>>>>"+Config.xyLinkList.get(w)+",当前还剩下"+Config.xyLinkList.size());
				String data = Config.xyLinkList.get(w);
				returnSb.append(data).append("\n");
				try {
						Config.xyLinkList.remove(w);
						--w;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		}
		System.out.println("返回给PC的数据是:"+returnSb.toString());
		PrintWriter writer =response.getWriter();  
	    writer.write(returnSb.toString());//这里是你要返回的字符串  
	    writer.flush();
	    if(writer!=null){
	    	writer.close();
	    }
		//RenderUtils.renderText("....................................");
	} 
	public void ceshi(HttpServletRequest request,HttpServletResponse response ) throws Exception{}
}
