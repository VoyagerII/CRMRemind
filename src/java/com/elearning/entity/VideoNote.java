package com.elearning.entity;

import java.io.Serializable;
import java.util.Date;

public class VideoNote implements Serializable {
    /**
     * video_note.note_id (主键id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer noteId;

    /**
     * video_note.video_id (对应章节id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer videoId;

    /**
     * video_note.admin_id (用户id)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Integer adminId;

    /**
     * video_note.note_public (笔记是否公开（1：公开，0：不公开。）)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Short notePublic;

    /**
     * video_note.create_time (创建时间)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private Date createTime;

    /**
     * video_note.note_content (笔记内容)
     * @ibatorgenerated 2015-04-22 09:42:28
     */
    private String noteContent;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Short getNotePublic() {
        return notePublic;
    }

    public void setNotePublic(Short notePublic) {
        this.notePublic = notePublic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}