package com.elearning.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.elearning.bean.UserBean;
import com.elearning.bean.output.Subject.SubjectListBean;
import com.elearning.bean.output.Subject.UpdateSubjectPageBean;
import com.elearning.bean.output.Subject.VideoBean;
import com.elearning.bean.output.base.BaseResult;
import com.elearning.bean.output.base.ResultCode;
import com.elearning.entity.Admin;
import com.elearning.entity.Subjects;
import com.elearning.entity.Video;
import com.elearning.entity.VideoHomework;
import com.elearning.service.impl.SubjectService;
import com.elearning.web.util.CommonServiceUtil;
import com.elearning.web.util.CommonUtil;

/**
 * 课程科目action
 * 
 * @author xinwenfeng
 *
 */
public class SubjectAction extends MultiActionController {

	private SubjectService subjectService;
	
	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	/**
	 * 根据用户id获得课程科目列表(分页)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getSubjectByAdminId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		Admin admin = CommonUtil.getUserInfo(request);
//		Integer adminId = admin.getAdminId();
		Integer adminId = 1;
		int pageIndex = ServletRequestUtils.getIntParameter(request, "pageIndex", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize",6);

		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", adminId);
		paramMap.put("pageIndex",pageIndex);
		paramMap.put("pageSize",pageSize);
		
		SubjectListBean subjectListBean = subjectService.getSubject(paramMap);
		subjectListBean.setResultcode(ResultCode.SUCCESS);
		subjectListBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		CommonServiceUtil.beanToJsonWrite(subjectListBean, response);
		return null;
	}
	
	/**
	 * 新增课程科目、上传第一章视频
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addSubjectVideo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserBean userBean = CommonUtil.getUserInfo(request);
		Integer adminId = userBean.getAdmin().getAdminId();
		Integer departmentId = userBean.getAdmin().getDepartmentId();
		String subjectName = request.getParameter("subjectName");
		String subjectPicture = request.getParameter("subjectPicture");
		String subjectDesc = request.getParameter("subjectDesc");
		String subjectFinsh = request.getParameter("subjectFinsh");
//		String lastVideoId = request.getParameter("lastVideoId");

		Subjects subject = new Subjects();
		subject.setAdminId(adminId);
		subject.setCreateTime(new Date());
		subject.setSubjectDesc(subjectDesc);
		subject.setSubjectFinsh(Short.parseShort(subjectFinsh));
		subject.setLastVideoId(1);
		subject.setSubjectName(subjectName);
		subject.setSubjectPicture(subjectPicture);
		subject.setSubjectStatus((short)1);
		
		String videoName = request.getParameter("videoName");
		String videoPublic = request.getParameter("videoPublic");
		String videoChapter = request.getParameter("videoChapter");
		String videoUrl = request.getParameter("videoUrl");
		String videoDesc = request.getParameter("videoDesc");
		String videoResource = request.getParameter("videoResource");

		Video video = new Video();
		video.setCreateTime(new Date());
		video.setDepartmentId(departmentId);
		video.setVideoAdmin(adminId);
		video.setVideoChapter(Integer.parseInt(videoChapter));
		video.setVideoDesc(videoDesc);
		video.setVideoName(videoName);
		video.setVideoPublic(Short.parseShort(videoPublic));
		video.setVideoResource(videoResource);
		video.setVideoStatus((short)1);
		video.setVideoUrl(videoUrl);
		
		this.subjectService.addSubject(subject,video);
		
		BaseResult br = new BaseResult();
		br.setResultcode(ResultCode.SUCCESS);
		br.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		CommonServiceUtil.beanToJsonWrite(br, response);
		
		return null;
	}
	
	/**
	 * 根据subjectId保存一个视频
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addVideoBySubjectId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserBean userBean = CommonUtil.getUserInfo(request);
		Integer adminId = userBean.getAdmin().getAdminId();
		Integer departmentId = userBean.getAdmin().getDepartmentId();
		String subjectId = request.getParameter("subjectId");
		String videoName = request.getParameter("videoName");
		String videoPublic = request.getParameter("videoPublic");
		String videoChapter = request.getParameter("videoChapter");
		String videoUrl = request.getParameter("videoUrl");
		String videoDesc = request.getParameter("videoDesc");
		String videoResource = request.getParameter("videoResource");
		
		Video video = new Video();
		video.setCreateTime(new Date());
		video.setDepartmentId(departmentId);
		video.setVideoAdmin(adminId);
		video.setVideoChapter(Integer.parseInt(videoChapter));
		video.setVideoDesc(videoDesc);
		video.setVideoName(videoName);
		video.setVideoPublic(Short.parseShort(videoPublic));
		video.setVideoResource(videoResource);
		video.setVideoStatus((short)1);
		video.setVideoUrl(videoUrl);
		video.setSubjectId(Integer.parseInt(subjectId));
		
		this.subjectService.addVideoBySubjectId(video);
		
		BaseResult br = new BaseResult();
		br.setResultcode(ResultCode.SUCCESS);
		br.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		CommonServiceUtil.beanToJsonWrite(br, response);
		
		return null;
	}
	
	/**
	 * 根据videoId新增一条的作业
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addHomework(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String videoId = request.getParameter("videoId");
		String homeworkContent = request.getParameter("homeworkContent");
		String homeworkRequired = request.getParameter("homeworkRequired");
		
		VideoHomework videoHomework = new VideoHomework();
		videoHomework.setCreateTime(new Date());
		videoHomework.setHomeworkContent(homeworkContent);
		videoHomework.setHomeworkRequired(Short.parseShort(homeworkRequired));
		videoHomework.setVideoId(Integer.parseInt(videoId));
		
		this.subjectService.addHomeword(videoHomework);
		
		BaseResult br = new BaseResult();
		br.setResultcode(ResultCode.SUCCESS);
		br.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		CommonServiceUtil.beanToJsonWrite(br, response);
		
		return null;
	}
	
	/**
	 * 根据subjectId获得更新发布页面的数据(subject和video)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getSubjectVideosBySubjectId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer subjectId = Integer.parseInt(request.getParameter("subjectId"));
		UpdateSubjectPageBean updateSubjectPageBean = subjectService.getUpdateSubjectPageBean(subjectId);
		updateSubjectPageBean.setResultcode(ResultCode.SUCCESS);
		updateSubjectPageBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		CommonServiceUtil.beanToJsonWrite(updateSubjectPageBean, response);
		return null;
	}
	
	/**
	 * 根据videoId获得video
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView getVideoByVideoId(HttpServletRequest request,HttpServletResponse response){
		Integer videoId = Integer.parseInt(request.getParameter("videoId"));
		VideoBean video = subjectService.getVideoByVideoId(videoId);
		video.setResultcode(ResultCode.SUCCESS);
		video.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		CommonServiceUtil.beanToJsonWrite(video, response);
		return null;
	}
	
	/**
	 * 根据adminId获得已经完结的课程
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView getFinishedSubjects(HttpServletRequest request,HttpServletResponse response){
//		UserBean userBean = CommonUtil.getUserInfo(request);
//		Integer adminId = userBean.getAdmin().getAdminId();
		Integer adminId = 1;
		int pageIndex = ServletRequestUtils.getIntParameter(request, "pageIndex", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize",6);

		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", adminId);
		paramMap.put("pageIndex",pageIndex);
		paramMap.put("pageSize",pageSize);
		SubjectListBean subjectListBean = subjectService.getFinishedSubject(paramMap);
		subjectListBean.setResultcode(ResultCode.SUCCESS);
		subjectListBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		CommonServiceUtil.beanToJsonWrite(subjectListBean, response);
		return null;
	}
	
	
	
	
	
	
	

}
