package com.elearning.bean.output.subjectLearning;

import java.io.Serializable;
import java.util.List;

import com.elearning.bean.output.base.BaseResult;

/**
 * @author Ilia
 * @version 创建时间：2015年2月2日 下午4:09:00
 */
public class SubjectLearningListBean extends BaseResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1645513200300284963L;
	
	private List<SubjectLearningBean> SubjectLearningListBean ;
	private int pageno;
	private int totalPages;
	
	public List<SubjectLearningBean> getSubjectLearningListBean() {
		return SubjectLearningListBean;
	}
	public void setSubjectLearningListBean(
			List<SubjectLearningBean> subjectLearningListBean) {
		SubjectLearningListBean = subjectLearningListBean;
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
