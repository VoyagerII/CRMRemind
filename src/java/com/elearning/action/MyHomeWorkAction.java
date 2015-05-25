package com.elearning.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.elearning.bean.UserBean;
import com.elearning.bean.output.base.BaseResult;
import com.elearning.bean.output.base.ResultCode;
import com.elearning.bean.output.login.LoginBean;
import com.elearning.bean.output.myHomeWork.SubjectVideHomeAnswer;
import com.elearning.bean.output.myHomeWork.bean.AdminAnswerBean;
import com.elearning.bean.output.myHomeWork.bean.VideoBean;
import com.elearning.entity.Admin;
import com.elearning.entity.AdminAnswer;
import com.elearning.entity.Video;
import com.elearning.entity.VideoHomework;
import com.elearning.service.impl.MyHomeWorkService;
import com.elearning.web.util.CommonServiceUtil;
import com.elearning.web.util.CommonUtil;
import com.elearning.web.util.DictionaryCacheKeys;
import com.google.gson.Gson;

/**
 * TODO 作业Action
 * 
 * @author dingdapeng
 */
public class MyHomeWorkAction extends MultiActionController {
	
	private String getNowDateString() {

		java.text.SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	} 
	
	private MyHomeWorkService myHomeWorkService;

	public void setMyHomeWorkService(MyHomeWorkService myHomeWorkService) {
		this.myHomeWorkService = myHomeWorkService;
	}
	
	/**
	 * 查询作业概况---仅有视屏层--
	 * 分页、概要信息
	 * 点入后，进入作业，一个作业好多题，
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getSubjectListByMyAnswer(HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		UserBean userBean = CommonUtil.getUserInfo(request);
		int pageIndex = ServletRequestUtils.getIntParameter(request,"pageIndex", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize",1);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		paramMap.put("adminId", 1);
		
		List<VideoBean> videoBeanList = this.myHomeWorkService.getSubjectList(paramMap);
		int totalpage = videoBeanList.size()/1;
		if (videoBeanList.size()%1 != 0) {
			totalpage = totalpage + 1;
		}
		
		SubjectVideHomeAnswer subjectVideHomeAnswer = new SubjectVideHomeAnswer(videoBeanList,null);
		subjectVideHomeAnswer.setTotalpage(totalpage);
		subjectVideHomeAnswer.setResultcode(ResultCode.USRPWILLEGAL);
		subjectVideHomeAnswer.setResultmsg(ResultCode.getResultMsg(ResultCode.USRPWILLEGAL));
		
		Gson gson = new Gson();
		System.err.println(gson.toJson(subjectVideHomeAnswer));
		CommonServiceUtil.beanToJsonWrite(subjectVideHomeAnswer, response);
		return null;
	}
	

	/**
	 * 查询作业详情By视频ID
	 * 关联answer表得到每一份作业
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getAnswerByVideoId(HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		UserBean userBean = CommonUtil.getUserInfo(request);
		long videoId = ServletRequestUtils.getLongParameter(request, "videoId", -1L);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", 1);
		paramMap.put("videoId", 1);
		
		List<AdminAnswerBean> adminAnswerBeanList = this.myHomeWorkService.getAnswerByVideoId(paramMap);
		
		SubjectVideHomeAnswer subjectVideHomeAnswer = new SubjectVideHomeAnswer(null,adminAnswerBeanList);
		subjectVideHomeAnswer.setResultcode(ResultCode.USRPWILLEGAL);
		subjectVideHomeAnswer.setResultmsg(ResultCode.getResultMsg(ResultCode.USRPWILLEGAL));
		CommonServiceUtil.beanToJsonWrite(subjectVideHomeAnswer, response);
		return null;
	} 
	
	/**
	 * 给别人调用的
	 * 传入··video_id
	 * 返回··该视频的作业··很多道题
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getQuestionByVideoId(HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		UserBean userBean = CommonUtil.getUserInfo(request);
		long videoId = ServletRequestUtils.getLongParameter(request, "videoId", -1L);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("videoId", 1);
		
		List<AdminAnswerBean> adminAnswerBeanList = this.myHomeWorkService.getQuestionByVideoId(paramMap);
		
		SubjectVideHomeAnswer subjectVideHomeAnswer = new SubjectVideHomeAnswer(null,adminAnswerBeanList);
		subjectVideHomeAnswer.setResultcode(ResultCode.USRPWILLEGAL);
		subjectVideHomeAnswer.setResultmsg(ResultCode.getResultMsg(ResultCode.USRPWILLEGAL));
		CommonServiceUtil.beanToJsonWrite(subjectVideHomeAnswer, response);
		return null;
	} 
	
	/**
	 * 新增做作业
	 * 一个视频可能会有好多题
	 * 此方法是针对每一道题
	 * 也就是做完一道提交下
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertAnswerByVideoIdHomeworkId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserBean userBean = CommonUtil.getUserInfo(request);
		Date nowTime = new SimpleDateFormat("yyyy-MM-dd").parse(getNowDateString()); 
		int videoId = ServletRequestUtils.getIntParameter(request, "videoId", -1);
		int homeworkId = ServletRequestUtils.getIntParameter(request, "homeworkId", -1);
		String answerContent = ServletRequestUtils.getStringParameter(request, "answerContent","填入数据异常");
//		int homeworkRequired = ServletRequestUtils.getIntParameter(request, "homeworkRequired", -1);
		
//  必填,接受的为空,那就是没做题,让它做!!!
		if (null == answerContent || "填入数据异常".equals(answerContent) || "null".equals(answerContent)) {
			return null;
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", 1);
		paramMap.put("videoId", videoId);
		paramMap.put("lastTime", nowTime);
		paramMap.put("homeworkFinish", 1);
		
		AdminAnswer adminAnswer = new AdminAnswer();
		adminAnswer.setAdminId(1);
		adminAnswer.setVideoId(new Integer(videoId));
		adminAnswer.setHomeworkId(new Integer(homeworkId));
		adminAnswer.setAnswerContent(answerContent);
		
		int i = this.myHomeWorkService.insertAnswerByVideoIdHomeworkId(adminAnswer,paramMap);
		
		if (i == 2) {
			BaseResult br = new BaseResult();
			br.setResultcode(ResultCode.SUCCESS);
			br.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
			CommonServiceUtil.beanToJsonWrite(br, response); 
			return null;
		}

		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 查询视频列表By课程ID
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getVideoListBySubjectId(HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		LoginBean loginBean = (LoginBean) CommonUtil.getInfoBySession(request, DictionaryCacheKeys.User_Info);
		long subjectId = ServletRequestUtils.getLongParameter(request, "subjectId", -1L);
		int pageIndex = ServletRequestUtils.getIntParameter(request,"pageIndex", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize",20);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		paramMap.put("adminId", loginBean.getAdmin().getAdminId());
		paramMap.put("subjectId", subjectId);
		
		this.myHomeWorkService.setMasterDB();
		List<Video> videoList = this.myHomeWorkService.getVideoListBySubjectId(paramMap);
		
		SubjectVideHomeAnswer subjectVideHomeAnswer = new SubjectVideHomeAnswer();
		subjectVideHomeAnswer.setResultcode(ResultCode.USRPWILLEGAL);
		subjectVideHomeAnswer.setResultmsg(ResultCode.getResultMsg(ResultCode.USRPWILLEGAL));
		CommonServiceUtil.beanToJsonWrite(subjectVideHomeAnswer, response);
		return null;
	}
	
	/**
	 * 查询作业列表By视频ID
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getHomeWorkListByVideoId(HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		LoginBean loginBean = (LoginBean) CommonUtil.getInfoBySession(request, DictionaryCacheKeys.User_Info);
		long videoId = ServletRequestUtils.getLongParameter(request, "videoId", -1L);
		int pageIndex = ServletRequestUtils.getIntParameter(request,"pageIndex", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize",20);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		paramMap.put("adminId", loginBean.getAdmin().getAdminId());
		paramMap.put("videoId", videoId);
		
		this.myHomeWorkService.setMasterDB();
		List<VideoHomework> videoHomeWorkList = this.myHomeWorkService.getHomeWorkListByVideoId(paramMap);
		
		SubjectVideHomeAnswer subjectVideHomeAnswer = new SubjectVideHomeAnswer();
		subjectVideHomeAnswer.setResultcode(ResultCode.USRPWILLEGAL);
		subjectVideHomeAnswer.setResultmsg(ResultCode.getResultMsg(ResultCode.USRPWILLEGAL));
		CommonServiceUtil.beanToJsonWrite(subjectVideHomeAnswer, response);
		return null;
	}
	
}
