package com.elearning.bean.output.Subject;

import java.io.Serializable;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.entity.Video;

public class VideoBean extends BaseResult implements Serializable {
	
	private static final long serialVersionUID = -4380496680547229990L;

	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
}
