package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class Courses implements Serializable {
    /**
     * courses.courses_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer coursesId;

    /**
     * courses.admin_id (用户id)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer adminId;

    /**
     * courses.subject_id (课程id)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Integer subjectId;

    /**
     * courses.subject_finish (是否完成课程（1：已完成，0：未完成。）)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Short subjectFinish;

    /**
     * courses.create_time (创建时间)
     * @ibatorgenerated 2015-04-22 09:42:27
     */
    private Date createTime;

    public Integer getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(Integer coursesId) {
        this.coursesId = coursesId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Short getSubjectFinish() {
        return subjectFinish;
    }

    public void setSubjectFinish(Short subjectFinish) {
        this.subjectFinish = subjectFinish;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}