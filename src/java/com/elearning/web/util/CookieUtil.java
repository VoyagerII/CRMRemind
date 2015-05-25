package com.elearning.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookieUtil {

	static Log logger = LogFactory.getLog(CookieUtil.class);

	private static final String USER_KEY = "ELEARN_M#!@_2342##@1%@#$@%1121^$%$";

	public static final String COOKIE_KEY = "ELEARN";

	private static final String SPLITER = "#||#";

	private static final String SPLITER_REGEX = "#\\|\\|#";

	public static final int COOKIE_LIVE_ONE_DAY = 60 * 60 * 24;

	public static final int COOKIE_LIVE_SESSION = -1;

	/**
	 * 设置登录Cookie
	 * 
	 * @param response
	 * @param luserid
	 * @return
	 */
	public static boolean setLoginUseridCookie(HttpServletResponse response, long loginUserId, String domain) {

		StringBuffer sb = new StringBuffer();
		sb.append(loginUserId + "").append(USER_KEY);

		String sign = SecretUtil.MD5(sb.toString());
		String svalue = loginUserId + SPLITER + sign;

		return setCookie(response, COOKIE_KEY, svalue, domain);
	}

	public static Map<String, Object> getUserFromCookie(HttpServletRequest request) {

		String cookieValue = CookieUtil.getCookie(request, COOKIE_KEY);

		if (null != cookieValue) {
			try {
				cookieValue = URLDecoder.decode(cookieValue, Charset.defaultCharset().toString());
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}

		String[] strs = cookieValue.split(SPLITER_REGEX);

		// debug
		for (String s : strs) {
			try {
				logger.debug(s);
			} catch (Exception e) {
			}
		}

		// userid || key
		StringBuffer sb = new StringBuffer();
		sb.append(strs[0]);

		String sign = SecretUtil.MD5(sb.toString() + USER_KEY);

		if (sign.equals(strs[1])) {
			Long loginUserId = new Long(strs[0]);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", loginUserId);

			return map;
		}

		return null;
	}

	/**
	 * 登录设置
	 * 
	 * @param response
	 * @param key
	 * @param value
	 * @param domain
	 * @return
	 */
	public static boolean setCookie(HttpServletResponse response, String key, String value, String domain) {

		try {

			Cookie cookie = new Cookie(key, value); // will be throw new
													// IllegalArgumentException(errMsg);
			cookie.setPath("/"); // very important
			cookie.setDomain(domain);
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	private static String getCookie(HttpServletRequest request, String key) {

		try {

			Cookie[] cookies = request.getCookies();
			if ((cookies == null) || (cookies.length == 0)) {
				return null;
			}

			for (Cookie cooky : cookies) {
				if (cooky.getName().equals(key)) {
					return cooky.getValue();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public static Long getUserIDFromCookie(HttpServletRequest request) {

		Map<String, Object> map = getUserFromCookie(request);
		if (map != null) {
			Long userid = (Long) map.get("userId");
			return userid;
		}
		return null;
	}

	/**
	 * remove cookie from user brower by key .
	 */
	public static void removeCookie(HttpServletResponse response, String key) {

		try {
			Cookie cookie = new Cookie(key, null); // will be throw new
													// IllegalArgumentException(errMsg);
			cookie.setPath("/"); // very important
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());

		}

	}
}
