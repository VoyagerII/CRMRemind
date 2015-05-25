package com.elearning.bean.output.myHomeWork;

import java.io.Serializable;
import java.util.List;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.bean.output.myHomeWork.bean.AdminAnswerBean;
import com.elearning.bean.output.myHomeWork.bean.VideoBean;

/**
 * 我的作业bean
 * @author dingdapeng
 *
 */
public class SubjectVideHomeAnswer extends BaseResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2614931438696127757L;
	
	public SubjectVideHomeAnswer() {
		// TODO Auto-generated constructor stub
	}
	
	public SubjectVideHomeAnswer(List<VideoBean> videoBeanList, List<AdminAnswerBean> adminAnswerBeanList){
		this.setVideoBeanList(videoBeanList);
		this.setAdminAnswerBeanList(adminAnswerBeanList);
	}
	
	/**
     * totalpage (视频作业页数)
     * @author dingdapeng 2015-04-15
     */
	private int totalpage;
	
	/**
     * `videoBeanList`.videoBeanList (课程列表)
     * @author dingdapeng 2015-04-15
     */
    private List<VideoBean> videoBeanList;
    
    /**
     * `adminAnswerList`.adminAnswerList (答案列表)
     * @author dingdapeng 2015-04-15
     */
    private List<AdminAnswerBean> adminAnswerBeanList;

	public List<VideoBean> getVideoBeanList() {
		return videoBeanList;
	}

	public void setVideoBeanList(List<VideoBean> videoBeanList) {
		this.videoBeanList = videoBeanList;
	}

	public List<AdminAnswerBean> getAdminAnswerBeanList() {
		return adminAnswerBeanList;
	}

	public void setAdminAnswerBeanList(List<AdminAnswerBean> adminAnswerBeanList) {
		this.adminAnswerBeanList = adminAnswerBeanList;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
