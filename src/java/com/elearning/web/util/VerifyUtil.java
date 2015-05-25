package com.elearning.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ilia
 * @version 创建时间：2014年11月27日 上午10:46:52
 */
public class VerifyUtil {
	
	/**
	 * 验证手机号码
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		Pattern pattern	=	Pattern.compile("^(1[0-9[0-9])\\d{8}$");
		Matcher matcher	=	pattern.matcher(phone);
		return matcher.matches();
	}
	
	/**
	 * 验证email
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * 验证用户名邮箱类型
	 * @param register
	 * @return
	 */
	public static String checkRegister(String register,int minLength,int maxLength) {
		if (register.length()>maxLength||register.length()<minLength) {
			return "loginname length error";
		}
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(register);
		if (matcher.matches()) {
			return null;
		}
		return "verify error";
	}
	
	/**
	 * 验证用户名字符类型
	 * @param register
	 * @return
	 */
	public static String checkRegisterCom(String register,int minLength,int maxLength) {
		if (register.length()>maxLength||register.length()<minLength) {
			return "loginname length error";
		}
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(register);
		if (matcher.matches()) {
			return null;
		}
		return "verify error";
	}
	
	/**
	 * 验证密码
	 * @param password
	 * @return
	 */
	public static String checkPassword(String password,int minLength,int maxLength) {
		if (password.length()>maxLength||password.length()<minLength) {
			return "password length error";
		}
		Pattern pattern = Pattern.compile("^([!|.|$|#|~|&|*|^|@+|\\w]+)$");
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			return null;
		}
		return "verify error";
	}
	
}
