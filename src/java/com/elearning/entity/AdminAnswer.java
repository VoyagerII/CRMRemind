package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class AdminAnswer implements Serializable {
    /**
     * admin_answer.answer_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer answerId;

    /**
     * admin_answer.video_id (视频id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer videoId;

    /**
     * admin_answer.homework_id (所属作业id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer homeworkId;

    /**
     * admin_answer.admin_id (用户id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer adminId;

    /**
     * admin_answer.create_time (提交时间)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Date createTime;

    /**
     * admin_answer.answer_content (答案)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String answerContent;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}