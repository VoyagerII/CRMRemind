package com.elearning.bean.output.myHomeWork.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author dingdapeng
 *
 */
public class VideoBean implements Serializable {
    /**
     * video.video_id (视频id)
     * @ibatorgenerated 2015-04-15 11:22:55
     */
    private Integer videoId;

    /**
     * video.video_name (视频名称)
     * @ibatorgenerated 2015-04-15 11:22:55
     */
    private String videoName;
    
    /**
     * video.total (数量)
     * @ibatorgenerated 2015-04-15 11:22:55
     */
    private Integer countNum;

    /**
     * video.last_time (最后时间)
     * @ibatorgenerated 2015-04-15 11:22:55
     */
    private Date lastTime;

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

}