package com.elearning.bean.output.subjectLearning;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.entity.AdminFavorite;
import com.elearning.entity.AdminStudy;
import com.elearning.entity.Subjects;

/**
 * @author Ilia
 * @version 创建时间：2015年2月2日 下午4:09:00
 */
public class SubjectLearningBean extends BaseResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6808179175829408209L;
	
	private Integer subjectId;//课程id
	private Integer adminId; //课程上传用户id
	private String subjectPicture ; //课程封面
	private String subjectName;//课程名称
	private String videoName;//课程更新
	private String adminName;//上传者名字
	private String lastTime;//更新时间
	private String videoNameLearning ;//最近学习进度
	private String lastTimeLearning ;//最近学习时间
	private Integer subjectScore; //课程评分
	private Integer subjectPlay;//课程播放次数
	private Integer isFavorite;//是否收藏
	

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
	public String getSubjectPicture() {
		return subjectPicture;
	}
	public void setSubjectPicture(String subjectPicture) {
		this.subjectPicture = subjectPicture;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastTimeLearning() {
		return lastTimeLearning;
	}
	public void setLastTimeLearning(String lastTimeLearning) {
		this.lastTimeLearning = lastTimeLearning;
	}
	public String getVideoNameLearning() {
		return videoNameLearning;
	}
	public void setVideoNameLearning(String videoNameLearning) {
		this.videoNameLearning = videoNameLearning;
	}
	
	public Integer getSubjectScore() {
		return subjectScore;
	}
	public void setSubjectScore(Integer subjectScore) {
		this.subjectScore = subjectScore;
	}
	public Integer getSubjectPlay() {
		return subjectPlay;
	}
	public void setSubjectPlay(Integer subjectPlay) {
		this.subjectPlay = subjectPlay;
	}
	public Integer getIsFavorite() {
		return isFavorite;
	}
	public void setIsFavorite(Integer isFavorite) {
		this.isFavorite = isFavorite;
	}
	

	
}
