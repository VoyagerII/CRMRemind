package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class AdminFavorite implements Serializable {
    /**
     * admin_favorite.favorite_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer favoriteId;

    /**
     * admin_favorite.subject_id (收藏课程id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer subjectId;

    /**
     * admin_favorite.admin_id (用户id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer adminId;

    /**
     * admin_favorite.create_time (创建时间)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Date createTime;

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}