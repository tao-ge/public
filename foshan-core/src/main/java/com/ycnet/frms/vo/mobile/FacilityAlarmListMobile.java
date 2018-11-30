package com.ycnet.frms.vo.mobile;

import java.math.BigDecimal;
import java.util.List;

public class FacilityAlarmListMobile {


    private Long devId;

    private String devCode;
    
    private String devName;
    
    private String devAddr;
    
    private BigDecimal baiduX;
    
    private BigDecimal baiduY;
    
    private String longitude;//经度
    
    private String latitude;//纬度

    private List<AlarmMobile> alarmList;
    
    
    
	public List<AlarmMobile> getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(List<AlarmMobile> alarmList) {
		this.alarmList = alarmList;
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

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public BigDecimal getBaiduX() {
		return baiduX;
	}

	public void setBaiduX(BigDecimal baiduX) {
		this.baiduX = baiduX;
	}

	public BigDecimal getBaiduY() {
		return baiduY;
	}

	public void setBaiduY(BigDecimal baiduY) {
		this.baiduY = baiduY;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
  
    
}
