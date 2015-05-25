package com.elearning.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.elearning.bean.UserBean;
import com.elearning.bean.output.base.BaseResult;
import com.elearning.bean.output.base.ResultCode;
import com.elearning.bean.output.subjectLearning.SubjectLearningBean;
import com.elearning.bean.output.subjectLearning.VideoNoteListBean;
import com.elearning.bean.output.videoLearning.VideoLearningBean;
import com.elearning.entity.Admin;
import com.elearning.entity.VideoNote;
import com.elearning.service.impl.VideoLearningService;
import com.elearning.web.util.CommonServiceUtil;
import com.elearning.web.util.CommonUtil;


public class VideoLearningAction extends MultiActionController {

	private static final Log log = org.apache.commons.logging.LogFactory.getLog(VideoLearningAction.class);

	private VideoLearningService videoLearningService;
	
	public VideoLearningService getVideoLearningService() {
		return videoLearningService;
	}

	public void setVideoLearningService(VideoLearningService videoLearningService) {
		this.videoLearningService = videoLearningService;
	}

	//点击章节进入视频详情
	public ModelAndView getVideosByVideoId(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {
		
		UserBean userBean = CommonUtil.getUserInfo(request);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("videoId", request.getAttribute("videoId"));
		
		VideoLearningBean videoLearningBean = this.videoLearningService.getVideosByVideoId(paramMap);
		
		if(null!=videoLearningBean){
			videoLearningBean.setResultcode(ResultCode.SUCCESS);
			videoLearningBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		}else{
			videoLearningBean.setResultcode(ResultCode.NORMALERROR);
			videoLearningBean.setResultmsg(ResultCode.getResultMsg(ResultCode.NORMALERROR));
		}
		
		CommonServiceUtil.beanToJsonWrite(videoLearningBean, response);
		return null;
	}
	
	/**
	 * 根据adminId查看所有课程
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 */
	public ModelAndView getSubjectByAdminId(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {

		Map<String,Object> paramMap = new HashMap<String, Object>();
		int adminId = ServletRequestUtils.getIntParameter(request, "adminId", -1);
		List<SubjectLearningBean> subjectLearningList ;
		paramMap.put("adminId",adminId);
		int pageIndex = ServletRequestUtils.getIntParameter(request, "pageIndex", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize",21);
		paramMap.put("pageindex", pageIndex);
		paramMap.put("pagesize", pageSize);
		if(adminId != -1){
			subjectLearningList = this.videoLearningService.getSubjectByAdminId(paramMap);
		}else{
			subjectLearningList = null;
		}
	
		VideoNoteListBean subjectLearningListBean = new VideoNoteListBean();
		subjectLearningListBean.setResultcode(ResultCode.SUCCESS);
		subjectLearningListBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		CommonServiceUtil.beanToJsonWrite(subjectLearningListBean, response);
		return null;
	}
	
	
	//<!-- 记一笔-->
	public ModelAndView insertNewVideoNote(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {

		UserBean userBean = CommonUtil.getUserInfo(request);
		BaseResult baseResult = new BaseResult();
		VideoNote  videoNote = new VideoNote();
		
		String noteContent = ServletRequestUtils.getStringParameter(request, "noteContent", "");
		int videoId = ServletRequestUtils.getIntParameter(request, "videoId", -1);
		int notePublic = ServletRequestUtils.getIntParameter(request, "notePublic", -1);
		
		videoNote.setAdminId(userBean.getAdmin().getAdminId());
		videoNote.setCreateTime(new Date());
		videoNote.setNoteContent(noteContent);
		videoNote.setVideoId(videoId);
		videoNote.setNotePublic((short)notePublic);
		
		int insertInt = this.videoLearningService.insertNewVideoNote(videoNote);
		
		if(insertInt == 0){
			baseResult.setResultcode(ResultCode.NORMALERROR);
			baseResult.setResultmsg(ResultCode.getResultMsg(ResultCode.NORMALERROR));
		}else{
			baseResult.setResultcode(ResultCode.SUCCESS);
			baseResult.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		}
		CommonServiceUtil.beanToJsonWrite(baseResult, response);
		return null;
	}
	
}
