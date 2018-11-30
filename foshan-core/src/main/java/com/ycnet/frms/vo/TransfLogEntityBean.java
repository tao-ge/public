package com.ycnet.frms.vo;

import com.ycnet.frms.bean.TransfLogEntity;

public class TransfLogEntityBean extends TransfLogEntity{
	
	private String startTime;
	
	private String endTime;

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
