/**     
* @Title: MyBackController.java
* @Package cn.vetech.web.yclink
* @Description: TODO(用一句话描述该文件做什么)   
* @author vetech
* @date 2017-4-19 下午2:08:53  
*/   
package com.jackie.springmvc.handlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *测试restful请求风格多个参数测试
 */
@Controller
@RequestMapping(value = "/yourback")
public class YourBackController {
	
	@RequestMapping(value = "/{vendorID}/commyback/{orderID}",method=RequestMethod.GET)
	public void commyback_MoreParam(@PathVariable String vendorID,@PathVariable String orderID,HttpServletResponse out) throws Exception {
		System.out.println("请求参数="+vendorID+"/"+orderID);
		out.getWriter().write("param is ["+vendorID+","+orderID+"]");
	}
}
