package com.elearning.bean.output.login;

import java.io.Serializable;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.entity.Admin;

public class LoginBean extends BaseResult implements Serializable {

	private static final long serialVersionUID = 535340747830819508L;

	private Admin admin;

	public Admin getAdmin() {

		return admin;
	}

	public void setAdmin(Admin admin) {

		this.admin = admin;
	}

}
