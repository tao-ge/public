package com.ycnet.frms.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ycnet.core.util.CustomDateSerializer;

public class DeviceStatus {
    private Long devStatusId;

    private Long devId;

    private String devCode;

    private String devName;
    @JsonSerialize(using=CustomDateSerializer.class)
    private Date colTime;
    
    @JsonSerialize(using=CustomDateSerializer.class)
    private Date rptTime;

    private String oprStyle;

    private String lockStatus;

    private String doorStatus;

    private String lowTempThd;

    private String highTempShd;

    private String temp;

    private String humidityShd;

    private String humidity;

    private String batteryThd;

    private String battery;

    private String tiltThd;

    private String tilt;

    private String signals;

    private String devImei;

    private String devMac;

    private String alarmSign;

    private String dealSign;

    private String curStatus;

    private Long orgId;
    
    private String fName;//所属设施名称
    
    private String fdevName;//导出所属设施名称
    
    private String dateTime;//格式化后的日期
    
    private String alarmContent;//报警内容

    public Long getDevStatusId() {
        return devStatusId;
    }

    public void setDevStatusId(Long devStatusId) {
        this.devStatusId = devStatusId;
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

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }

    public Date getRptTime() {
        return rptTime;
    }

    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }

    public String getOprStyle() {
        return oprStyle;
    }

    public void setOprStyle(String oprStyle) {
        this.oprStyle = oprStyle;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }

    public String getLowTempThd() {
        return lowTempThd;
    }

    public void setLowTempThd(String lowTempThd) {
        this.lowTempThd = lowTempThd;
    }

    public String getHighTempShd() {
        return highTempShd;
    }

    public void setHighTempShd(String highTempShd) {
        this.highTempShd = highTempShd;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidityShd() {
        return humidityShd;
    }

    public void setHumidityShd(String humidityShd) {
        this.humidityShd = humidityShd;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getBatteryThd() {
        return batteryThd;
    }

    public void setBatteryThd(String batteryThd) {
        this.batteryThd = batteryThd;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getTiltThd() {
        return tiltThd;
    }

    public void setTiltThd(String tiltThd) {
        this.tiltThd = tiltThd;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public String getSignals() {
        return signals;
    }

    public void setSignals(String signals) {
        this.signals = signals;
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

    public String getAlarmSign() {
        return alarmSign;
    }

    public void setAlarmSign(String alarmSign) {
        this.alarmSign = alarmSign;
    }

    public String getDealSign() {
        return dealSign;
    }

    public void setDealSign(String dealSign) {
        this.dealSign = dealSign;
    }

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}
}