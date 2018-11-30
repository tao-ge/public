package com.ycnet.frms.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ycnet.core.util.CustomDateSerializer;
import com.ycnet.core.util.DateJsonDeserializer;

public class FacilityInspect {
    private Long inspectId;

    private Long devId;

    private String devCode;

    private String devName;

    private Long userId;

    private String userName;

    @JsonDeserialize(using = DateJsonDeserializer.class)
    @JsonSerialize(using=CustomDateSerializer.class)
    private Date inspectTime;

    private String longitude;

    private String latitude;

    private String inspectInfo;

    private String inspectStatus;
    
    private String baiduX;
    
    private String baiduY;
    
    private String imgUrl;//巡检图片路径

    public String getBaiduX() {
		return baiduX;
	}

	public void setBaiduX(String baiduX) {
		this.baiduX = baiduX;
	}

	public String getBaiduY() {
		return baiduY;
	}

	public void setBaiduY(String baiduY) {
		this.baiduY = baiduY;
	}

	public Long getInspectId() {
        return inspectId;
    }

    public void setInspectId(Long inspectId) {
        this.inspectId = inspectId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getInspectInfo() {
        return inspectInfo;
    }

    public void setInspectInfo(String inspectInfo) {
        this.inspectInfo = inspectInfo;
    }

    public String getInspectStatus() {
        return inspectStatus;
    }

    public void setInspectStatus(String inspectStatus) {
        this.inspectStatus = inspectStatus;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}