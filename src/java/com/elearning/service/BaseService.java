package com.elearning.service;

import org.apache.commons.logging.Log;

import com.elearning.dao.BaseDao;
import com.elearning.web.mybatis.DataSourceContextHolder;

/**
 * 
 * TODO 用于其它Service继承选择数据源
 * 
 * @author xinglt
 * @date 2014年8月2日 下午3:39:06
 *
 */
public abstract class BaseService {

	protected Log log = org.apache.commons.logging.LogFactory.getLog(BaseService.class);

	protected BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {

		this.baseDao = baseDao;
	}

	/**
	 * 业务数据源
	 * 
	 * @throws Exception
	 */
	public void setMasterDB() throws Exception {

		setDatasourceName("masterdb");
	}

	/**
	 * 设置数据源
	 * 
	 * @param sName
	 * @throws Exception
	 */
	private void setDatasourceName(String sName) throws Exception {

		DataSourceContextHolder.setDatasourceName(sName);
	}

}
