/**     
* @Title: Config.java
* @Package com.web.main
* @Description: TODO(用一句话描述该文件做什么)   
* @author vetech
* @date 2017-8-14 下午9:11:08  
*/   
package com.web.main;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 常量定义
 * @date 2017-8-14 下午9:11:08
 */
public class Config {

	
	/**存放涂鸦坐标数据*/
	public static LinkedList<String> xyLinkList=new LinkedList<String>();
	
	/**存放涂鸦id数据*/
	public static LinkedList<String> tuyaIdList=new LinkedList<String>();
	
	/**存放涂鸦id数据*/
	public static ArrayList<String> tuyaIdOldList=new ArrayList<String>();
	
	public static int count=1;
	
	static{
		for(int i=1;i<9 ;i++){
			tuyaIdList.add(String.valueOf(i));
		}
	}
}
