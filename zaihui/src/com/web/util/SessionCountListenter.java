/**     
* @Title: SessionCountListenter.java
* @Package com.web.util
* @Description: TODO(用一句话描述该文件做什么)   
* @author vetech
* @date 2017-7-13 下午9:47:28  
*/   
package com.web.util;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.web.main.Config;
/**
 *  参考：http://blog.csdn.net/qq_21439971/article/details/51478989
 * <p>Title: SessionCountListenter</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @authorvetech 
 * @date 2017-7-13 下午9:47:28
 */
public class SessionCountListenter implements HttpSessionAttributeListener{
	private final Logger logger = LoggerFactory.getLogger(SessionCountListenter.class);
	/***
	 * 代表进入不同的游戏
	 */
	public final static String LISTENER_NAME_TUYA = "tuyaId";
	public final static String LISTENER_NAME_YINPIN = "geme_yinpin";
	public final static String LISTENER_NAME_YANHUA = "yanhuaId";
	
	
    /**
     * 定义存储客户登录session的集合.
     */
    private static List sessions_tuya = new ArrayList();
    private static List sessions_yinpin = new ArrayList();
    private static List session_yanhua=new ArrayList();
	/***
	 * 加入session时的监听方法.
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		if (LISTENER_NAME_TUYA.equals(arg0.getName())) {
			sessions_tuya.add(arg0.getValue());
        }else if (LISTENER_NAME_YINPIN.equals(arg0.getName())) {
			sessions_yinpin.add(arg0.getValue());
        }else if(LISTENER_NAME_YANHUA.equals(arg0.getName())){
        	session_yanhua.add(arg0.getValue());
        }
		logger.info("增加session");
	}
	/**
	 * session失效时做数量减员操作(删除不掉呢)
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		if (LISTENER_NAME_TUYA.equals(arg0.getName())) {
			sessions_tuya.remove(arg0.getValue());
        	String s=(String) arg0.getValue();
        	Config.tuyaIdList.addLast(s);
        	/***
        	 * 当session失效的时候 将tuyaid从list中移除  然后通过这个list里面的值 来判断游戏是新旧
        	 */
        	Config.tuyaIdOldList.remove(s);
        }else if (LISTENER_NAME_YINPIN.equals(arg0.getName())) {
			sessions_yinpin.remove(arg0.getValue());
        }else if(LISTENER_NAME_YANHUA.equals(arg0.getName())){
        	session_yanhua.remove(arg0.getValue());
        	String s=(String) arg0.getValue();
        	Config.tuyaIdList.addLast(s);
        	/***
        	 * 当session失效的时候 将tuyaid从list中移除  然后通过这个list里面的值 来判断游戏是新旧
        	 */
        	Config.tuyaIdOldList.remove(s);
        }
		logger.info("减员session");
	}
 
	/***
	 * session覆盖时的监听方法.
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		logger.info("attributeReplaced");
	}
	public static List getTuYaSessions() {
        return sessions_tuya;
    }
	public static List getYinPinSessions() {
        return sessions_yinpin;
    }
}
