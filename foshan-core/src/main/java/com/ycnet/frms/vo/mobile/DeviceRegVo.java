package com.ycnet.frms.vo.mobile;

public class DeviceRegVo {
	
	private String lockName;
	
	private String devName;
	
	private String devStatus;
	
	private Long devId;
	
	private String alarmContent;
	
	private String lockStatus;
	
	private String doorStatus;
	
	private String signals;
	
	private String battery;
	
	private String temp;
	
	private String humidity;
	
	private String baiduX;
	
	private String baiduY;
	
	private String devAddr;
	
	public String getLockName() {
		return lockName;
	}

	public String getDevName() {
		return devName;
	}

	public String getDevStatus() {
		return devStatus;
	}

	public Long getDevId() {
		return devId;
	}

	public String getAlarmContent() {
		return alarmContent;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName ==null?"":lockName;
	}

	public void setDevName(String devName) {
		this.devName = devName==null?"":devName;
	}

	public void setDevStatus(String devStatus) {
		this.devStatus = devStatus==null?"":devStatus;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent==null?"":alarmContent;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public String getDoorStatus() {
		return doorStatus;
	}

	public String getSignals() {
		return signals;
	}

	public String getBattery() {
		return battery;
	}

	public String getTemp() {
		return temp;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getBaiduX() {
		return baiduX;
	}

	public String getBaiduY() {
		return baiduY;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus==null?"":lockStatus;
	}

	public void setDoorStatus(String doorStatus) {
		this.doorStatus = doorStatus==null?"":doorStatus;
	}

	public void setSignals(String signals) {
		this.signals = signals==null?"":signals;
	}

	public void setBattery(String battery) {
		this.battery = battery==null?"":battery;
	}

	public void setTemp(String temp) {
		this.temp = temp==null?"":temp;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity==null?"":humidity;
	}

	public void setBaiduX(String baiduX) {
		this.baiduX = baiduX==null?"":baiduX;
	}

	public void setBaiduY(String baiduY) {
		this.baiduY = baiduY==null?"":baiduY;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr==null?"":devAddr;
	}

}
