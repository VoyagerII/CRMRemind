package com.elearning.bean.output.base;

import java.io.Serializable;

/**
 * 
 * TODO 状态码定义类
 * 
 * @author guocy
 * @date 2015年1月6日 下午2:17:31
 *
 */
public class ResultCode implements Serializable{

	/**
	 * 成功
	 */
	public final static String SUCCESS = "000";
	/**
	 * 校验失败
	 */
	public final static String CHECKERROR = "100";
	/**
	 * 参数不合法
	 */
	public final static String ILLEGALPARA = "101";
	/**
	 * 接口异常
	 */
	public final static String INTERFACEUNUSUAL = "102";
	/**
	 * 非登录状态
	 */
	public final static String NOLOGIN = "103";
	/**
	 * 发送验证码失败
	 */
	public final static String SENDERROR = "104";
	/**
	 * 地址禁止访问
	 */
	public final static String FORBIDURL = "105";
	/**
	 * 执行错误
	 */
	public final static String NORMALERROR = "106";
	/**
	 * 用户名密码校验失败
	 */
	public final static String USRPWILLEGAL = "201";
	/**
	 * 用户名已被使用
	 */
	public final static String EMAILORMOBUSED = "202";
	/**
	 * 验证码错误
	 */
	public final static String VERIFYCODEERRO = "203";
	/**
	 * 原有密码输入错误
	 */
	public final static String OLDPWERRO = "204";
	/**
	 * 浏览器不支持cookie
	 */
	public final static String COOKIEERROR = "205";
	/**
	 * 修改用户信息失败
	 */
	public final static String UPADMINERROR = "206";
	/**
	 * 上传文件类型不允许
	 */
	public final static String FILETYPEUNILLEGE = "501";
	/**
	 * 上传文件太大了
	 */
	public final static String FILETOOBIG = "502";
	/**
	 * 上传文件不能为空
	 */
	public final static String FILENOEMPTY = "503";
	
	public static String getResultMsg(String resultCode){
		if(resultCode.equals(SUCCESS)){
			return "成功";
		}else if(resultCode.equals(CHECKERROR)){
			return "校验失败";
		}else if(resultCode.equals(ILLEGALPARA)){
			return "参数不合法";
		}else if(resultCode.equals(INTERFACEUNUSUAL)){
			return "接口异常";
		}else if(resultCode.equals(NOLOGIN)){
			return "用户不是登录状态";
		}else if(resultCode.equals(USRPWILLEGAL)){
			return "用户名密码校验失败";
		}else if(resultCode.equals(EMAILORMOBUSED)){
			return "用户名已被使用";
		}else if(resultCode.equals(VERIFYCODEERRO)){
			return "验证码错误";
		}else if(resultCode.equals(OLDPWERRO)){
			return "原有密码输入错误";
		}else if(resultCode.equals(SENDERROR)){
			return "发送验证码失败";
		}else if(resultCode.equals(NORMALERROR)){
			return "执行错误";
		}else if(resultCode.equals(FORBIDURL)){
			return "地址禁止访问";
		}else if(resultCode.equals(FILETYPEUNILLEGE)){
			return "上传文件类型不允许";
		}else if(resultCode.equals(FILETOOBIG)){
			return "上传文件太大了";
		}else if(resultCode.equals(FILENOEMPTY)){
			return "上传文件不能为空";
		}else if(resultCode.equals(COOKIEERROR)){
			return "登录失败,浏览器不支持cookie, 请打开设置";
		}else if(resultCode.equals(UPADMINERROR)){
			return "修改用户信息失败";
		}else
			return "";
	}
}
