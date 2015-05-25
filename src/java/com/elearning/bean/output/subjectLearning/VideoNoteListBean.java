package com.elearning.bean.output.subjectLearning;

import java.io.Serializable;
import java.util.List;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.entity.VideoNote;

/**
 * @author Ilia
 * @version 创建时间：2015年2月2日 下午4:09:00
 */
public class VideoNoteListBean extends BaseResult implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4808674138521333865L;
	private List<VideoNoteBean> VideoNoteList ;
	private int pageno;
	private int totalPages;
	

	
	public List<VideoNoteBean> getVideoNoteList() {
		return VideoNoteList;
	}
	public void setVideoNoteList(List<VideoNoteBean> videoNoteList) {
		VideoNoteList = videoNoteList;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	} 
	
	
}
