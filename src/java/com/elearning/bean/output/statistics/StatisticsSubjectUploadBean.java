package com.elearning.bean.output.statistics;

/**
 * @author Ilia
 * @version 创建时间：2015年4月15日 下午3:17:05
 */
public class StatisticsSubjectUploadBean {

	private int total = 0 ;
	private int adminId ;
	private String adminName = "" ;
	private String adminPicture = "";
	private String departmentName = "";
	private String subjectId = "";
	private String subjectName = "";
	private String subjectPicture = "";
	private String disTime = "";
	public int getTotal() {
		return total;
	}
	public int getAdminId() {
		return adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public String getAdminPicture() {
		return adminPicture;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public String getSubjectPicture() {
		return subjectPicture;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setAdminPicture(String adminPicture) {
		this.adminPicture = adminPicture;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public void setSubjectPicture(String subjectPicture) {
		this.subjectPicture = subjectPicture;
	}
	public String getDisTime() {
		return disTime;
	}
	public void setDisTime(String disTime) {
		this.disTime = disTime;
	}


}
