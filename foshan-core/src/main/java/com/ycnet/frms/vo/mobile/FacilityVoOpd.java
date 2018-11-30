package com.ycnet.frms.vo.mobile;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FacilityVoOpd {

    private Long devId;

    private String devCode;

    private String devName;

    private String devType;

    private String longitude;

    private String latitude;

    private String devAddr;

    private String baiduX;
    
    private String baiduY;
    
    private String areaName;//汇聚区名称
    
    private String devState;//设施状态 0 未核对 1 正常 2 新增 3修改 4删除
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;//最后修改时间，空则用创建时间

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

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public String getBaiduX() {
		return baiduX;
	}

	public String getBaiduY() {
		return baiduY;
	}

	public String getAreaName() {
		return areaName;
	}

	public String getDevState() {
		return devState;
	}

	public Date getTime() {
		return time;
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

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public void setBaiduX(String baiduX) {
		this.baiduX = baiduX;
	}

	public void setBaiduY(String baiduY) {
		this.baiduY = baiduY;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public void setTime(Date time) {
		this.time = time;
	}
    
}
