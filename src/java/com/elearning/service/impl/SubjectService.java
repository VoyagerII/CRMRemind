package com.elearning.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elearning.bean.output.Subject.SubjectListBean;
import com.elearning.bean.output.Subject.UpdateSubjectPageBean;
import com.elearning.bean.output.Subject.VideoBean;
import com.elearning.entity.Subjects;
import com.elearning.entity.Video;
import com.elearning.entity.VideoHomework;
import com.elearning.service.BaseService;
import com.elearning.web.util.PageInfo;
import com.elearning.web.util.PagingUtil;

/**
 * 课程科目模块Service
 * @author xinwenfeng
 *
 */
public class SubjectService extends BaseService {
	

	/**
	 * 根据adminId获得课程科目列表
	 * @return
	 */
	public SubjectListBean getSubject(Map<String, Object> paramMap) {
		int count = (Integer) this.baseDao.selectOne(
				"sqlMapper.customSqlMapper.SubjectSqlMapper.xml.countGetSubject", paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageIndex")
				.toString()));
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pageSize")
				.toString()));
		
		pageInfo.setTotalCount(count);
		
		PagingUtil.getPaging(pageInfo, paramMap);

		List<Subjects> list = (List<Subjects>) this.baseDao.selectList(
				"sqlMapper.customSqlMapper.SubjectSqlMapper.xml.getSubject", paramMap);
		SubjectListBean subjectListBean =new SubjectListBean();
		subjectListBean.setTotal(count);
		subjectListBean.setTotalPages((int)((count+5)/6));
		subjectListBean.setSubjectsBeanList(list);
		return subjectListBean;
	}
	
	/**
	 * 新增课程科目、视频
	 * @param subject
	 * @param video
	 * @return
	 */
	public int addSubject(Subjects subject, Video video){
		int i = this.baseDao.insert("com.elearning.SubjectsMapper.insertSelective", subject);
		Integer subjectId = subject.getSubjectId();
		video.setSubjectId(subjectId);
		i += this.baseDao.insert("com.elearning.VideoMapper.insertSelective",video);
		return i;
	}
	
	/**
	 * 根据subjectId添加视频
	 * @param video
	 * @return
	 */
	public int addVideoBySubjectId(Video video){
		int i = this.baseDao.insert("com.elearning.VideoMapper.insertSelective", video);
		return i;
	}

	/**
	 * 增加作业
	 * @param videoHomework
	 * @return
	 */
	public int addHomeword(VideoHomework videoHomework) {
		int i = this.baseDao.insert("com.elearning.VideoHomeworkMapper.insertSelective", videoHomework);
		return i;
	}
	
	/**
	 * 根据subjectId获得更新发布页面的数据
	 * @param subjectId
	 * @return
	 */
	public UpdateSubjectPageBean getUpdateSubjectPageBean(Integer subjectId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subjectId", subjectId);
		Subjects subject = (Subjects) this.baseDao.selectOne("com.elearning.SubjectsMapper.selectByPrimaryKey", subjectId);
		List<Video> list = (List<Video>) this.baseDao.selectList(
				"sqlMapper.customSqlMapper.SubjectSqlMapper.xml.getVideosBySubjectId", param);
		UpdateSubjectPageBean updateSubjectPageBean =new UpdateSubjectPageBean();
		updateSubjectPageBean.setSubjects(subject);
		updateSubjectPageBean.setVideoList(list);
		return updateSubjectPageBean;
	}
	
	/**
	 * 根据videoId获得video
	 * @param videoId
	 * @return
	 */
	public VideoBean getVideoByVideoId(Integer videoId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("videoId",videoId);
		VideoBean videoBean = new VideoBean();
		Video video = (Video) this.baseDao.selectOne("com.elearning.VideoMapper.selectByPrimaryKey",param);
		videoBean.setVideo(video);
		return videoBean;
	}
	
	/**
	 * 根据amdinId获得已经完结的课程列表
	 * @param adminId
	 * @return
	 */
	public SubjectListBean getFinishedSubject(Map<String, Object> paramMap) {
		int count = (Integer) this.baseDao.selectOne(
				"sqlMapper.customSqlMapper.SubjectSqlMapper.xml.countGetFinishedSubject", paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageIndex")
				.toString()));
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pageSize")
				.toString()));
		
		pageInfo.setTotalCount(count);
		
		PagingUtil.getPaging(pageInfo, paramMap);
		
		List<Subjects> list = (List<Subjects>) this.baseDao.selectList(
				"sqlMapper.customSqlMapper.SubjectSqlMapper.xml.getFinishedSubject", paramMap);
		SubjectListBean subjectListBean =new SubjectListBean();
		subjectListBean.setTotal(count);
		subjectListBean.setTotalPages((int)((count+5)/6));
		subjectListBean.setSubjectsBeanList(list);
		return subjectListBean;
	}
	
	
	
	
	
	
	

}
