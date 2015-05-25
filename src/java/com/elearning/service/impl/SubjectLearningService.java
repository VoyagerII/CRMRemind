package com.elearning.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elearning.bean.output.base.ResultCode;
import com.elearning.bean.output.subjectLearning.SubjectLearningBean;
import com.elearning.bean.output.subjectLearning.SubjectLearningListBean;
import com.elearning.bean.output.subjectLearning.VideoNoteBean;
import com.elearning.bean.output.subjectLearning.VideoNoteListBean;
import com.elearning.bean.output.videoLearning.VideoLearningBean;
import com.elearning.entity.Admin;
import com.elearning.entity.AdminAnswer;
import com.elearning.entity.AdminFavorite;
import com.elearning.entity.SubjectRate;
import com.elearning.entity.Subjects;
import com.elearning.entity.Video;
import com.elearning.entity.VideoHomework;
import com.elearning.entity.VideoNote;
import com.elearning.service.BaseService;
import com.elearning.web.util.PageInfo;
import com.elearning.web.util.PagingUtil;

public class SubjectLearningService extends BaseService {


	// 获取所有正在学习的课程
	public SubjectLearningListBean getAllSubjectByAdminIdAndSubjectFinish(Map<String,Object> paramMap){
		
		SubjectLearningListBean subjectLearningListBean = new SubjectLearningListBean();
		SubjectLearningBean subjectLearningBean = new SubjectLearningBean();
		Integer subjectId;
		
		int count = (Integer) this.baseDao.selectOne(
				"com.elearning.SubjectLearningExtMapper.getAllSubjectByAdminIdAndSubjectFinishCount", paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageindex")
				.toString()));
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pagesize")
				.toString()));
		pageInfo.setTotalCount(count);
		PagingUtil.getPaging(pageInfo, paramMap);
		
		List<SubjectLearningBean> subjectLearningBeanList = (List<SubjectLearningBean>) this.baseDao.selectList(
				"com.elearning.SubjectLearningExtMapper.getAllSubjectByAdminIdAndSubjectFinish", paramMap);
		
		subjectLearningListBean.setSubjectLearningListBean(subjectLearningBeanList);
		subjectLearningListBean.setTotalPages(Integer.parseInt(paramMap.get("totalPages").toString()));
		subjectLearningListBean.setPageno(Integer.parseInt(paramMap.get("pageindex").toString()));
		subjectLearningListBean.setSubjectLearningListBean(subjectLearningBeanList);
		subjectLearningListBean.setResultcode(ResultCode.SUCCESS);
		subjectLearningListBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		return subjectLearningListBean;
		
	
		
	}
	
	
	// 获取所有收藏的视频
	public SubjectLearningListBean getAllSubjectFavorite(Map<String,Object> paramMap){
		
		SubjectLearningListBean subjectLearningListBean = new SubjectLearningListBean();
		
		SubjectLearningBean subjectLearningBean = new SubjectLearningBean();
		Integer subjectId;
		int count = (Integer) this.baseDao.selectOne(
				"com.elearning.SubjectLearningExtMapper.getAllSubjectFavoriteCount", paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageindex")
				.toString()));
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pagesize")
				.toString()));
		
		pageInfo.setTotalCount(count);
		
		PagingUtil.getPaging(pageInfo, paramMap);

		List<SubjectLearningBean> subjectLearningBeanList = (List<SubjectLearningBean>) this.baseDao.selectList(
				"com.elearning.SubjectLearningExtMapper.selectUserFavorite", paramMap);
		
		subjectLearningListBean.setTotalPages(Integer.parseInt(paramMap.get("totalPages").toString()));
		subjectLearningListBean.setPageno(Integer.parseInt(paramMap.get("pageindex").toString()));
		subjectLearningListBean.setSubjectLearningListBean(subjectLearningBeanList);
		subjectLearningListBean.setResultcode(ResultCode.SUCCESS);
		subjectLearningListBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		return subjectLearningListBean;
	}

	// 课程视频评分  
	public int updateSubjectScore(SubjectRate subjectRate) {
		// TODO Auto-generated method stub
		if(this.baseDao.insert("com.elearning.SubjectRateMapper.insert", subjectRate)>0){
			
			Map<String,Object> dataMap =  (Map<String, Object>) this.baseDao.selectOne(
					"com.elearning.SubjectLearningExtMapper.getAllSubjectRateBySubjectId", subjectRate.getSubjectId());
			Subjects subject = new Subjects();
			subject.setSubjectId(subjectRate.getSubjectId());
			Double score= Double.parseDouble(dataMap.get("rateScore").toString())/Double.parseDouble(dataMap.get("countnum").toString());
			subject.setSubjectScore(Math.round(score*100)/100.0);
			return this.baseDao.update("com.elearning.SubjectMapper.updateByPrimaryKeySelective", subject);
		}else{
			return 0;
		}
	}

	//收藏课程  
	public int insertAdminFavorite(AdminFavorite AdminFavorite) {
		// TODO Auto-generated method stub
		return this.baseDao.insert("com.elearning.AdminFavoriteMapper.insert", AdminFavorite);
	}

	//取消收藏课程 
	public int deleteAdminFavorite(AdminFavorite adminFavorite) {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subjectId", adminFavorite.getSubjectId());
		paramMap.put("adminId", adminFavorite.getAdminId());
		return this.baseDao.delete("com.elearning.SubjectLearningExtMapper.deleteAdminFavorite", paramMap);
		 
	}

	//点击课程进入课程视频列表页面 
	public VideoLearningBean getAllVideosBySubjectId(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		VideoLearningBean videoLearningBean = new VideoLearningBean();
		
		//获得课程信息
		Subjects subject = (Subjects) this.baseDao.selectOne(
				"com.elearning.SubjectsMapper.selectByPrimaryKey", Integer.parseInt(paramMap.get("subjectId").toString()));
		videoLearningBean.setSubjects(subject);
		
		//获得最后学习的章节
		Video video = (Video) this.baseDao.selectOne(
				"com.elearning.SubjectLearningExtMapper.selectVideoLastLearning", paramMap);
		videoLearningBean.setVideo(video);
		
		//获得所有章节ID
		List<Video> videoIdList = (List<Video>) this.baseDao.selectList(
				"com.elearning.SubjectLearningExtMapper.getVideoIdListBySubjectId", paramMap);
		videoLearningBean.setVideoList(videoIdList);
		
		//是否有作业、作业是否完成(查询必填题)
		Map<String, Object> videoParamMap = new HashMap<String, Object>();
		videoParamMap.put("videoId", video.getVideoId());
		List<VideoHomework> videoHomeworkList = (List<VideoHomework>)this.baseDao.selectList(
				"com.elearning.SubjectLearningExtMapper.getVideoHomeworkByVideoId", videoParamMap);
		log.info("videoHomeworkList:"+videoHomeworkList);
		
		if(null == videoHomeworkList || videoHomeworkList.size() == 0){
			videoLearningBean.setIsFinishedHomework(true);
		}else{
			videoLearningBean.setIsFinishedHomework(true);//假设全做完
			for(VideoHomework videoHomework : videoHomeworkList){
				AdminAnswer adminAnswer = (AdminAnswer) this.baseDao.selectOne(
						"com.elearning.SubjectLearningExtMapper.selectAdminAnswerByHomeworkId", videoHomework.getHomeworkId());
				if(null == adminAnswer){
					videoLearningBean.setIsFinishedHomework(false); //有未做完的作业，修改值为false，否则就是做完
				}
			}
		}
		//笔记分页
		paramMap.put("videoId", video.getVideoId());
		VideoNoteListBean videoNoteListBean = this.getVideoNoteByVideoId(paramMap);
		videoLearningBean.setVideoNoteListBean(videoNoteListBean);
		
		return videoLearningBean;
	}
	
	// 笔记分页
	public VideoNoteListBean getVideoNoteByVideoId(Map<String,Object> paramMap){
		
		VideoNoteListBean videoNoteListBean = new VideoNoteListBean();
		List<VideoNoteBean> videoNoteBeanList = new ArrayList<VideoNoteBean>();
		VideoNoteBean videoNoteBean = new VideoNoteBean() ; 
		int count = (Integer) this.baseDao.selectOne(
				"com.elearning.SubjectLearningExtMapper.getVideoNoteByAdminIdAndVideoIdCount", paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageindex")
				.toString()));
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pagesize")
				.toString()));
		
		pageInfo.setTotalCount(count);
		
		PagingUtil.getPaging(pageInfo, paramMap);
		
		List<VideoNote> videoNoteList = (List<VideoNote>) this.baseDao.selectList(
				"com.elearning.SubjectLearningExtMapper.getVideoNoteByAdminIdAndVideoId", paramMap);

		for(VideoNote v : videoNoteList){
			videoNoteBean.setAdmin((Admin) this.baseDao.selectOne(
					"com.elearning.AdminMapper.selectByPrimaryKey", v.getAdminId()));
			videoNoteBean.setVideoNote(v);
			videoNoteBeanList.add(videoNoteBean);
		}
		
		videoNoteListBean.setVideoNoteList(videoNoteBeanList);
		videoNoteListBean.setPageno(Integer.parseInt(paramMap.get("pageindex").toString()));
		videoNoteListBean.setTotalPages(count);
		return videoNoteListBean;
	}
}
