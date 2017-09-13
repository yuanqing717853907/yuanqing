package com.web.main.business;
import java.util.concurrent.Callable;
import com.web.main.Config; 
/**
 * <p>Description: 生产坐标数据，并存放到静态数据容器中</p>
 * @date 2017-8-14 下午3:13:53
 */
public class MakeXyThread implements Callable<Object> {
	/**新产生的坐标数据**/
	private static String xyinfo;
	public static void madeXy() throws InterruptedException{
		System.out.println("存入新坐标:"+xyinfo);
		Config.xyLinkList.add(xyinfo);
	}
	@Override
	public Object call() throws Exception {
		madeXy();
		return null;
	}
	public MakeXyThread(String xy){
		this.xyinfo=xy;
	}
	/**
	 * 获取新产生的坐标数据
	 * @return xyinfo
	 */
	public String getXyinfo() {
		return xyinfo;
	}
	/**
	 * @param 设置[ 新产生的坐标数据 ]
	 */
	public void setXyinfo(String xyinfo) {
		this.xyinfo = xyinfo;
	}
}
