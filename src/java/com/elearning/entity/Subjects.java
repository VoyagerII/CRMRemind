package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class Subjects implements Serializable {
    /**
     * subjects.subject_id (主键ID)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer subjectId;

    /**
     * subjects.admin_id (用户id)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer adminId;

    /**
     * subjects.subject_name (课程名称)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String subjectName;

    /**
     * subjects.subject_picture (课程封面)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String subjectPicture;

    /**
     * subjects.subject_finsh (是否已完结（1：已完结，0:未完结）)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Short subjectFinsh;

    /**
     * subjects.last_video_id (最后更新章节id)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer lastVideoId;

    /**
     * subjects.subject_score (课程评分)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Double subjectScore;

    /**
     * subjects.subject_play (课程播放次数)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer subjectPlay;

    /**
     * subjects.subject_status (课程是否有效（1：有效，0：无效）)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Short subjectStatus;

    /**
     * subjects.last_time (最后更新时间)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Date lastTime;

    /**
     * subjects.create_time (创建时间)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Date createTime;

    /**
     * subjects.subject_public (课程是否共享（1：共享，0：非共享）)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Short subjectPublic;

    /**
     * subjects.subject_desc (课程简介)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private String subjectDesc;

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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectPicture() {
        return subjectPicture;
    }

    public void setSubjectPicture(String subjectPicture) {
        this.subjectPicture = subjectPicture;
    }

    public Short getSubjectFinsh() {
        return subjectFinsh;
    }

    public void setSubjectFinsh(Short subjectFinsh) {
        this.subjectFinsh = subjectFinsh;
    }

    public Integer getLastVideoId() {
        return lastVideoId;
    }

    public void setLastVideoId(Integer lastVideoId) {
        this.lastVideoId = lastVideoId;
    }

    public Double getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(Double subjectScore) {
        this.subjectScore = subjectScore;
    }

    public Integer getSubjectPlay() {
        return subjectPlay;
    }

    public void setSubjectPlay(Integer subjectPlay) {
        this.subjectPlay = subjectPlay;
    }

    public Short getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(Short subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getSubjectPublic() {
        return subjectPublic;
    }

    public void setSubjectPublic(Short subjectPublic) {
        this.subjectPublic = subjectPublic;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }
}