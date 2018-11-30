package com.ycnet.frms.vo;

import com.ycnet.frms.bean.DeviceStatus;

public class DeviceStatusBean extends DeviceStatus {

	
	private String opStyleName;
	private String lockStatusName;//锁状态中文名
	private String doorStatusName;//门状态中文名
	private String isAlarmName;//是否报警中文状态
	
	private String tempAlarm;//温度报警标志 1正常  0报警
	private String humiAlarm;//湿度报警标志 1正常  0报警
	private String battAlarm;//电量报警标志 1正常  0报警
	private String tiltAlarm;//倾斜报警标志 1正常  0报警
	private String doorOpenErr;//异常开门
	private String dateTime;//格式化后的日期
	private String devStatu;//设备状态0 正常 1 工作 2报警
	
	
	
	
	public String getLockStatusName() {
		return lockStatusName;
	}
	public void setLockStatusName(String lockStatusName) {
		this.lockStatusName = lockStatusName;
	}
	public String getDoorStatusName() {
		return doorStatusName;
	}
	public void setDoorStatusName(String doorStatusName) {
		this.doorStatusName = doorStatusName;
	}
	public String getIsAlarmName() {
		return isAlarmName;
	}
	public void setIsAlarmName(String isAlarmName) {
		this.isAlarmName = isAlarmName;
	}
	
	public String getTempAlarm() {
		return tempAlarm;
	}
	public void setTempAlarm(String tempAlarm) {
		this.tempAlarm = tempAlarm;
	}
	public String getHumiAlarm() {
		return humiAlarm;
	}
	public void setHumiAlarm(String humiAlarm) {
		this.humiAlarm = humiAlarm;
	}
	public String getBattAlarm() {
		return battAlarm;
	}
	public void setBattAlarm(String battAlarm) {
		this.battAlarm = battAlarm;
	}
	public String getTiltAlarm() {
		return tiltAlarm;
	}
	public void setTiltAlarm(String tiltAlarm) {
		this.tiltAlarm = tiltAlarm;
	}
	public String getOpStyleName() {
		return opStyleName;
	}
	public void setOpStyleName(String opStyleName) {
		this.opStyleName = opStyleName;
	}
	public String getDoorOpenErr() {
		return doorOpenErr;
	}
	public void setDoorOpenErr(String doorOpenErr) {
		this.doorOpenErr = doorOpenErr;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getDevStatu() {
		return devStatu;
	}
	public void setDevStatu(String devStatu) {
		this.devStatu = devStatu;
	}
	
}
