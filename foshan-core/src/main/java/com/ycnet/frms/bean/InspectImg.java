package com.ycnet.frms.bean;

import java.util.Date;

import com.ycnet.core.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class InspectImg {
    private Long devImgId;

    private Long devId;

    private String devCode;
    
    private String devName;

    private String imgTypes;

    private String imgDesc;

    @JsonSerialize(using=CustomDateSerializer.class)
    private Date photoTime;

    private String imgUrl;

    private String flag;
    
    private Long workId;

    private String inspectStatus;
    
    
  

	public String getInspectStatus() {
		return inspectStatus;
	}

	public void setInspectStatus(String inspectStatus) {
		this.inspectStatus = inspectStatus;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public Long getDevImgId() {
        return devImgId;
    }

    public void setDevImgId(Long devImgId) {
        this.devImgId = devImgId;
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

    public String getImgTypes() {
        return imgTypes;
    }

    public void setImgTypes(String imgTypes) {
        this.imgTypes = imgTypes;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public Date getPhotoTime() {
        return photoTime;
    }

    public void setPhotoTime(Date photoTime) {
        this.photoTime = photoTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}
}