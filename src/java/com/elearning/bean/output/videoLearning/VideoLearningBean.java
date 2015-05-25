package com.elearning.bean.output.videoLearning;

import java.io.Serializable;
import java.util.List;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.bean.output.subjectLearning.VideoNoteListBean;
import com.elearning.entity.Subjects;
import com.elearning.entity.Video;

/**
 * @author Ilia
 * @version 创建时间：2015年2月2日 下午4:09:00
 */
public class VideoLearningBean extends BaseResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6481509104323870870L;
	private Subjects subjects;
	private Video video;
	private List<Video> videoList ;
	private Boolean isFinishedHomework;
	private VideoNoteListBean videoNoteListBean;
	
	
	public Subjects getSubjects() {
		return subjects;
	}
	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	
	public List<Video> getVideoList() {
		return videoList;
	}
	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}
	public Boolean getIsFinishedHomework() {
		return isFinishedHomework;
	}
	public void setIsFinishedHomework(Boolean isFinishedHomework) {
		this.isFinishedHomework = isFinishedHomework;
	}
	public VideoNoteListBean getVideoNoteListBean() {
		return videoNoteListBean;
	}
	public void setVideoNoteListBean(VideoNoteListBean videoNoteListBean) {
		this.videoNoteListBean = videoNoteListBean;
	}
	
	
}
