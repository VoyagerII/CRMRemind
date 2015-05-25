package com.elearning.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.elearning.bean.output.Subject.SubjectListBean;
import com.elearning.bean.output.Subject.UpdateSubjectPageBean;
import com.elearning.bean.output.Subject.VideoBean;
import com.elearning.bean.output.subjectManage.subjectManageBean;
import com.elearning.entity.Subjects;
import com.elearning.entity.Video;
import com.elearning.entity.VideoHomework;
import com.elearning.service.BaseService;
import com.elearning.web.util.PageInfo;
import com.elearning.web.util.PagingUtil;

/**
 * 课程管理模块Service
 * @author xinwenfeng
 *
 */
public class SubjectManageService extends BaseService {
	
	/**
	 * 课程管理的筛选课程
	 * @param paramMap
	 * @return
	 */
	public List<subjectManageBean> getManageSubjects(Map<String, Object> paramMap) {
		int count = (Integer) this.baseDao.selectOne(
				"sqlMapper.customSqlMapper.SubjectSqlMapper.xml.countGetManageSubjects", paramMap);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(Integer.parseInt(paramMap.get("pageIndex")
				.toString()));
		pageInfo.setPageCount(Integer.parseInt(paramMap.get("pageSize")
				.toString()));
		
		pageInfo.setTotalCount(count);
		
		PagingUtil.getPaging(pageInfo, paramMap);

		List<subjectManageBean> list = (List<subjectManageBean>) this.baseDao.selectList(
				"sqlMapper.customSqlMapper.SubjectSqlMapper.xml.getManageSubjects", paramMap);
		return list;
	}
	
	/**
	 * 更新subject的status
	 * @param subject
	 * @return
	 */
	public int updateSubjectStatus(Subjects subject){
		int i = this.baseDao.update("com.elearning.SubjectsMapper.updateByPrimaryKeySelective", subject);
		return i;
	}

	
	
	

}
