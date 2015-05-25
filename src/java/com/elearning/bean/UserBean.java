package com.elearning.bean;

import com.elearning.entity.Admin;

/**
 * 用户bean
 * 
 * @author 白凤吉
 * @date 2015年4月21日 下午3:53:07
 *
 */
public class UserBean {

	private String departmentname;

	private Admin admin;

	public String getDepartmentname() {

		return departmentname;
	}

	public void setDepartmentname(String departmentname) {

		this.departmentname = departmentname;
	}

	public Admin getAdmin() {

		return admin;
	}

	public void setAdmin(Admin admin) {

		this.admin = admin;
	}

}
