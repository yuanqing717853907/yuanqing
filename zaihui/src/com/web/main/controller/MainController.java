/**     
 * @Title: MainController.java
 * @Package com.web.main.controller
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author vetech
 * @date 2017-5-26 下午2:15:32  
 */
package com.web.main.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.utils.StepLogUtil;
import com.web.main.Config;

/**
 * 前台系统入口
 * 
 * @date 2017-5-26 下午2:15:32
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {
	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	/***
	 * 前台跳转转发专用方法 这个跳转只是响应客户的某个操作动作，然后跳转到具体的业务操作界面，这个方法是不涉及业务逻辑的
	 * 
	 * @param toUrl
	 *            要跳转的页面
	 * @param out
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{toUrl}/{gamemore}", method = RequestMethod.GET)
	public ModelAndView jumpCenter(@PathVariable("toUrl") String toUrl, @PathVariable("gamemore") String gamemore, HttpServletResponse out, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		StepLogUtil.loginfo("用户进入游戏");
		String viewUrl = "";
		boolean flag = false;
		HttpSession session = request.getSession();
		if (StringUtils.equals("-", gamemore)) {
			/* 涂鸦游戏 */
			if (StringUtils.equals("tuya", toUrl)) {
				StepLogUtil.loginfo("用户进入tuy游戏");
				if (!(session.getAttributeNames().hasMoreElements())) {
					if (null != Config.tuyaIdList && Config.tuyaIdList.size() > 0) {
						/* 将id放入到session中 */
						session.setAttribute("tuyaId", Config.tuyaIdList.getFirst());
						StepLogUtil.loginfo("添加到session中的tuyaId为" + Config.tuyaIdList.getFirst());
						/* 添加到session中后移出list */
						Config.tuyaIdList.removeFirst();
					} else {
						flag = true;
					}
				} else {
					StepLogUtil.loginfo("已存在的session中的涂鸦id为" + session.getAttribute("tuyaId"));
					checkSession("tuyaId", session);
				}
				viewUrl = "/web/realtouchtuya";
			}
			/* 音频游戏 */
			if (StringUtils.equals("yinpin", toUrl)) {
				viewUrl = "/web/yinpin";
			}
			/* 我的神秘图片 */
			if (StringUtils.equals("SMTP", toUrl)) {
				viewUrl = "/web/myCodePic";
			}
			/* 烟花游戏主页 */
			if (StringUtils.equals("yanhua", toUrl)) {
				if (!(session.getAttributeNames().hasMoreElements())) {
					if (null != Config.tuyaIdList && Config.tuyaIdList.size() > 0) {
						/* 将id放入到session中 */
						session.setAttribute("yanhuaId", Config.tuyaIdList.getFirst());
						StepLogUtil.loginfo("添加到session中的yanhuaid为" + Config.tuyaIdList.getFirst());
						/* 添加到session中后移出list */
						Config.tuyaIdList.removeFirst();

					} else {
						flag = true;
					}
				} else {
					StepLogUtil.loginfo("已存在的session中的烟花id为" + session.getAttribute("yanhuaId"));
					checkSession("yanhuaId", session);
				}
				viewUrl = "/web/yanhua/index";
			}
		} else {
			/* 具体烟花游戏 */
			if (StringUtils.equals("playyanhua", gamemore)) {
				viewUrl = "/web/yanhua/" + gamemore;
				String pNum = request.getParameter("pNum");
				request.setAttribute("pNum", pNum);
			}
		}
		if (flag) {
			viewUrl = "/web/wait";
		}
		mv.setViewName(viewUrl);
		mv.addObject("showInfo", " 对应的属性值，它是一个对象 ");
		return mv;
	}

	/**
	 * 判断session中所有的键值中有无对应的gameName 如果存在则不出来如果不存在则添加
	 * 
	 * @param gameName
	 * @param session
	 */
	public void checkSession(String gameName, HttpSession session) {
		boolean flag = false;
		Enumeration<String> enumeration = session.getAttributeNames();
		String idValue = "";
		// 获取所有的name 并判断是否存在name为tuyaId的
		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();
			if (gameName.equals(name)) {
				flag = true;
			}
			idValue = (String) session.getAttribute(name);
		}
		// 如果不存在则添加到session中
		if (!flag) {
			StepLogUtil.loginfo("游戏名称为:===>" + gameName + "====id为:===>" + idValue);
			session.setAttribute(gameName, idValue);
		}

	}
}
