package com.elearning.bean.output.myHomeWork.bean;

import java.io.Serializable;

/**
 * 
 * @author dingdapeng
 *
 */
public class AdminAnswerBean implements Serializable {
	
	 /**
     * `admin`.admin_name(上传者信息)
     * @ibatorgenerated 2015-04-16 15:22:48
     */
    private String adminName;
    
    /**
     * `admin`.admin_avatar (上传者头像)
     * @ibatorgenerated 2015-04-16 15:22:48
     */
    private String adminAvatar;
    
	/**
     * video.video_id (视频id)
     * @ibatorgenerated 2015-04-15 11:22:55
     */
    private Integer videoId;

    /**
     * video.video_name (视频名称)
     * @ibatorgenerated 2015-04-15 11:22:55
     */
    private String videoName;
    
    /**
     * video.video_desc (视频简介)
     * @ibatorgenerated 2015-04-16 15:22:48
     */
    private String videoDesc;
    
    /**
     * video_homework.homework_id (作业ID)
     * @ibatorgenerated 2015-04-16 15:22:48
     */
    private Integer homeworkId;
    
    /**
     * video_homework.homework_required (是否必填题)
     * @ibatorgenerated 2015-04-16 15:22:49
     */
    private Short homeworkRequired;
    
    /**
     * video_homework.homework_content (作业内容)
     * @ibatorgenerated 2015-04-16 15:22:49
     */
    private String homeworkContent;
    
    /**
     * admin_answer.answer_content (答案)
     * @ibatorgenerated 2015-04-16 15:22:48
     */
    private String answerContent;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminAvatar() {
		return adminAvatar;
	}

	public void setAdminAvatar(String adminAvatar) {
		this.adminAvatar = adminAvatar;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoDesc() {
		return videoDesc;
	}

	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}

	public Integer getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}

	public String getHomeworkContent() {
		return homeworkContent;
	}

	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Short getHomeworkRequired() {
		return homeworkRequired;
	}

	public void setHomeworkRequired(Short homeworkRequired) {
		this.homeworkRequired = homeworkRequired;
	}
    
}