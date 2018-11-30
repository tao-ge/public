package com.ycnet.frms.vo;

import java.util.Date;

import com.ycnet.frms.bean.DeviceAlarmEntity;

public class DeviceAlarmEntityBean extends DeviceAlarmEntity{

	private String devCode;
	
	private String devName;
	
	private String codMac;
	
	private String doorOpen01;
	
	private String lockOpen01;
	
	private String doorOpen02;
	
	private String lockOpen02;
	
	private String temp;
	
	private String battery;
	
	private String tilt;
	
	private String signals;
	
	 private Long codId;

    private String codCode;

    private String codName;

    private String codImei;

    private Long devId;

    private String codState;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;

    private Long hardId;

    private Date lastHardTime;
    
    private String devType;
    
    private String devAddr;
    
    private String areaName;
    
    private String lastModifyUserName;
    
    private String createUserName;
    
    private String hardVersion;
    
    private Date rptTime;

	public String getDevCode() {
		return devCode;
	}

	public String getDevName() {
		return devName;
	}

	public String getCodMac() {
		return codMac;
	}

	public String getTemp() {
		return temp;
	}

	public String getBattery() {
		return battery;
	}

	public String getTilt() {
		return tilt;
	}

	public String getSignals() {
		return signals;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setCodMac(String codMac) {
		this.codMac = codMac;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public void setTilt(String tilt) {
		this.tilt = tilt;
	}

	public void setSignals(String signals) {
		this.signals = signals;
	}

	public Long getCodId() {
		return codId;
	}

	public String getCodCode() {
		return codCode;
	}

	public String getCodName() {
		return codName;
	}

	public String getCodImei() {
		return codImei;
	}

	public Long getDevId() {
		return devId;
	}

	public String getCodState() {
		return codState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public Long getLastModifyUser() {
		return lastModifyUser;
	}

	public Long getOrgId() {
		return orgId;
	}

	public Long getHardId() {
		return hardId;
	}

	public String getDevType() {
		return devType;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public String getAreaName() {
		return areaName;
	}

	public String getLastModifyUserName() {
		return lastModifyUserName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCodId(Long codId) {
		this.codId = codId;
	}

	public void setCodCode(String codCode) {
		this.codCode = codCode;
	}

	public void setCodName(String codName) {
		this.codName = codName;
	}

	public void setCodImei(String codImei) {
		this.codImei = codImei;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setCodState(String codState) {
		this.codState = codState;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public void setLastModifyUser(Long lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public void setHardId(Long hardId) {
		this.hardId = hardId;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setLastModifyUserName(String lastModifyUserName) {
		this.lastModifyUserName = lastModifyUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getHardVersion() {
		return hardVersion;
	}

	public void setHardVersion(String hardVersion) {
		this.hardVersion = hardVersion;
	}

	public String getDoorOpen01() {
		return doorOpen01;
	}

	public void setDoorOpen01(String doorOpen01) {
		this.doorOpen01 = doorOpen01;
	}

	public String getLockOpen01() {
		return lockOpen01;
	}

	public void setLockOpen01(String lockOpen01) {
		this.lockOpen01 = lockOpen01;
	}

	public String getDoorOpen02() {
		return doorOpen02;
	}

	public void setDoorOpen02(String doorOpen02) {
		this.doorOpen02 = doorOpen02;
	}

	public String getLockOpen02() {
		return lockOpen02;
	}

	public void setLockOpen02(String lockOpen02) {
		this.lockOpen02 = lockOpen02;
	}

	public Date getRptTime() {
		return rptTime;
	}

	public void setRptTime(Date rptTime) {
		this.rptTime = rptTime;
	}

	public Date getLastHardTime() {
		return lastHardTime;
	}

	public void setLastHardTime(Date lastHardTime) {
		this.lastHardTime = lastHardTime;
	}
	
}
