package com.elearning.web.mybatis;

import java.util.ArrayList;
import java.util.Map;

/**
 * 
 * TODO 多数据源进行数据库操作
 * 
 * @author xinglt
 * @date 2014年8月2日 下午3:44:29
 *
 */
public class DataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static Map<Object, Object> DataSourcelist = null;

	public static void setDslist(Map<Object, Object> dslist) {

		DataSourcelist = dslist;
	}

	public static String getDatasourceName() {

		return contextHolder.get();
	}

	/**
	 * 
	 * 设置使用的数据源, 名字要与dataSourceContext.xml 定义一致
	 * 
	 * @param name
	 * @throws Exception
	 */
	public static synchronized void setDatasourceName(String name) throws Exception {

		if (DataSourcelist != null && DataSourcelist.get(name) != null) {
			contextHolder.set(name);
		} else {
			throw new Exception("数据源不存在.");

		}
	}

	public static void main(String args[]) {

	}
}
