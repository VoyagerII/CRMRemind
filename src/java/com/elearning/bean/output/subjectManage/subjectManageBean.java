package com.elearning.bean.output.subjectManage;

import java.io.Serializable;
import java.util.Date;

import com.elearning.bean.output.base.BaseResult;

public class subjectManageBean extends BaseResult implements Serializable {

	private static final long serialVersionUID = -6139204148798452588L;

	private int subjectId;
	private Date createTime;
	private String subjectName;
	private String departmentName;
	private String adminName;
	private int subjectStatus;
	
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getSubjectStatus() {
		return subjectStatus;
	}
	public void setSubjectStatus(int subjectStatus) {
		this.subjectStatus = subjectStatus;
	}
	
}
