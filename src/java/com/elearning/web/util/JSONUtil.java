package com.elearning.web.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;

/**
 * JSON公共类 TODO 类描述
 * 
 * @author guocy
 * @date 2014年8月6日 下午1:49:03
 *
 */
public class JSONUtil {

	public static void main(String args[]) {
	}

	/**
	 * 根据map生成json
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getJSONStrByMap(Map map) {

		JSONObject jobj = JSONObject.fromObject(map);

		return jobj.toString();
	}

	/**
	 * bean生成json
	 * 
	 * @param m
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getJSON(Object object) {

		if (object instanceof Map) {
			return getJSONStrByMap((Map) object);
		}

		JSONArray jsonArray = JSONArray.fromObject(object);

		return jsonArray.toString();
	}

	/**
	 * 
	 * @param ofs
	 * @return
	 */
	public static String BeanToJSONString(Object ofs, String dateType) {

		JsonConfig config = new JsonConfig();

		if (NullUtil.isNotNull(dateType) && dateType.toUpperCase().equals("YMD".toUpperCase())) {
			config.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessorDate("yyyy-MM-dd"));
		} else {
			config.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessorDate("yyyy-MM-dd HH:mm:ss"));
		}

		JSONObject json = JSONObject.fromObject(ofs, config);
		return json.toString();
	}

	/**
	 * 
	 * @param ofs
	 * @return
	 */
	public static String ArrayToJSONString(Object ofs, String dateType) {

		JsonConfig config = new JsonConfig();

		if (NullUtil.isNotNull(dateType) && dateType.toUpperCase().equals("YMD".toUpperCase())) {
			config.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessorDate("yyyy-MM-dd"));
		} else {
			config.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessorDate("yyyy-MM-dd HH:mm:ss"));
		}

		JSONArray json = JSONArray.fromObject(ofs, config);

		return json.toString();
	}

	/**
	 * 
	 * @param json
	 * @param classd
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object JSONStringToBean(String json, Class classd) {

		String[] formats = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };

		JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));

		JSONObject jsonObject = JSONObject.fromObject(json);

		return JSONObject.toBean(jsonObject, classd);
	}

}

class TimestampMorpher extends AbstractObjectMorpher {

	/*** 日期字符串格式 */
	private String[] formats;

	public TimestampMorpher(String[] formats) {

		this.formats = formats;
	}

	public Object morph(Object value) {

		if (value == null) {
			return null;
		}
		if (Date.class.isAssignableFrom(value.getClass())) {
			return (Timestamp) value;
		}
		if (!supports(value.getClass())) {
			throw new MorphException(value.getClass() + " 是不支持的类型");
		}
		String strValue = (String) value;
		SimpleDateFormat dateParser = null;
		for (int i = 0; i < formats.length; i++) {
			if (null == dateParser) {
				dateParser = new SimpleDateFormat(formats[i]);
			} else {
				dateParser.applyPattern(formats[i]);
			}
			try {
				return new Date(dateParser.parse(strValue.toLowerCase()).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class morphsTo() {

		return Date.class;
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {

		return String.class.isAssignableFrom(clazz);
	}

}

class JsonValueProcessorDate implements JsonValueProcessor {

	private String format = "yyyy-MM-dd";

	public JsonValueProcessorDate() {

		super();
	}

	public JsonValueProcessorDate(String format) {

		super();
		this.format = format;
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {

		String[] obj = {};
		if (value instanceof Date[]) {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = sf.format(dates[i]);
			}
		}
		return obj;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {

		if (value == null)
			return null;

		if (value instanceof java.util.Date) {
			String str = new SimpleDateFormat(format).format((Date) value);
			return str;
		}
		return value.toString();
	}

	public String getFormat() {

		return format;
	}

	public void setFormat(String format) {

		this.format = format;
	}

}