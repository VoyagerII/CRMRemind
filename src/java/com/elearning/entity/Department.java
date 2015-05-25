package com.elearning.entity;

import java.io.Serializable;

public class Department implements Serializable {
    /**
     * department.department_id (部门id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer departmentId;

    /**
     * department.department_name (部门名称)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String departmentName;

    /**
     * department.department_status (是否开启权限（1：开启，0：关闭）)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Short departmentStatus;

    /**
     * department.department_father (父部门id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer departmentFather;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Short getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(Short departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    public Integer getDepartmentFather() {
        return departmentFather;
    }

    public void setDepartmentFather(Integer departmentFather) {
        this.departmentFather = departmentFather;
    }
}