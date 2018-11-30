package com.ycnet.frms.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AccessConditionBean {
	//2017-10-13修改,整合AccessConditionBean和FacilityAccess
//	String userName;
	private String devImei;
	private String mobile;
	private String devName;
	private String bdate;
	private String edate;
//	String status;
	private String bdateEnd;
	private String edateEnd;
	
	private Long devId;//设施id
//	Long userId;//用户id
	
    private Long accessId;

    private Long userId;

    private String userName;

    private String devIds;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date validateTime;

    private String validateStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date accessTime;

    private String remark;
	
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getDevimei() {
//		return devimei;
//	}
//	public void setDevimei(String devimei) {
//		this.devimei = devimei;
//	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
//	public String getDevname() {
//		return devname;
//	}
//	public void setDevname(String devname) {
//		this.devname = devname;
//	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
	public Long getDevId() {
		return devId;
	}
	public void setDevId(Long devId) {
		this.devId = devId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getBdateEnd() {
		return bdateEnd;
	}
	public void setBdateEnd(String bdateEnd) {
		this.bdateEnd = bdateEnd;
	}
	public String getEdateEnd() {
		return edateEnd;
	}
	public void setEdateEnd(String edateEnd) {
		this.edateEnd = edateEnd;
	}
	public Long getAccessId() {
		return accessId;
	}
	public void setAccessId(Long accessId) {
		this.accessId = accessId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDevIds() {
		return devIds;
	}
	public void setDevIds(String devIds) {
		this.devIds = devIds;
	}
	public Date getValidateTime() {
		return validateTime;
	}
	public void setValidateTime(Date validateTime) {
		this.validateTime = validateTime;
	}
	public String getValidateStatus() {
		return validateStatus;
	}
	public void setValidateStatus(String validateStatus) {
		this.validateStatus = validateStatus;
	}
	public Date getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDevImei() {
		return devImei;
	}
	public void setDevImei(String devImei) {
		this.devImei = devImei;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	
	

}
