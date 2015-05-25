package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable {
    /**
     * video.video_id (视频id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer videoId;

    /**
     * video.subject_id (所属课程id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer subjectId;

    /**
     * video.video_name (视频名称)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String videoName;

    /**
     * video.video_admin (视频上传者)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer videoAdmin;

    /**
     * video.department_id (所属部门)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer departmentId;

    /**
     * video.video_public (视频是否公开（1：公开，0：非公开。）)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Short videoPublic;

    /**
     * video.video_chapter (视频章节)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer videoChapter;

    /**
     * video.video_resource (视频附加资料地址)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String videoResource;

    /**
     * video.create_time (创建时间)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Date createTime;

    /**
     * video.video_url (视频地址)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String videoUrl;

    /**
     * video.video_status (视频是否有效（1：有效，0：无效）)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Short videoStatus;

    /**
     * video.video_new (是否是新上传视频)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Short videoNew;

    /**
     * video.video_desc (视频简介)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String videoDesc;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Integer getVideoAdmin() {
        return videoAdmin;
    }

    public void setVideoAdmin(Integer videoAdmin) {
        this.videoAdmin = videoAdmin;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Short getVideoPublic() {
        return videoPublic;
    }

    public void setVideoPublic(Short videoPublic) {
        this.videoPublic = videoPublic;
    }

    public Integer getVideoChapter() {
        return videoChapter;
    }

    public void setVideoChapter(Integer videoChapter) {
        this.videoChapter = videoChapter;
    }

    public String getVideoResource() {
        return videoResource;
    }

    public void setVideoResource(String videoResource) {
        this.videoResource = videoResource;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Short getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(Short videoStatus) {
        this.videoStatus = videoStatus;
    }

    public Short getVideoNew() {
        return videoNew;
    }

    public void setVideoNew(Short videoNew) {
        this.videoNew = videoNew;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }
}