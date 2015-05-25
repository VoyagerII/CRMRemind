package com.elearning.bean.output.Subject;

import java.io.Serializable;
import java.util.List;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.entity.Subjects;
import com.elearning.entity.Video;

public class UpdateSubjectPageBean  extends BaseResult implements Serializable {
	
	private static final long serialVersionUID = 40789152935492295L;
	
	private Subjects subjects;
	private List<Video> videoList;

	public List<Video> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

	public Subjects getSubjects() {
		return subjects;
	}

	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}
	
}
