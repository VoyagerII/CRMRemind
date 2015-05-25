package com.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.elearning.bean.output.subjectLearning.SubjectLearningBean;
import com.elearning.bean.output.subjectLearning.VideoNoteListBean;
import com.elearning.bean.output.videoLearning.VideoLearningBean;
import com.elearning.entity.AdminAnswer;
import com.elearning.entity.AdminStudy;
import com.elearning.entity.Subjects;
import com.elearning.entity.Video;
import com.elearning.entity.VideoHomework;
import com.elearning.entity.VideoNote;
import com.elearning.service.BaseService;
import com.elearning.web.util.PageInfo;
import com.elearning.web.util.PagingUtil;

public class VideoLearningService extends BaseService {


	//点击课程进入课程视频列表页面 
	public VideoLearningBean getVideosByVideoId(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		VideoLearningBean videoLearningBean = new VideoLearningBean();
		
		//获得选中的章节
		Video video = (Video) this.baseDao.selectOne(
				"com.elearning.SubjectLearningExtMapper.selectVideoLastLearning", paramMap);
		videoLearningBean.setVideo(video);
		//获得课程信息
		Subjects subject = (Subjects) this.baseDao.selectOne(
				"com.elearning.SubjectMapper.selectByPrimaryKey", video.getSubjectId());
		videoLearningBean.setSubjects(subject);
		
		//获得所有章节ID
		List<Video> videoList = (List<Video>) this.baseDao.selectList(
				"com.elearning.SubjectLearningExtMapper.getVideoIdListBySubjectId", video.getSubjectId());
		videoLearningBean.setVideoList(videoList);
		//是否有作业、作业是否完成(查询必填题)
		List<VideoHomework> videoHomeworkList = (List<VideoHomework>)this.baseDao.selectList(
				"com.elearning.SubjectLearningExtMapper.getVideoHomeworkByVideoId", video.getVideoId());
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
		SubjectLearningService subjectLearningService= new SubjectLearningService();
		VideoNoteListBean videoNoteListBean = subjectLearningService.getVideoNoteByVideoId(paramMap);
		videoLearningBean.setVideoNoteListBean(videoNoteListBean);
		
		return videoLearningBean;
	}
	
	
	// 获取所有正在学习的课程
	public List<SubjectLearningBean> getSubjectByAdminId(Map<String,Object> paramMap){
		
		
		SubjectLearningBean subjectLearningBean = new SubjectLearningBean();
		Integer subjectId;
		int count = (Integer) this.baseDao.selectOne(
				"com.elearning.VideoLearningExtMapper.getSubjectByAdminIdCount", paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageindex")
				.toString()) + 1);
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pagesize")
				.toString()));
		
		pageInfo.setTotalCount(count);
		
		PagingUtil.getPaging(pageInfo, paramMap);
		
		List<SubjectLearningBean> subjectLearningBeanList = (List<SubjectLearningBean>) this.baseDao.selectList(
				"com.elearning.VideoLearningExtMapper.getSubjectByAdminId", paramMap);
		
		return subjectLearningBeanList;
	}
	
	
	//记一笔  
	public int insertNewVideoNote(VideoNote videoNote) {
		// TODO Auto-generated method stub
		return this.baseDao.insert("com.elearning.VideoNoteMapper.insert", videoNote);
	}
		
}
