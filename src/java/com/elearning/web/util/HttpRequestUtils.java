package com.elearning.web.util;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.ServletRequestUtils;

/**
 * 
 * TODO HttpRequest公共类
 * 
 * @author xinglt
 * @date 2014年8月4日 下午5:38:20
 *
 */
@SuppressWarnings("rawtypes")
public class HttpRequestUtils {

	private static ConvertUtilsBean convert;

	private static BeanUtilsBean beanUtils;

	static {
		convert = new ConvertUtilsBean();

		convert.register(new Converter() {

			@Override
			public Object convert(Class clz, Object value) {

				if (value == null)
					return null;
				if (value instanceof String) {
					if (StringUtils.isBlank((String) value)) {
						return null;
					}
					try {
						return DateUtils.parseDate(String.valueOf(value), new String[] {
								"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm" });
					} catch (IllegalArgumentException e) {
						throw new ConversionException("ill date patten:" + value);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				return value;
			}
		}, Date.class);

		beanUtils = new BeanUtilsBean(convert, new PropertyUtilsBean());
	}

	/**
	 * 获取HttpServletRequest中Java Bean值
	 * 
	 * @param clz
	 * @param request
	 * @return
	 */
	public static <T> T getBean(Class<T> clz, HttpServletRequest request) {

		T t = null;
		try {
			t = clz.newInstance();
			copyToObject(t, request);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static void copyToObject(Object obj, HttpServletRequest request) {

		try {
			Map<String, String> map = convertString(request);

			beanUtils.copyProperties(obj, map);

		} catch (IllegalAccessException ex) {

			throw new ContextedRuntimeException("", ex);
		} catch (InvocationTargetException ex) {

			throw new ContextedRuntimeException("", ex);
		}
	}

	/**
	 * 获取HttpRequest中所有K-V参数
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> convertString(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();
		for (Iterator iter = request.getParameterMap().entrySet().iterator(); iter.hasNext();) {
			Map.Entry e = (Entry) iter.next();
			String key = String.valueOf(e.getKey());
			String value = ServletRequestUtils.getStringParameter(request, key, "");

			if (!StringUtils.isBlank(value)) {
				map.put(key, value);
			}
		}
		return map;
	}
	
	
	public static void copyToObject(Object obj, JSONObject json) {

		try {
			//Map<String, String> map = convertString(request);

			beanUtils.copyProperties(obj, json);

		} catch (IllegalAccessException ex) {

			throw new ContextedRuntimeException("", ex);
		} catch (InvocationTargetException ex) {

			throw new ContextedRuntimeException("", ex);
		}
	}
	
}
