package com.elearning.bean.output.myHomeWork;

import java.io.Serializable;

import com.elearning.bean.output.base.BaseResult;
import com.elearning.entity.AdminAnswer;
import com.elearning.entity.VideoHomework;

/**
 * 作业答案关联实体bean
 * @author dingdapeng
 *
 */
public class MyHomeWorkAnswer extends BaseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4841408262589362131L;

	/**
     * `adminAnswer`.adminAnswer (作业答案信息)
     * @author dingdapeng 2015-04-15
     */
	private AdminAnswer adminAnswer;
	
	/**
     * `videoHomework`.videoHomework (作业题目信息)
     * @author dingdapeng 2015-04-15
     */
	private VideoHomework videoHomework;
}
