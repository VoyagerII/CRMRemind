package com.elearning.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 
 * TODO 数据层
 * 
 * @author xinglt
 * @date 2014年7月24日 下午3:23:47
 *
 */

public class BaseDao extends SqlSessionDaoSupport {
	

	/**
	 * @param type
	 * @return
	 */
	public <T> T getMapper(Class<T> cls) {

		return getSqlSession().getMapper(cls);
	}

	/**
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public Object selectOne(String statement, Object parameter) {

		return getSqlSession().selectOne(statement, parameter);
	}

	/**
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public List<?> selectList(String statement, Object parameter) {

		return getSqlSession().selectList(statement, parameter);
	}

	/**
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public <K, V> Map<K, V> selectMap(String statement, String parameter) {

		return getSqlSession().selectMap(statement, parameter);
	}

	/**
	 * @param statement
	 * @param parameter
	 * @param key
	 * @return
	 */
	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String key) {

		return getSqlSession().selectMap(statement, parameter, key);
	}

	/**
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int insert(String statement, Object parameter) {

		return getSqlSession().insert(statement, parameter);
	}

	/**
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int update(String statement, Object parameter) {

		return getSqlSession().update(statement, parameter);
	}

	/**
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int delete(String statement, Object parameter) {

		return getSqlSession().delete(statement, parameter);
	}
}
