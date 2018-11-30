package com.ycnet.frms.vo;

import java.math.BigDecimal;

public class DeviceFactilityEntity{//机柜

    private Long devId;

    private String devName;//机房名称
    
    private String devCode;//机房编码
    
    private String devAddr;//设施地址
    
    private String devType;
    
    private BigDecimal baiduX;

    private BigDecimal baiduY;
    
    private int disSum;//盘数
    
    private int deviceSum;//绑定数

	public Long getDevId() {
		return devId;
	}

	public String getDevName() {
		return devName;
	}

	public String getDevCode() {
		return devCode;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public String getDevType() {
		return devType;
	}

	public BigDecimal getBaiduX() {
		return baiduX;
	}

	public BigDecimal getBaiduY() {
		return baiduY;
	}

	public int getDisSum() {
		return disSum;
	}

	public int getDeviceSum() {
		return deviceSum;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public void setBaiduX(BigDecimal baiduX) {
		this.baiduX = baiduX;
	}

	public void setBaiduY(BigDecimal baiduY) {
		this.baiduY = baiduY;
	}

	public void setDisSum(int disSum) {
		this.disSum = disSum;
	}

	public void setDeviceSum(int deviceSum) {
		this.deviceSum = deviceSum;
	}
}
