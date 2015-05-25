package com.elearning.web.mybatis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * TODO 动态选择数据源
 * 
 * @author xinglt
 * @date 2014年8月2日 下午3:46:00
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {

		return DataSourceContextHolder.getDatasourceName();
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> dataSourceMap) {

	
		DataSourceContextHolder.setDslist(dataSourceMap);
	
		super.setTargetDataSources(dataSourceMap);
	}

}
