package com.elearning.bean.output.Subject;

import java.io.Serializable;
import java.util.List;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.entity.Subjects;

public class SubjectListBean  extends BaseResult implements Serializable {
	
	private static final long serialVersionUID = -7135758983249159807L;
	
	private int total = 0;
	private int totalPages = 0;
	private List<Subjects> subjectsBeanList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Subjects> getSubjectsBeanList() {
		return subjectsBeanList;
	}

	public void setSubjectsBeanList(List<Subjects> subjectsBeanList) {
		this.subjectsBeanList = subjectsBeanList;
	}
	
}
