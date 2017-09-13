package com.web.util;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;


/**
 * HTTP工具类
 * 
 * @author zhangyang
 * @version [版本号, 2012-6-20]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class HttpUtils {

	/**
	 * GET发送URL 以UTF-8编码默认发送
	 * 
	 * @param urlvalue
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String sendUrl(String urlvalue) throws Exception {
		return sendUrl(urlvalue, null);
	}

	/**
	 * GET发送URL
	 * 
	 * @param urlvalue
	 * @param encode
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String sendUrl(String urlvalue, String encode) throws Exception {
		if (encode == null) {
			encode = "UTF-8";
		}
		StringBuffer inputLine = new StringBuffer();
		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(20000);
			urlConnection.setReadTimeout(120000);
			urlConnection.setDoOutput(true); 
			urlConnection.connect();
			InputStream in1=urlConnection.getErrorStream();
			int i =urlConnection.getResponseCode();
			Reader rd = new InputStreamReader(urlConnection.getInputStream(), encode);
			BufferedReader in = new BufferedReader(rd);
			String line = "";
			while ((line = in.readLine()) != null) {
				inputLine.append(line);
				inputLine.append("\n");
			}
			in.close();
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("URL发送数据失败" + e.getMessage());
		}
		return StringUtils.trimToEmpty(inputLine.toString());
	}

	/**
	 * POST发送URL 以UTF-8编码默认发送
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String sendHttpClient(String url, NameValuePair[] params) throws Exception {
		return HttpUtils.sendHttpClient(url, params, null);
	}

	/**
	 * POST发送URL
	 * 
	 * @param url
	 * @param params
	 * @param encode
	 *            中文编码
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String sendHttpClient(String url, NameValuePair[] params, String encode) throws Exception {
		if (StringUtils.isBlank(encode)) {
			encode = "UTF-8";
		}
		Http http = Http.getInstance();
		HttpClient client = http.getClient();
		PostMethod method = new PostMethod(url);
        method.addRequestHeader("Connection","keep-alive");
		// method.setRequestBody(params);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encode);
		method.addParameters(params);
		int statusCode;
		try {
			statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				byte[] responseBody = method.getResponseBody();
				String res = StringUtils.trimToEmpty(new String(responseBody, encode));
				return res;
			}
		} catch (Exception e) {
			 e.printStackTrace();
			throw new Exception("HTTP发送数据失败" + e.getMessage());
		} finally {
			method.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return "";
	}

	public static String sendGet(String url,String encode) throws Exception{
		 URL localurl = new URL(url) ;
         URLConnection uc = localurl.openConnection() ;
         uc.setRequestProperty("User-Agent","Mozilla/3.5.7 (compatible; MSIE 5.0; Windows NT; DigExt)");
         uc.connect() ;
         StringBuffer sb=new StringBuffer();
         InputStream localObject1 = localurl.openStream();
         System.out.println(localObject1.available()) ;
         byte[] localObject2 = new byte[131072];
         StringBuffer localStringBuffer = new StringBuffer() ;
         int j = 0 ;
         while(true){
             if(localObject1 .available()>0){
            	 int y;
                 if((y=localObject1.read(localObject2))>0){
                     sb.append(new String(localObject2,0,y,encode)) ;
                 } else{
                     break ;
                 }
             }else if(localObject1.available()==0){
                 System.out.println("与服务器的链接已中断") ;
                 break ;
             }
          }
         localObject1.close() ;
         return sb.toString();
	}
	/**
	 * POST发送URL
	 * 
	 * @param url
	 * @param params
	 * @param requsetEncode
	 * @param responseEncode
	 *            中文编码
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String sendHttpClient(String url, NameValuePair[] params, String requsetEncode, String responseEncode)
			throws Exception {
		if (StringUtils.isBlank(requsetEncode)) {
			requsetEncode = "UTF-8";
		}

		if (StringUtils.isBlank(responseEncode)) {
			responseEncode = "GBK";
		}
		Http http = Http.getInstance();
		HttpClient client = http.getClient();
		PostMethod method = new PostMethod(url);
		// method.setRequestBody(params);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, requsetEncode);
		method.addParameters(params);
		int statusCode;
		try {

			statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				byte[] responseBody = method.getResponseBody();
				String res = StringUtils.trimToEmpty(new String(responseBody, responseEncode));
				return res;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("HTTP发送数据失败" + e.getMessage());
		} finally {
			method.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return "";
	}

	/**
	 * POST HTTPS请求
	 * 
	 * @param url
	 *            请求URL
	 * @param xml
	 *            参数XML
	 * @param jksPath
	 *            证书路径
	 * @param jksPassWord
	 *            证书密码
	 * @return [参数说明]
	 * 
	 * @return InputStream [返回类型说明] 返回文件流
	 * @throws Exception
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static InputStream sendHttpsUrlForPost(String url, String xml, String jksPath, String jksPassWord,
			String sendEncode) throws Exception {
		OutputStream out = null;
		HttpsURLConnection connect = null;
		if (StringUtils.isBlank(sendEncode)) {
			sendEncode = "GBK";
		}
		try {
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(jksPath), jksPassWord.toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks, jksPassWord.toCharArray());

			// 同位体验证信任决策源
			TrustManager[] tm = { new MyX509TrustManager() };

			// 初始化安全套接字
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(kmf.getKeyManagers(), tm, null);
			SSLSocketFactory factory = sslContext.getSocketFactory();
			URL _url = new URL(url);
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String urlHostName, SSLSession session) {
					System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost() + " port."
							+ session.getPeerPort());
					return true;
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			connect = (HttpsURLConnection) _url.openConnection();
			connect.setSSLSocketFactory(factory);
			connect.setDoOutput(true);
			connect.setDoInput(true);
			connect.setReadTimeout(1000 * 60);

			out = connect.getOutputStream();
			out.write(xml.getBytes(sendEncode));
			out.flush();
			return connect.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * POST HTTPS请求
	 * 
	 * @param url
	 *            请求URL
	 * @param xml
	 *            参数XML
	 * @param jksPath
	 *            证书路径
	 * @param jksPassWord
	 *            证书密码
	 * @param rtnEncode
	 *            返回字符串编码
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明] 返回字符串
	 * @throws Exception
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String sendHttpsUrlForPost(String url, String xml, String jksPath, String jksPassWord,
			String sendEncode, String rtnEncode) throws Exception {
		InputStream is = sendHttpsUrlForPost(url, xml, jksPath, jksPassWord, sendEncode);
		if (rtnEncode == null) {
			rtnEncode = "UTF-8";
		}
		String reqData = null;
		if (is != null) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] receiveBuffer = new byte[2048];
			int readBytesSize;
			try {
				readBytesSize = is.read(receiveBuffer);
				while (readBytesSize != -1) {
					bos.write(receiveBuffer, 0, readBytesSize);
					readBytesSize = is.read(receiveBuffer);
				}
				reqData = new String(bos.toByteArray(), rtnEncode);
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception(e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return reqData;
	}

	public static String sendHttpUrlForPost(String url, String xml, String sendEncode, String rtnEncode)
			throws Exception {
		InputStream is = sendHttpUrlForPost(url, xml, sendEncode);
		if (rtnEncode == null) {
			rtnEncode = "UTF-8";
		}
		String reqData = null;
		if (is != null) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] receiveBuffer = new byte[2048];
			int readBytesSize;
			try {
				readBytesSize = is.read(receiveBuffer);
				while (readBytesSize != -1) {
					bos.write(receiveBuffer, 0, readBytesSize);
					readBytesSize = is.read(receiveBuffer);
				}
				reqData = new String(bos.toByteArray(), rtnEncode);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return reqData;
	}

	public static InputStream sendHttpUrlForPost(String url, String xml, String sendEncode) throws Exception {
		OutputStream out = null;
		HttpURLConnection connect = null;
		if (StringUtils.isBlank(sendEncode)) {
			sendEncode = "GBK";
		}
		try {
			URL _url = new URL(url);
			connect = (HttpURLConnection) _url.openConnection();
			connect.setDoOutput(true);
			connect.setDoInput(true);
			connect.setReadTimeout(1000 * 60);

			out = connect.getOutputStream();
			out.write(xml.getBytes(sendEncode));
			out.flush();
			return connect.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 发送post数据流
	 * 
	 * @param url
	 * @param in
	 * @param encode
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
    public static String sendInputStreamForPost(String url, InputStream in,
	    String encode) {
	HttpClient client = new HttpClient();
	PostMethod method = new PostMethod(url);
	try {
	    RequestEntity fr = new InputStreamRequestEntity(in);
	    method.setRequestEntity(fr);
	    client.executeMethod(method);
	    if (method.getStatusCode() == HttpStatus.SC_OK) {
		byte[] responseBody = method.getResponseBody();
		String res = StringUtils.trimToEmpty(new String(responseBody,
			encode));
		return res;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (method != null) {
		method.releaseConnection();
	    }
	}
	return "";
    }

	public static void main(String[] args) {
		String s = "无数据";
		try {
			s = HttpUtils
					.sendHttpUrlForPost(
							"http://port.e-bestone.net/Sky/SkyYs.ashx",
							"<Request><Ticket><Custid>XYJADMIN/XYJADMIN ASMS管理员</Custid><Orderid>50847048491212170</Orderid><OldOrderID/><tkno>4123432123</tkno><tkStatus>1</tkStatus><pnr>2TF01</pnr><tktype>1</tktype><passenger>C/SA</passenger><IdentityType/><IdentityNo>.</IdentityNo><cfdatetime>2012-12-17 11:11:00</cfdatetime><voyage>WUHPVGSHA</voyage><flightno>CA211</flightno><cabin>Y</cabin><eventdate>2013-01-21 14:31:27</eventdate><amountevent>1209.4</amountevent></Ticket></Request>",
							"UTF-8", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ssssss");
		System.out.println(s);
	}
}
