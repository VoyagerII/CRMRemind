package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
    /**
     * `admin`.admin_id (用户id)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer adminId;

    /**
     * `admin`.admin_login (用户登录名)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String adminLogin;

    /**
     * `admin`.admin_pass (登录密码)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String adminPass;

    /**
     * `admin`.admin_name
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String adminName;

    /**
     * `admin`.admin_email (用户邮箱)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String adminEmail;

    /**
     * `admin`.admin_avatar (用户头像)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String adminAvatar;

    /**
     * `admin`.admin_mobile (用户手机号)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String adminMobile;

    /**
     * `admin`.department_id (用户所属部门id)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer departmentId;

    /**
     * `admin`.admin_position (用户职位)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer adminPosition;

    /**
     * `admin`.creat_time (注册时间)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Date creatTime;

    /**
     * `admin`.create_admin (注册管理员)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer createAdmin;

    /**
     * `admin`.admin_status (用户状态（0：禁用，1：正常）)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Short adminStatus;

    /**
     * `admin`.admin_role (用户角色)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer adminRole;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminAvatar() {
        return adminAvatar;
    }

    public void setAdminAvatar(String adminAvatar) {
        this.adminAvatar = adminAvatar;
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getAdminPosition() {
        return adminPosition;
    }

    public void setAdminPosition(Integer adminPosition) {
        this.adminPosition = adminPosition;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getCreateAdmin() {
        return createAdmin;
    }

    public void setCreateAdmin(Integer createAdmin) {
        this.createAdmin = createAdmin;
    }

    public Short getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Short adminStatus) {
        this.adminStatus = adminStatus;
    }

    public Integer getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(Integer adminRole) {
        this.adminRole = adminRole;
    }
}