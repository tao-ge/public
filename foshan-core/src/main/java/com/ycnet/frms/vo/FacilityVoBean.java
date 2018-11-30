package com.ycnet.frms.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FacilityVoBean {
	
	private Long devId;
	
	private String devType;
	
	private String devCode;
	
	private String devName;
	
	private String devState;
	
	private String isCoordinate;//0 没有经/纬度 1 有经纬度
	
	private String isDevImg;//0 没有图片,1 有图片
	
	private String longitude;
	
	private String latitude;
	
	private String areaCode1;
	
	private String devAddr;//详细地址
	
	private Long createUser; 
	
	private Long lastModifyUser; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  createTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastModifyTime; 
	
	private String createUserName;
	
	private String modifyUserName;

	public String getDevType() {
		return devType;
	}

	public String getDevCode() {
		return devCode;
	}

	public String getDevName() {
		return devName;
	}

	public String getDevState() {
		return devState;
	}

	public String getIsCoordinate() {
		return isCoordinate;
	}

	public String getIsDevImg() {
		return isDevImg;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public void setIsCoordinate(String isCoordinate) {
		this.isCoordinate = isCoordinate;
	}

	public void setIsDevImg(String isDevImg) {
		this.isDevImg = isDevImg;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public Long getLastModifyUser() {
		return lastModifyUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public void setLastModifyUser(Long lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

}
