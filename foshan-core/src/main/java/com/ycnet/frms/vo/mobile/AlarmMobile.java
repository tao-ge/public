package com.ycnet.frms.vo.mobile;

import java.util.Date;

public class AlarmMobile {

	private Long alarmId;

    private Long lockStatusId;

    private Long devId;

    private Date alarmTime;

    private String alarmTypes;

    private String alarmContent;

    private String dealSign;

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
    
}
