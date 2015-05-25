package com.elearning.entity;

import java.io.Serializable;

public class SystemFunction implements Serializable {
    /**
     * system_function.function_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer functionId;

    /**
     * system_function.function_name (功能名称)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String functionName;

    /**
     * system_function.function_url (功能路径)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String functionUrl;

    /**
     * system_function.function_level
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer functionLevel;

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    public Integer getFunctionLevel() {
        return functionLevel;
    }

    public void setFunctionLevel(Integer functionLevel) {
        this.functionLevel = functionLevel;
    }
}