/**     
* @Title: CreateMenu.java
* @Package com.web.weixin
* @Description: TODO(用一句话描述该文件做什么)   
* @author vetech
* @date 2017-7-31 下午9:41:33  
*/   
package com.web.weixin;

import org.apache.commons.httpclient.NameValuePair;

import com.web.util.HttpUtils;

/**
 * @authorvetech 
 * @date 2017-7-31 下午9:41:33
 */
public class CreateMenu {
	/**
	{
	     "button":[
	     {	
	          "type":"click",
	          "name":"今日歌曲",
	          "key":"V1001_TODAY_MUSIC"
	      },
	      {
	           "name":"菜单",
	           "sub_button":[
	           {	
	               "type":"view",
	               "name":"搜索",
	               "url":"http://www.soso.com/"
	            },
	            {
	                 "type":"miniprogram",
	                 "name":"wxa",
	                 "url":"http://mp.weixin.qq.com",
	                 "appid":"wx286b93c14bbf93aa",
	                 "pagepath":"pages/lunar/index"
	             },
	            {
	               "type":"click",
	               "name":"赞一下我们",
	               "key":"V1001_GOOD"
	            }]
	       }]
	 }
	 * @throws Exception
	 * 
	 *  在线调试接口
	 *  https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=%E8%87%AA%E5%AE%9A%E4%B9%89%E8%8F%9C%E5%8D%95&form=%E8%87%AA%E5%AE%9A%E4%B9%89%E8%8F%9C%E5%8D%95%E5%88%9B%E5%BB%BA%E6%8E%A5%E5%8F%A3%20/menu/create
	 *  
	 **/
	public static void create() throws Exception{
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("'button':[{");
		sb.append("'name':'大幕介绍',");
		sb.append("'type':'click',");
		sb.append("'key':'JFC_GEME_CENTER'");	
		sb.append("},{");
		sb.append("'name':'GC',");
		sb.append("sub_button[{");
		sb.append("'name':'TY',");
		sb.append("'type':'view',");
		sb.append("'url':'http://www.wangdawujin.cn/main/tuya/'");
		sb.append("},{");
		sb.append("'name':'WHT',");
		sb.append("'type':'view',");
		sb.append("'url':'http://www.wangdawujin.cn/main/wanhuatong/'");
		sb.append("},{");
		sb.append("'name':'ZQY',");
		sb.append("'type':'view',");
		sb.append("'url':'http://www.wangdawujin.cn/main/bishengyin/'");
		sb.append("}]");
		sb.append("}]");
		sb.append("}");
		
		String ACCESS_TOKEN="";
		String menuStr=sb.toString();
        NameValuePair[] params = new NameValuePair[1];
        params[0] = new NameValuePair("xyinfo", menuStr);
        String cdsurl="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ACCESS_TOKEN;
        String sendResult=HttpUtils.sendHttpClient(cdsurl, params);
        System.out.println("请求结果="+sendResult);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
