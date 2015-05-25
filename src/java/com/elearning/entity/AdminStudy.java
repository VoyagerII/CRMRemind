package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class AdminStudy implements Serializable {
    /**
     * admin_study.study_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer studyId;

    /**
     * admin_study.subject_id (课程id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer subjectId;

    /**
     * admin_study.video_id (视频id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer videoId;

    /**
     * admin_study.admin_id (用户id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer adminId;

    /**
     * admin_study.last_time (上次学习时间)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Date lastTime;

    /**
     * admin_study.create_time (创建时间)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Date createTime;

    /**
     * admin_study.homework_finish (是否完成作业（1：已完成，0：未完成。）（没有作业的视频在保存时视为已完成。）)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Short homeworkFinish;

    public Integer getStudyId() {
        return studyId;
    }

    public void setStudyId(Integer studyId) {
        this.studyId = studyId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    public Short getHomeworkFinish() {
        return homeworkFinish;
    }

    public void setHomeworkFinish(Short homeworkFinish) {
        this.homeworkFinish = homeworkFinish;
    }
}