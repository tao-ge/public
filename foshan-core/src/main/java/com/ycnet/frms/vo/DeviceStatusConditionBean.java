package com.ycnet.frms.vo;

public class DeviceStatusConditionBean {
	
    private Long devStatusId;
    
    private Long devId;

	private String devCode;

    private String devName;

    private String startTime;

    private String endTime;

    private String oprStyle;

    private String alarmSign;
    
    private String lockStatus;
    
    private String doorStatus;
    
    private String orgId;
    
    private String curStatus;
    
    private Integer alarmType;
    
    private String fName;//所属设施名称

    public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public Long getDevStatusId() {
		return devStatusId;
	}

	public void setDevStatusId(Long devStatusId) {
		this.devStatusId = devStatusId;
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

	public String getOprStyle() {
		return oprStyle;
	}

	public void setOprStyle(String oprStyle) {
		this.oprStyle = oprStyle;
	}

	public String getAlarmSign() {
		return alarmSign;
	}

	public void setAlarmSign(String alarmSign) {
		this.alarmSign = alarmSign;
	}

	public String getCurStatus() {
		return curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

    
}