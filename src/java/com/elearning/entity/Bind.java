package com.elearning.entity;

import java.io.Serializable;

public class Bind implements Serializable {
    /**
     * bind.bind_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer bindId;

    /**
     * bind.role_id (角色id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer roleId;

    /**
     * bind.function_id (功能id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer functionId;

    public Integer getBindId() {
        return bindId;
    }

    public void setBindId(Integer bindId) {
        this.bindId = bindId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }
}