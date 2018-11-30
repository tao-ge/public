package com.ycnet.frms.vo.mobile;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FacilityListOpd {
	
	private Long devId;
	
	private String devCode;
	
	private String devName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date time;
	
	private	String devType;
	
	private	String devState;
	
	private String baiduX;
	
	private String baiduY;
	
	private String devAddr;

	public Long getDevId() {
		return devId;
	}

	public String getDevCode() {
		return devCode;
	}

	public String getDevName() {
		return devName;
	}

	public String getDevType() {
		return devType;
	}

	public String getDevState() {
		return devState;
	}

	public String getBaiduX() {
		return baiduX;
	}

	public String getBaiduY() {
		return baiduY;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public void setBaiduX(String baiduX) {
		this.baiduX = baiduX;
	}

	public void setBaiduY(String baiduY) {
		this.baiduY = baiduY;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

}
