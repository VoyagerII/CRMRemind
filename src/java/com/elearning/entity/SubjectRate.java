package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class SubjectRate implements Serializable {
    /**
     * subject_rate.rate_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer rateId;

    /**
     * subject_rate.subject_id (课程id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer subjectId;

    /**
     * subject_rate.admin_id (用户id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer adminId;

    /**
     * subject_rate.rate_score (评分分数)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer rateScore;

    /**
     * subject_rate.create_time (创建时间)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Date createTime;

    public Integer getRateId() {
        return rateId;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRateScore() {
        return rateScore;
    }

    public void setRateScore(Integer rateScore) {
        this.rateScore = rateScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}