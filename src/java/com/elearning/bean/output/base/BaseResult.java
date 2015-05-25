package com.elearning.bean.output.base;

import java.io.Serializable;

/**
 * 
 * TODO APP基础bean
 * 
 * @author guocy
 * @date 2015年1月6日 上午9:39:06
 *
 */
public class BaseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7855384890804340511L;
	private String resultcode;
	private String resultmsg;
	
	public String getResultcode() {
	
		return resultcode;
	}
	
	public void setResultcode(String resultcode) {
	
		this.resultcode = resultcode;
	}
	
	public String getResultmsg() {
	
		return resultmsg;
	}
	
	public void setResultmsg(String resultmsg) {
	
		this.resultmsg = resultmsg;
	}
	
	
}
