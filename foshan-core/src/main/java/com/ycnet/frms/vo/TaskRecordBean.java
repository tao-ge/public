package com.ycnet.frms.vo;

import com.ycnet.frms.bean.TaskRecord;

public class TaskRecordBean extends TaskRecord{
	
	private String userName;
	
	private String areaCode;
	
	private String startTime;

	private String endTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	

	
	
	

}
