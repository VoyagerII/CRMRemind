package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class VideoHomework implements Serializable {
    /**
     * video_homework.homework_id (主键ID)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer homeworkId;

    /**
     * video_homework.video_id (所属视频id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer videoId;

    /**
     * video_homework.homework_required (是否必填题(1：必填，0：非必填))
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Short homeworkRequired;

    /**
     * video_homework.create_time (创建时间)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Date createTime;

    /**
     * video_homework.homework_content (作业内容)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String homeworkContent;

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Short getHomeworkRequired() {
        return homeworkRequired;
    }

    public void setHomeworkRequired(Short homeworkRequired) {
        this.homeworkRequired = homeworkRequired;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }
}