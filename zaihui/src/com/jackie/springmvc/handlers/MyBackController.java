/**     
* @Title: MyBackController.java
* @Package cn.vetech.web.yclink
* @author vetech
* @date 2017-4-19 下午2:08:53  
*/   
package com.jackie.springmvc.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *测试restful请求风格
 */
@Controller
@RequestMapping(value = "/webdomain")
public class MyBackController {
	@RequestMapping(value = "/{orderID}",method=RequestMethod.GET)
    public void inMyFunction(@PathVariable("orderID") String orderID,HttpServletResponse out) throws IOException {
		System.out.println("请求参数="+orderID);
		out.getWriter().write("param is ["+orderID+"]");
    }
}
