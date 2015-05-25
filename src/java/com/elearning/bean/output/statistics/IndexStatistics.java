package com.elearning.bean.output.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.elearning.bean.output.base.BaseResult;

/**
 * @author Ilia
 * @version 创建时间：2015年4月16日 下午1:53:56
 */
public class IndexStatistics extends BaseResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1263831514222835966L;
	private List<StatisticsSubjectUploadBean> statisticsTopRate = new ArrayList<StatisticsSubjectUploadBean>();
	private List<StatisticsSubjectUploadBean> statisticsTopSubject = new ArrayList<StatisticsSubjectUploadBean>();
	private List<StatisticsSubjectUploadBean> statisticsTopAdmin = new ArrayList<StatisticsSubjectUploadBean>();
	
	private List<StatisticsSubjectUploadBean> statisticsSubjects = new ArrayList<StatisticsSubjectUploadBean>();
	private List<StatisticsSubjectUploadBean> statisticsPlay = new ArrayList<StatisticsSubjectUploadBean>();
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<StatisticsSubjectUploadBean> getStatisticsTopRate() {
		return statisticsTopRate;
	}
	public List<StatisticsSubjectUploadBean> getStatisticsTopSubject() {
		return statisticsTopSubject;
	}
	public List<StatisticsSubjectUploadBean> getStatisticsTopAdmin() {
		return statisticsTopAdmin;
	}
	public void setStatisticsTopRate(List<StatisticsSubjectUploadBean> statisticsTopRate) {
		this.statisticsTopRate = statisticsTopRate;
	}
	public void setStatisticsTopSubject(List<StatisticsSubjectUploadBean> statisticsTopSubject) {
		this.statisticsTopSubject = statisticsTopSubject;
	}
	public void setStatisticsTopAdmin(List<StatisticsSubjectUploadBean> statisticsTopAdmin) {
		this.statisticsTopAdmin = statisticsTopAdmin;
	}
	public List<StatisticsSubjectUploadBean> getStatisticsSubjects() {
		return statisticsSubjects;
	}
	public List<StatisticsSubjectUploadBean> getStatisticsPlay() {
		return statisticsPlay;
	}
	public void setStatisticsSubjects(List<StatisticsSubjectUploadBean> statisticsSubjects) {
		this.statisticsSubjects = statisticsSubjects;
	}
	public void setStatisticsPlay(List<StatisticsSubjectUploadBean> statisticsPlay) {
		this.statisticsPlay = statisticsPlay;
	}
	
}