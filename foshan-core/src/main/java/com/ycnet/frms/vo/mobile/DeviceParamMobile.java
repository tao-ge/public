package com.ycnet.frms.vo.mobile;

import java.util.Date;

public class DeviceParamMobile {
    private Long paramId;

    private String lowTempThd;

    private String highTempShd;

    private String batteryThd;

    private String tilt;

    private String ip;

    private String port;
    
    private Long dormantTime;
   
    private Long selfCheckingTime;
   
    private Long reportTime;
   
    private Long lockAbnorTime;
   
    private Long nowTime = new Date().getTime();
    
    private long ImDeviceNum = 0;
   
   
	public Long getNowTime() {
		return nowTime;
	}

	public Long getParamId() {
		return paramId;
	}

	public String getLowTempThd() {
		return lowTempThd;
	}

	public String getHighTempShd() {
		return highTempShd;
	}

	public String getBatteryThd() {
		return batteryThd;
	}

	public String getTilt() {
		return tilt;
	}

	public String getIp() {
		return ip;
	}

	public String getPort() {
		return port;
	}

	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}

	public void setLowTempThd(String lowTempThd) {
		this.lowTempThd = lowTempThd;
	}

	public void setHighTempShd(String highTempShd) {
		this.highTempShd = highTempShd;
	}

	public void setBatteryThd(String batteryThd) {
		this.batteryThd = batteryThd;
	}

	public void setTilt(String tilt) {
		this.tilt = tilt;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Long getDormantTime() {
		return dormantTime;
	}

	public Long getSelfCheckingTime() {
		return selfCheckingTime;
	}

	public Long getLockAbnorTime() {
		return lockAbnorTime;
	}

	public void setDormantTime(Long dormantTime) {
		this.dormantTime = dormantTime;
	}

	public void setSelfCheckingTime(Long selfCheckingTime) {
		this.selfCheckingTime = selfCheckingTime;
	}

	public void setLockAbnorTime(Long lockAbnorTime) {
		this.lockAbnorTime = lockAbnorTime;
	}

	public Long getReportTime() {
		return reportTime;
	}

	public void setReportTime(Long reportTime) {
		this.reportTime = reportTime;
	}

	public long getImDeviceNum() {
		return ImDeviceNum;
	}

	public void setImDeviceNum(long imDeviceNum) {
		ImDeviceNum = imDeviceNum;
	}
}