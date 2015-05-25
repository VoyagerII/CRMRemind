package com.elearning.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
import com.elearning.bean.output.subjectLearning.SubjectLearningListBean;
import com.elearning.bean.output.subjectLearning.VideoNoteListBean;
import com.elearning.bean.output.videoLearning.VideoLearningBean;
import com.elearning.entity.Admin;
import com.elearning.entity.AdminFavorite;
import com.elearning.entity.SubjectRate;
import com.elearning.service.impl.SubjectLearningService;
import com.elearning.web.util.CommonServiceUtil;
import com.elearning.web.util.CommonUtil;


public class SubjectLearningAction extends MultiActionController {

	private static final Log log = org.apache.commons.logging.LogFactory.getLog(SubjectLearningAction.class);

	private SubjectLearningService subjectLearningService;
	
	
	public SubjectLearningService getSubjectLearningService() {
		return subjectLearningService;
	}

	public void setSubjectLearningService(
			SubjectLearningService subjectLearningService) {
		this.subjectLearningService = subjectLearningService;
	}

	/**
	 * 获取所有正在学习的课程 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 */
	public ModelAndView getAllSubjectLearning(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {

		
		int pageno = ServletRequestUtils.getIntParameter(request, "pageno", 1);// 
		int pagesize = ServletRequestUtils.getIntParameter(request, "pagesize", 12);//
		UserBean userBean = CommonUtil.getUserInfo(request);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", userBean.getAdmin().getAdminId());
		//paramMap.put("adminId", 1);
		paramMap.put("subjectFinish", 0);
		paramMap.put("pageindex", pageno);
		paramMap.put("pagesize", pagesize);
		
		SubjectLearningListBean subjectLearningListBean = this.subjectLearningService.getAllSubjectByAdminIdAndSubjectFinish(paramMap);
		CommonServiceUtil.beanToJsonWrite(subjectLearningListBean, response);
		return null;
	}
	
	/**
	 * 获取所有学习完结的课程 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 */
	public ModelAndView getAllSubjectFinished(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {

		UserBean userBean = CommonUtil.getUserInfo(request);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", userBean.getAdmin().getAdminId());
		paramMap.put("subjectFinish", 1);
		int pageno = ServletRequestUtils.getIntParameter(request, "pageno", 1);// 
		int pagesize = ServletRequestUtils.getIntParameter(request, "pagesize", 12);//
		paramMap.put("pageindex", pageno);
		paramMap.put("pagesize", pagesize);
		
		SubjectLearningListBean subjectLearningListBean = this.subjectLearningService.getAllSubjectByAdminIdAndSubjectFinish(paramMap);
		CommonServiceUtil.beanToJsonWrite(subjectLearningListBean, response);
		return null;

	}

	/**
	 * 获取所有收藏的课程 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletRequestBindingException
	 * @throws IOException
	 */
	public ModelAndView getAllSubjectFavorite(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {

		UserBean userBean = CommonUtil.getUserInfo(request);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", userBean.getAdmin().getAdminId());
		
		int pageno = ServletRequestUtils.getIntParameter(request, "pageno", 1);//
		int pagesize = ServletRequestUtils.getIntParameter(request, "pagesize", 12);//
		paramMap.put("pageindex", pagesize);
		paramMap.put("pagesize", pageno);
		
		SubjectLearningListBean subjectLearningListBean = this.subjectLearningService.getAllSubjectFavorite(paramMap);
		
		CommonServiceUtil.beanToJsonWrite(subjectLearningListBean, response);
		return null;

	}

	
	// 课程视频评分
	public ModelAndView scoreSubject(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {

		UserBean userBean = CommonUtil.getUserInfo(request);
		BaseResult baseResult = new BaseResult();
		
		SubjectRate subjectRate = new SubjectRate();
		subjectRate.setAdminId(userBean.getAdmin().getAdminId());
		subjectRate.setRateScore(Integer.parseInt(request.getAttribute("subjectScore").toString()));
		subjectRate.setSubjectId(Integer.parseInt(request.getAttribute("subjectId").toString()));
		subjectRate.setCreateTime(new Date());
		int updateInt = this.subjectLearningService.updateSubjectScore(subjectRate);
		
		if(updateInt == 0){
			baseResult.setResultcode(ResultCode.NORMALERROR);
			baseResult.setResultmsg(ResultCode.getResultMsg(ResultCode.NORMALERROR));
		}else{
			baseResult.setResultcode(ResultCode.SUCCESS);
			baseResult.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		}
		CommonServiceUtil.beanToJsonWrite(baseResult, response);
		return null;
	}
	
	//收藏课程  
	public ModelAndView changeSubjectFavorite(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {

		UserBean userBean = CommonUtil.getUserInfo(request);
		BaseResult baseResult = new BaseResult();
		AdminFavorite  adminFavorite = new AdminFavorite();
		int updateInt = 0;
		int isFavorite = ServletRequestUtils.getIntParameter(request, "isFavorite", -1);
		adminFavorite.setAdminId(userBean.getAdmin().getAdminId());
		adminFavorite.setCreateTime(new Date());
		adminFavorite.setSubjectId(Integer.parseInt(request.getAttribute("subjectId").toString()));
		if(isFavorite == 0){
			updateInt = this.subjectLearningService.deleteAdminFavorite(adminFavorite);
		}else if(isFavorite == 1){
			updateInt = this.subjectLearningService.insertAdminFavorite(adminFavorite);
		}
		
		if(updateInt == 0){
			baseResult.setResultcode(ResultCode.NORMALERROR);
			baseResult.setResultmsg(ResultCode.getResultMsg(ResultCode.NORMALERROR));
		}else{
			baseResult.setResultcode(ResultCode.SUCCESS);
			baseResult.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		}
		CommonServiceUtil.beanToJsonWrite(baseResult, response);
		return null;
	}
	
	
	//点击课程进入课程视频列表页面
	public ModelAndView getAllVideosBySubjectId(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {
		
		//UserBean userBean = CommonUtil.getUserInfo(request);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("subjectId", request.getAttribute("subjectId"));
		//paramMap.put("adminId", userBean.getAdmin().getAdminId());
		paramMap.put("subjectId", 1);
		paramMap.put("adminId", 1);

		int pagesize = ServletRequestUtils.getIntParameter(request, "pagesize", 12);

		int pageindex = ServletRequestUtils.getIntParameter(request, "pageindex", 1);
		paramMap.put("pagesize", pagesize);
		paramMap.put("pageindex", pageindex);
		
		VideoLearningBean videoLearningBean = this.subjectLearningService.getAllVideosBySubjectId(paramMap);
		
		
		videoLearningBean.setResultcode(ResultCode.SUCCESS);
		videoLearningBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		
		CommonServiceUtil.beanToJsonWrite(videoLearningBean, response);
		return null;
	}
	
	/**
	 * 笔记分页
	 */
	public ModelAndView getVideoNotePage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletRequestBindingException, IOException {

		UserBean userBean = CommonUtil.getUserInfo(request);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		int videoId = ServletRequestUtils.getIntParameter(request, "videoId", -1);
		int adminId = ServletRequestUtils.getIntParameter(request, "adminId", -1);
		VideoNoteListBean videoNoteListBean ;
		//paramMap.put("videoId",videoId);
		//paramMap.put("adminId", admin.getAdminId());
		int pageIndex = ServletRequestUtils.getIntParameter(request, "pageIndex", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize",21);
		
		if(adminId == -1){
			pageIndex = 1;
		}
		
		paramMap.put("pageindex", pageIndex);
		paramMap.put("pagesize", pageSize);
		
		videoNoteListBean = this.subjectLearningService.getVideoNoteByVideoId(paramMap);
		videoNoteListBean.setResultcode(ResultCode.SUCCESS);
		videoNoteListBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		
		CommonServiceUtil.beanToJsonWrite(videoNoteListBean, response);
		return null;
	}
}
