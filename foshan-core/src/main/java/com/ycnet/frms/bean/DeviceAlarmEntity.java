package com.ycnet.frms.bean;

import java.util.Date;

public class DeviceAlarmEntity {
    private Long alarmId;

    private Long lockStatusId;

    private Long devId;
    
    private Long discId;//报警检测板ID

    private Date alarmTime;

    private Date rptTime;

    private String alarmTypes;

    private String alarmContent;

    private String dealSign;

    private String dealSituation;

    private Date dealTime;

    private Long userId;

    private String userName;
    
    private Long orgId;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Long getLockStatusId() {
        return lockStatusId;
    }

    public void setLockStatusId(Long lockStatusId) {
        this.lockStatusId = lockStatusId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Date getRptTime() {
        return rptTime;
    }

    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }

    public String getAlarmTypes() {
        return alarmTypes;
    }

    public void setAlarmTypes(String alarmTypes) {
        this.alarmTypes = alarmTypes == null ? null : alarmTypes.trim();
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent == null ? null : alarmContent.trim();
    }

    public String getDealSign() {
        return dealSign;
    }

    public void setDealSign(String dealSign) {
        this.dealSign = dealSign == null ? null : dealSign.trim();
    }

    public String getDealSituation() {
        return dealSituation;
    }

    public void setDealSituation(String dealSituation) {
        this.dealSituation = dealSituation == null ? null : dealSituation.trim();
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
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
        this.userName = userName == null ? null : userName.trim();
    }

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getDiscId() {
		return discId;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}
    
    
}