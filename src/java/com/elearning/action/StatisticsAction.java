package com.elearning.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.elearning.bean.UserBean;
import com.elearning.bean.output.base.ResultCode;
import com.elearning.bean.output.statistics.IndexStatistics;
import com.elearning.bean.output.statistics.StatisticsSubjectUploadBean;
import com.elearning.entity.Admin;
import com.elearning.service.impl.StatisticsService;
import com.elearning.web.util.CommonServiceUtil;
import com.elearning.web.util.CommonUtil;
import com.elearning.web.util.LoggerUtil;
import com.elearning.web.util.NullUtil;

/**
 * @author Ilia
 * @version 创建时间：2015年4月14日 下午5:14:51
 */
public class StatisticsAction extends MultiActionController {
	private StatisticsService statisticsService;

	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	/**
	 * <!-- 统计——左边栏导航显示统计数据 -->
	 */
	public ModelAndView getIndexStatistics(HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = CommonUtil.getUserInfo(request);
		if (null == userBean) {
			return new ModelAndView("../pages/normalLeft.jsp", null);
		}
		Integer adminId = userBean.getAdmin().getAdminId();

		LoggerUtil.DebugLogger.info("[getIndexStatistics] [adminId=" + adminId + "]");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 查询关键值
		Map<String, Object> keyMap = new HashMap<String, Object>();
		// 学习课程数量
		int studySubjectCount = 0;
		keyMap.put("adminId", adminId);
		keyMap.put("subjectFinish", 0);// 未完成
		studySubjectCount = statisticsService.getStudySubjectCount(keyMap);
		resultMap.put("studySubjectCount", studySubjectCount);

		// 收藏课程数量
		int favoriteSubjectCount = 0;
		favoriteSubjectCount = statisticsService.getFavoriteSubjectCount(adminId);
		resultMap.put("favoriteSubjectCount", favoriteSubjectCount);

		// 学习完成课程数量
		int completeSubjectCount = 0;
		keyMap.put("subjectFinish", 1);// 已完成
		completeSubjectCount = statisticsService.getStudySubjectCount(keyMap);
		resultMap.put("completeSubjectCount", completeSubjectCount);

		// 我的作业课程数量
		int studyHomeworkCount = 0;
		studyHomeworkCount = statisticsService.getHomeworkCount(adminId);
		resultMap.put("studyHomeworkCount", studyHomeworkCount);

		// 我的发布更新课程
		int adminUploadSubjectCount = 0;
		keyMap.put("subjectFinish", 0);// 正在更新
		adminUploadSubjectCount = statisticsService.getUploadSubjectCount(keyMap);
		resultMap.put("adminUploadSubjectCount", adminUploadSubjectCount);

		// 我的发布完成课程
		int adminUploadCompleteSubjectCount = 0;
		keyMap.put("subjectFinish", 1);// 更新完成
		adminUploadCompleteSubjectCount = statisticsService.getUploadSubjectCount(keyMap);
		resultMap.put("adminUploadCompleteSubjectCount", adminUploadCompleteSubjectCount);

		return new ModelAndView("../pages/normalLeft.jsp", resultMap);
	}

	/**
	 * <!-- 统计——统计分析--部门课程统计top3 -->
	 */
	public ModelAndView getStatisticsSubject(HttpServletRequest request, HttpServletResponse response) {
		// 查询关键值
		Map<String, Object> keyMap = new HashMap<String, Object>();
		UserBean userBean = CommonUtil.getUserInfo(request);
		IndexStatistics statistics = new IndexStatistics();
		if (NullUtil.isNull(userBean)) {
			statistics.setResultcode(ResultCode.NOLOGIN);
			statistics.setResultmsg(ResultCode.getResultMsg(ResultCode.NOLOGIN));
			CommonServiceUtil.beanToJsonWrite(statistics, response);
			return null;
		}
		int adminId = ServletRequestUtils.getIntParameter(request, "adminId", -1);// 用户
		int departmentId = ServletRequestUtils.getIntParameter(request, "departmentId", -1);// 部门
		String lastTimebefor = ServletRequestUtils.getStringParameter(request, "lastTimebefor", "");// 更新时间
		String lastTimeafter = ServletRequestUtils.getStringParameter(request, "lastTimeafter", "");// 更新时间
		String createTimebefor = ServletRequestUtils.getStringParameter(request, "createTimebefor", "");// 创建
		String createTimeafter = ServletRequestUtils.getStringParameter(request, "createTimeafter", "");// 创建时间

		LoggerUtil.DebugLogger.info("[getStatisticsSubject] [adminId=" + adminId + "] [departmentId=" + departmentId + "] [lastTimebefor=" + lastTimebefor + "][lastTimeafter=" + lastTimeafter
				+ "][createTimebefor=" + createTimebefor + "][createTimeafter=" + createTimeafter + "]");

		keyMap.put("adminId", adminId);
		keyMap.put("lastTimebefor", lastTimebefor);
		keyMap.put("lastTimeafter", lastTimeafter);
		keyMap.put("createTimebefor", createTimebefor);
		keyMap.put("createTimeafter", createTimeafter);
		
		statistics.setResultcode(ResultCode.SUCCESS);
		statistics.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		statistics.setStatisticsTopRate(statisticsService.getStatisticsTopRate(keyMap)) ;
		statistics.setStatisticsTopSubject(statisticsService.getStatisticsTopSubject(keyMap));
		statistics.setStatisticsTopAdmin(statisticsService.getStatisticsTopAdmin(keyMap));

		CommonServiceUtil.beanToJsonWrite(statistics, response);
		return null;
	}
	
	/**
	 * <!-- 统计——统计分析--部门课程统计 -->
	 */
	public ModelAndView getStatisticsChart(HttpServletRequest request, HttpServletResponse response) {
		// 查询关键值
		Map<String, Object> keyMap = new HashMap<String, Object>();
		UserBean userBean = CommonUtil.getUserInfo(request);
		IndexStatistics statistics = new IndexStatistics();
		if (NullUtil.isNull(userBean)) {
			statistics.setResultcode(ResultCode.NOLOGIN);
			statistics.setResultmsg(ResultCode.getResultMsg(ResultCode.NOLOGIN));
			CommonServiceUtil.beanToJsonWrite(statistics, response);
			return null;
		}
		int adminId = ServletRequestUtils.getIntParameter(request, "adminId", -1);// 用户
		int departmentId = ServletRequestUtils.getIntParameter(request, "departmentId", -1);// 部门
		String subjectFinish = ServletRequestUtils.getStringParameter(request, "subjectFinish", "");// 更新时间
		String lastTimebefor = ServletRequestUtils.getStringParameter(request, "lastTimebefor", "");// 更新时间
		String lastTimeafter = ServletRequestUtils.getStringParameter(request, "lastTimeafter", "");// 更新时间
		String createTimebefor = ServletRequestUtils.getStringParameter(request, "createTimebefor", "");// 创建
		String createTimeafter = ServletRequestUtils.getStringParameter(request, "createTimeafter", "");// 创建时间

		LoggerUtil.DebugLogger.info("[getStatisticsChart] [adminId=" + adminId + "] [departmentId=" + departmentId + "] [lastTimebefor=" + lastTimebefor + "][lastTimeafter=" + lastTimeafter
				+ "][createTimebefor=" + createTimebefor + "][createTimeafter=" + createTimeafter + "]");

		keyMap.put("adminId", adminId);
		keyMap.put("subjectFinish", subjectFinish);
		keyMap.put("lastTimebefor", lastTimebefor);
		keyMap.put("lastTimeafter", lastTimeafter);
		keyMap.put("createTimebefor", createTimebefor);
		keyMap.put("createTimeafter", createTimeafter);
		
		statistics.setResultcode(ResultCode.SUCCESS);
		statistics.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		statistics.setStatisticsSubjects(statisticsService.getStatisticsSubjects(keyMap));
		statistics.setStatisticsPlay(statisticsService.getStatisticsPlay(keyMap));

		CommonServiceUtil.beanToJsonWrite(statistics, response);
		return null;
	}
}
