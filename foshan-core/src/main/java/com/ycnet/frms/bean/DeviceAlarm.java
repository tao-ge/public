package com.ycnet.frms.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DeviceAlarm {
    private Long alarmProcessId;

    private Long alarmId;

    private Long devId;

    private String devCode;

    private String devName;

    private String devImei;

    private String devMac;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
    private Date alarmTime;

    private Date rptTime;

    private String alarmTypes;

    private String alarmContent;

    private String dealSign;

    private String dealSituation;

    private Long userId;

    private String userName;
    
    private String fName;//所属设施名称
    
    private String fdevName;//导出所属设施名称
    
    private String dateTime;//格式化后的日期

    public Long getAlarmProcessId() {
        return alarmProcessId;
    }

    public void setAlarmProcessId(Long alarmProcessId) {
        this.alarmProcessId = alarmProcessId;
    }

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
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

    public String getDevImei() {
        return devImei;
    }

    public void setDevImei(String devImei) {
        this.devImei = devImei;
    }

    public String getDevMac() {
        return devMac;
    }

    public void setDevMac(String devMac) {
        this.devMac = devMac;
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
        this.alarmTypes = alarmTypes;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public String getDealSign() {
        return dealSign;
    }

    public void setDealSign(String dealSign) {
        this.dealSign = dealSign;
    }

    public String getDealSituation() {
        return dealSituation;
    }

    public void setDealSituation(String dealSituation) {
        this.dealSituation = dealSituation;
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getFdevName() {
		return fdevName;
	}

	public void setFdevName(String fdevName) {
		this.fdevName = fdevName;
	}
}