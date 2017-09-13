/**     
 * @Title: TestSendReturn.java
 * @Package test
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author vetech
 * @date 2017-8-20 下午9:39:16  
 */
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestSendReturn {
	/**server**/
	@RequestMapping(value = "/loginTest", method = RequestMethod.POST)
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// JSONObject json=JSONObject.fromObject(data);
		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		// 将资料解码
		String reqBody = sb.toString();
		JSONObject json = JSONObject.fromObject(reqBody);
		String data = "{'isAdmin':'true', 'usename':wsf}";

		PrintWriter writer = response.getWriter();
		writer.write(data); // 这里是你要返回的字符串
		writer.flush();
		writer.close();
	}
}
