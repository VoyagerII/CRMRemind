package com.elearning.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.bean.output.base.ResultCode;
import com.elearning.bean.output.subjectManage.subjectManageBean;
import com.elearning.entity.Admin;
import com.elearning.entity.Subjects;
import com.elearning.entity.Video;
import com.elearning.service.impl.SubjectManageService;
import com.elearning.web.util.CommonServiceUtil;
import com.elearning.web.util.CommonUtil;

/**
 * 课程管理action
 * 
 * @author xinwenfeng
 *
 */
public class SubjectManageAction extends MultiActionController {

	private SubjectManageService subjectManageService;

	public void setSubjectManageService(SubjectManageService subjectManageService) {
		this.subjectManageService = subjectManageService;
	}
	
	/**
	 * 课程管理的筛选课程
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getManageSubjects(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String startDate = ServletRequestUtils.getStringParameter(request, "startDate", null);
		String endDate = ServletRequestUtils.getStringParameter(request, "endDate", null);
		Integer adminId = ServletRequestUtils.getIntParameter(request, "adminId", -1);
		Integer departmentId = ServletRequestUtils.getIntParameter(request, "departmentId", -1);
		int pageIndex = ServletRequestUtils.getIntParameter(request, "pageIndex", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize",10);

		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("adminId", adminId);
		paramMap.put("departmentId", departmentId);
		paramMap.put("pageIndex",pageIndex);
		paramMap.put("pageSize",pageSize);
		
		List<subjectManageBean> subjectManageListBean = subjectManageService.getManageSubjects(paramMap);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("Resultcode", ResultCode.SUCCESS);
		model.put("Resultmsg", ResultCode.getResultMsg(ResultCode.SUCCESS));
		model.put("subjectManageListBean", subjectManageListBean);
		model.put("totalPages", (int)((subjectManageListBean.size()+10)/10));
		model.put("pageno",pageIndex);
		CommonServiceUtil.beanToJsonWrite(model, response);
//		return new ModelAndView("../pages/subjectManage/subjectManage.jsp",model);
		return null;
	}
	
	/**
	 * 更新subject的status
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateSubjectStatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String subjectId = request.getParameter("subjectId");
		String subjectStatus = request.getParameter("subjectStatus");

		Subjects subject = new Subjects();
		subject.setSubjectId(Integer.parseInt(subjectId));
		subject.setSubjectStatus(Short.parseShort(subjectStatus));
		
		this.subjectManageService.updateSubjectStatus(subject);
		
		BaseResult br = new BaseResult();
		br.setResultcode(ResultCode.SUCCESS);
		br.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		CommonServiceUtil.beanToJsonWrite(br, response);
		
		return null;
	}

	
	
	
	
	

}
