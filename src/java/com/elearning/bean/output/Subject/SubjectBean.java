package com.elearning.bean.output.Subject;

import java.io.Serializable;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.entity.Subjects;

public class SubjectBean  extends BaseResult implements Serializable {
	
	private static final long serialVersionUID = -6857618338675648348L;
	
	private Subjects subjects;

	public Subjects getSubjects() {
		return subjects;
	}

	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}
	
}
