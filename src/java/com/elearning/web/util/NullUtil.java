package com.elearning.web.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.elearning.web.util.DictionaryCacheKeys.returnStatus;

/**
 * 
 * TODO 可转化为String的Object对象空值判断
 * 
 * @author xinglt
 * @date 2014年8月7日 上午11:28:05
 *
 */
public class NullUtil {

	/**
	 * 判断是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {

		if (null != obj && null != obj.toString() && obj.toString().length() > 0 && null != obj.toString() && obj.toString().trim().length() > 0 && StringUtils.isNotBlank(obj.toString())
				&& StringUtils.isNotEmpty(obj.toString())) {

			return false;
		}

		return true;
	}
	/**
	 * 判断是否是空 多参数
	 * 若参数中存在空，则返回ture
	 * @param objects
	 * @return
	 */
	public static boolean isNull(Object...objects){
		for(Object obj:objects){
			if(isNull(obj)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {

		if (null != obj && null != obj.toString() && obj.toString().length() > 0 && null != obj.toString() && obj.toString().trim().length() > 0 && StringUtils.isNotBlank(obj.toString())
				&& StringUtils.isNotEmpty(obj.toString())) {

			return true;
		}

		return false;
	}

	/**
	 * 判断是否不为空
	 * 
	 * @param <T>
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> boolean isNotNull(Map<Long, T> objMap, Long objKey) {

		if (null != objMap && objMap.size() > 0 && isNotNull(objKey) && null != objMap.get(objKey)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断是否不为空
	 * 
	 * @param <T>
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> boolean isNotNull(Map<String, T> objMap, String objKey) {

		if (null != objMap && objMap.size() > 0 && null != objKey && null != objMap.get(objKey)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断不为空并返回String类型值
	 * 
	 * @param obj
	 * @return
	 */
	public static String isNotNullResultStringValue(Object obj) {

		if (null != obj && null != obj.toString() && obj.toString().length() > 0 && null != obj.toString().trim() && obj.toString().trim().length() > 0 && StringUtils.isNotBlank(obj.toString())
				&& StringUtils.isNotEmpty(obj.toString())) {

			return obj.toString().trim();
		}

		return null;
	}

	/**
	 * 判断不为空并返回值
	 * 
	 * @param obj
	 * @return
	 */
	public static Long isNotNullResultLongValue(Object obj) {

		if (null != obj && null != obj.toString() && null != obj.toString().trim() && StringUtils.isNotBlank(obj.toString()) && StringUtils.isNotEmpty(obj.toString())) {

			return Long.parseLong(obj.toString());
		}

		return null;
	}

	/**
	 * 判断不为空并返回值
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer isNotNullResultIntegerValue(Object obj) {

		if (null != obj && null != obj.toString() && null != obj.toString().trim() && StringUtils.isNotBlank(obj.toString()) && StringUtils.isNotEmpty(obj.toString())) {

			return Integer.parseInt(obj.toString());
		}

		return null;
	}
}
