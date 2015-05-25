package com.elearning.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.elearning.entity.Admin;
import com.elearning.entity.Department;
import com.elearning.service.BaseService;

public class LoginService extends BaseService {

	/**
	 * 插入用户表
	 * 
	 * @param admin
	 * @return
	 */
	public Admin insertAdmin(Admin admin) {

		int count = (Integer) this.baseDao.insert("com.elearning.AdminMapper.insert", admin);
		return (count > 0) ? admin : null;
	}

	/**
	 * 根据主键查找
	 * 
	 * @param id
	 * @return
	 */
	public Admin selectAdminByPrimaryKey(int id) {

		return (Admin) this.baseDao.selectOne("com.elearning.AdminMapper.selectByPrimaryKey", id);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param admin
	 * @return
	 */
	public int updateAdmin(Admin admin) {

		return this.baseDao.update("com.elearning.AdminMapper.updateByPrimaryKeySelective", admin);

	}

	/**
	 * 根据用户名密码登陆
	 * 
	 * @param adminLogin
	 * @param adminPass
	 * @return
	 */
	public Admin selectLogin(String adminLogin, String adminPass) {

		Map<String, String> paras = new HashMap<String, String>();
		paras.put("adminLogin", adminLogin);
		paras.put("adminPass", adminPass);
		return (Admin) this.baseDao.selectOne("com.elearning.LoginActionMapper.selectLogin", paras);
	}
	/**
	 * 根据id获取部门信息
	 * @param departmentId
	 * @return
	 */
	public Department selectDepartmentByPrimaryKey(int departmentId){
		return (Department) this.baseDao.selectOne("com.elearning.DepartmentMapper.selectByPrimaryKey", departmentId);
	}
	/**
	 * 根据用户名获取用户信息
	 * @param id
	 * @return
	 */
	public Admin selectAdminByadminLogin(String adminLogin) {
		return (Admin) this.baseDao.selectOne("com.elearning.LoginActionMapper.selectAdminByadminLogin", adminLogin);
	}
}
