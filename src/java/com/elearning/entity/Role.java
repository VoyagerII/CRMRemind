package com.elearning.entity;

import java.io.Serializable;

public class Role implements Serializable {
    /**
     * `role`.role_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer roleId;

    /**
     * `role`.role_name (角色名称)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}