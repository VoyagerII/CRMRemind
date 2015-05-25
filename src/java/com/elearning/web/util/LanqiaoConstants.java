package com.elearning.web.util;

/**
 * 保存系统内的常量， 都从配置文件获得。
 * 
 * @author UI
 *
 */
public class LanqiaoConstants {

	public static String staticurl = "";

	public static String uploadurl = "";

	public static String webhost = "";

	public static String webdomain = "";

	
	public String getStaticurl() {
	
		return staticurl;
	}

	
	public void setStaticurl(String staticurl) {
	
		LanqiaoConstants.staticurl = staticurl;
	}

	
	public String getUploadurl() {
	
		return uploadurl;
	}

	
	public void setUploadurl(String uploadurl) {
	
		LanqiaoConstants.uploadurl = uploadurl;
	}

	
	public String getWebhost() {
	
		return webhost;
	}

	
	public void setWebhost(String webhost) {
	
		LanqiaoConstants.webhost = webhost;
	}

	
	public String getWebdomain() {
	
		return webdomain;
	}

	
	public void setWebdomain(String webdomain) {
	
		LanqiaoConstants.webdomain = webdomain;
	}


}
