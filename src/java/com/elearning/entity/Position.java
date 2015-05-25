package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class Position implements Serializable {
    /**
     * `position`.position_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer positionId;

    /**
     * `position`.position_name (职位名称)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String positionName;

    /**
     * `position`.create_time (创建时间)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Date createTime;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}