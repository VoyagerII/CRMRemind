package com.elearning.service.impl;

import java.util.List;
import java.util.Map;

import com.elearning.bean.output.statistics.StatisticsSubjectUploadBean;
import com.elearning.service.BaseService;

/**
 * @author Ilia
 * @version 创建时间：2015年4月15日 下午2:41:50
 */
public class StatisticsService extends BaseService {
	
	/**
	 * 首页左边栏——统计学习(完成)课程数量
	 */
	public int getStudySubjectCount(Map<String, Object> keyMap) {
		return (Integer) this.baseDao.selectOne("com.elearning.statisticsActionMapper.getStudySubjectCount", keyMap);
	}
	/**
	 * 首页左边栏——统计收藏课程数量
	 */
	public int getFavoriteSubjectCount(Integer key) {
		return (Integer) this.baseDao.selectOne("com.elearning.statisticsActionMapper.getFavoriteSubjectCount", key);
	}
	/**
	 * 首页左边栏——统计我的作业数量
	 */
	public int getHomeworkCount(Integer key) {
		return (Integer) this.baseDao.selectOne("com.elearning.statisticsActionMapper.getHomeworkCount", key);
	}
	
	/**
	 * 首页左边栏——统计更新（完成）课程数量
	 */
	public int getUploadSubjectCount(Map<String, Object> keyMap) {
		return (Integer) this.baseDao.selectOne("com.elearning.statisticsActionMapper.getUploadSubjectCount", keyMap);
	}
	
	/**
	 * 部门课程统计
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStatisticsSubjectTime(Map<String, Object> keyMap) {
		return (List<Map<String, Object>>) this.baseDao.selectList("com.elearning.statisticsActionMapper.getStatisticsSubjectByTime", keyMap);
	}
	/**
	 * 部门课程统计
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStatisticsSubjectPlay(Map<String, Object> keyMap) {
		return (List<Map<String, Object>>) this.baseDao.selectList("com.elearning.statisticsActionMapper.getStatisticsSubjectByTime", keyMap);
	}
	

	/**
	 * 评分top3
	 */
	@SuppressWarnings("unchecked")
	public List<StatisticsSubjectUploadBean> getStatisticsTopRate(Map<String, Object> keyMap) {
		return ( List<StatisticsSubjectUploadBean>) this.baseDao.selectList("com.elearning.statisticsActionMapper.getStatisticsTopRate", keyMap);
	}
	
	/**
	 * 热门top3
	 */
	@SuppressWarnings("unchecked")
	public List<StatisticsSubjectUploadBean> getStatisticsTopSubject(Map<String, Object> keyMap) {
		return ( List<StatisticsSubjectUploadBean>) this.baseDao.selectList("com.elearning.statisticsActionMapper.getStatisticsTopSubject", keyMap);
	}
	
	/**
	 * 发布人top3
	 */
	@SuppressWarnings("unchecked")
	public List<StatisticsSubjectUploadBean> getStatisticsTopAdmin(Map<String, Object> keyMap) {
		return ( List<StatisticsSubjectUploadBean>) this.baseDao.selectList("com.elearning.statisticsActionMapper.getStatisticsTopAdmin", keyMap);
	}
	
	/**
	 * <!-- 图表 新开课程，更新课程，完结课程 -->
	 */
	@SuppressWarnings("unchecked")
	public List<StatisticsSubjectUploadBean> getStatisticsSubjects(Map<String, Object> keyMap) {
		return ( List<StatisticsSubjectUploadBean>) this.baseDao.selectList("com.elearning.statisticsActionMapper.getStatisticsSubjects", keyMap);
	}
	
	/**
	 * 浏览量统计
	 */
	@SuppressWarnings("unchecked")
	public List<StatisticsSubjectUploadBean> getStatisticsPlay(Map<String, Object> keyMap) {
		return ( List<StatisticsSubjectUploadBean>) this.baseDao.selectList("com.elearning.statisticsActionMapper.getStatisticsPlay", keyMap);
	}
	
	
}
