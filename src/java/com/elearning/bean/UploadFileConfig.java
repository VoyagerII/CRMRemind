package com.elearning.bean;

public class UploadFileConfig {

	private String ext = ""; // 扩展名

	private int maxsize = 0; // 上传最大字节数

	private String uptype = ""; // 上传类型。

	private String imgsrc = ""; // 图片上传地址

	private String httpurl = ""; // 图片访问的HTTP前缀

	public String getHttpurl() {

		return httpurl;
	}

	public void setHttpurl(String httpurl) {

		this.httpurl = httpurl;
	}

	public String getUptype() {

		return uptype;
	}

	public void setUptype(String uptype) {

		this.uptype = uptype;
	}

	public String getExt() {

		return ext;
	}

	public void setExt(String ext) {

		this.ext = ext;
	}

	public int getMaxsize() {

		return maxsize;
	}

	public void setMaxsize(int maxsize) {

		this.maxsize = maxsize;
	}

	public String getImgsrc() {

		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {

		this.imgsrc = imgsrc;
	}

}
