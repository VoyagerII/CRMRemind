package com.elearning.bean.output.subjectLearning;

import java.io.Serializable;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.entity.Admin;
import com.elearning.entity.VideoNote;

/**
 * @author Ilia
 * @version 创建时间：2015年2月2日 下午4:09:00
 */
public class VideoNoteBean extends BaseResult implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3276019270270641829L;
	private VideoNote VideoNote;
	private Admin admin;
	
	public VideoNote getVideoNote() {
		return VideoNote;
	}
	public void setVideoNote(VideoNote videoNote) {
		VideoNote = videoNote;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
}
