package com.ycnet.frms.vo;

public class DifferentPortsBean {
	
	private String areaCode1;
	
	private String areaCode2;
	
	private int isNotOccup=0;//未占用端子数
	
	private int isOccup=0;
	
	private int portFreeNum=0;//上报空闲端子数
	
	private int portOccupyNum=0;//上报占用端子数
	
	private int portErrorNum=0;//上报差异数
	
	private Double occupyBat=0.0;//端口占用率
	
	private Double errorNumBat=0.0;//端口差异率
	
	private String areaName;
	
	private String devName;
	
	private String devType;
	
	private Long orgId;
	
	private Long devId;

	public String getAreaCode1() {
		return areaCode1;
	}

	public int getIsNotOccup() {
		return isNotOccup;
	}

	public int getIsOccup() {
		return isOccup;
	}

	public int getPortFreeNum() {
		return portFreeNum;
	}

	public int getPortOccupyNum() {
		return portOccupyNum;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public void setIsNotOccup(int isNotOccup) {
		this.isNotOccup = isNotOccup;
	}

	public void setIsOccup(int isOccup) {
		this.isOccup = isOccup;
	}

	public void setPortFreeNum(int portFreeNum) {
		this.portFreeNum = portFreeNum;
	}

	public void setPortOccupyNum(int portOccupyNum) {
		this.portOccupyNum = portOccupyNum;
	}

	public int getPortErrorNum() {
		return portErrorNum;
	}

	public void setPortErrorNum(int portErrorNum) {
		this.portErrorNum = portErrorNum;
	}

	public Double getOccupyBat() {
		return occupyBat;
	}

	public Double getErrorNumBat() {
		return errorNumBat;
	}

	public void setOccupyBat(Double occupyBat) {
		this.occupyBat = occupyBat;
	}

	public void setErrorNumBat(Double errorNumBat) {
		this.errorNumBat = errorNumBat;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getDevName() {
		return devName;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getAreaCode2() {
		return areaCode2;
	}

	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	
}
