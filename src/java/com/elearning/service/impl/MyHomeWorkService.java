package com.elearning.service.impl;

import java.util.List;
import java.util.Map;

import com.elearning.bean.output.myHomeWork.bean.AdminAnswerBean;
import com.elearning.bean.output.myHomeWork.bean.VideoBean;
import com.elearning.entity.AdminAnswer;
import com.elearning.entity.AdminStudy;
import com.elearning.entity.Video;
import com.elearning.entity.VideoHomework;
import com.elearning.service.BaseService;
import com.elearning.web.util.PageInfo;
import com.elearning.web.util.PagingUtil;

/**
 * @author dingdapeng
 */
public class MyHomeWorkService extends BaseService {
	
	/**
	 * 查询课程列表
	 * @param paramMap
	 * @return
	 */  
	@SuppressWarnings("unchecked")
	public List<VideoBean> getSubjectList(Map<String, Object> paramMap) {
		Integer count = (Integer) this.baseDao.selectOne(
						"sqlMapper.customSqlMapper.VideoHomeWorkExtMapper.xml.getSubjectListCount",paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageIndex")
				.toString()) + 1);
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pageSize")
				.toString()));
		pageInfo.setTotalCount(count);
		PagingUtil.getPaging(pageInfo, paramMap);
		return (List<VideoBean>) this.baseDao.selectList(
						"sqlMapper.customSqlMapper.VideoHomeWorkExtMapper.xml.getSubjectList",paramMap);
	}
	

	
	/**
	 * 查询答案By视频ID
	 * 通过ID去查主表'video_homework'的ID
	 * 关联answer表得到每一份作业
	 * @param paramMap
	 * @return
	 */  
	@SuppressWarnings("unchecked")
	public List<AdminAnswerBean> getAnswerByVideoId(Map<String, Object> paramMap) {
		return (List<AdminAnswerBean>) this.baseDao.selectList(
						"sqlMapper.customSqlMapper.VideoHomeWorkExtMapper.xml.getAnswerByVideoId",paramMap);
	}
	
	/**
	 * ··该视频的作业··很多道题
	 * 
	 * @param paramMap
	 * @return
	 */  
	@SuppressWarnings("unchecked")
	public List<AdminAnswerBean> getQuestionByVideoId(Map<String, Object> paramMap) {
		return (List<AdminAnswerBean>) this.baseDao.selectList(
						"sqlMapper.customSqlMapper.VideoHomeWorkExtMapper.xml.getQuestionByVideoId",paramMap);
	}
	
	/**
	 * 做作业
	 * @param paramMap
	 * @return
	 */  
	public int insertAnswerByVideoIdHomeworkId(AdminAnswer adminAnswer, Map<String, Object> paramMap) {
		int i = this.baseDao.insert("com.elearning.AdminAnswerMapper.insertSelective", adminAnswer);
		i = i + (int)this.baseDao.update("sqlMapper.customSqlMapper.VideoHomeWorkExtMapper.xml.updateAdminStudyByVideoIdAdminId", paramMap);
		return i;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 查询视频列表By课程ID
	 * @param loginBean
	 * @return
	 */  
	@SuppressWarnings("unchecked")
	public List<Video> getVideoListBySubjectId(Map<String, Object> paramMap) {
		Integer count = (Integer) this.baseDao.selectOne(
						"sqlMapper.customSqlMapper.VideoHomeWorkExtMapper.xml.getVideoListBySubjectIdCount",paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageIndex")
				.toString()) + 1);
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pageSize")
				.toString()));
		pageInfo.setTotalCount(count);
		PagingUtil.getPaging(pageInfo, paramMap);
		return (List<Video>) this.baseDao.selectList(
						"sqlMapper.customSqlMapper.VideoHomeWorkExtMapper.xml.getVideoListBySubjectId",paramMap);
	}
	
	/**
	 * 查询作业列表By视频ID
	 * @param loginBean
	 * @return
	 */  
	@SuppressWarnings("unchecked")
	public List<VideoHomework> getHomeWorkListByVideoId(Map<String, Object> paramMap) {
		Integer count = (Integer) this.baseDao.selectOne(
						"sqlMapper.customSqlMapper.VideoHomeWorkExtMapper.xml.getHomeWorkListByVideoIdCount",paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageIndex")
				.toString()) + 1);
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pageSize")
				.toString()));
		pageInfo.setTotalCount(count);
		PagingUtil.getPaging(pageInfo, paramMap);
		return (List<VideoHomework>) this.baseDao.selectList(
						"sqlMapper.customSqlMapper.VideoHomeWorkExtMapper.xml.getHomeWorkListByVideoId",paramMap);
	}
}
