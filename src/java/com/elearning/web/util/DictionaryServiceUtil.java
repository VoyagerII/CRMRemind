package com.elearning.web.util;

import org.springframework.beans.factory.InitializingBean;

import com.elearning.dao.BaseDao;
import com.elearning.entity.Admin;

/**
 * 用于加载常用字典表数据，在service中使用
 */
public class DictionaryServiceUtil implements InitializingBean {

	private static BaseDao mydao = null;

	public static String EMAILSTRING = "";

	public static String EMAILSTRINGBYPWD = "";

	@Override
	public void afterPropertiesSet() throws Exception {

		// TODO Auto-generated method stub

	}

	/**
	 * 根据主键获取用户id
	 * 
	 * @param userid
	 * @return
	 */
	public static Admin getRegistUserById(Long id) {

		return (Admin) mydao.selectOne("com.elearning.AdminMapper.selectByPrimaryKey", id);
	}
}
