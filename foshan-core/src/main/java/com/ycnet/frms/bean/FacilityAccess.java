package com.ycnet.frms.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FacilityAccess {
    private Long accessId;

    private Long userId;
    
    private Long accessUserId;//授权人ID

    private String userName;

    private String devIds;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date validateTime;

    private String validateStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date accessTime;

    private String remark;
    
    private String mobilePhone;//电话号码

    
    
    public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Long getAccessUserId() {
		return accessUserId;
	}

	public void setAccessUserId(Long accessUserId) {
		this.accessUserId = accessUserId;
	}

	public Long getAccessId() {
        return accessId;
    }

    public void setAccessId(Long accessId) {
        this.accessId = accessId;
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

    public String getDevIds() {
        return devIds;
    }

    public void setDevIds(String devIds) {
        this.devIds = devIds;
    }

    public Date getValidateTime() {
        return validateTime;
    }

    public void setValidateTime(Date validateTime) {
        this.validateTime = validateTime;
    }

    public String getValidateStatus() {
        return validateStatus;
    }

    public void setValidateStatus(String validateStatus) {
        this.validateStatus = validateStatus;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}