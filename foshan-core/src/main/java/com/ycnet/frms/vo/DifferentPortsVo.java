package com.ycnet.frms.vo;

import java.util.Date;

public class DifferentPortsVo {
	
    private Long dataId;

    private String dataType;

    private Date createTime;

    private String areaCode;

    private Long orgId;
	
	private String areaCode1;
	
	private String areaCode2;
	
	private String isNotOccup="0";//未占用端子数
	
	private String isOccup="0";
	
	private String portFreeNum="0";//上报空闲端子数
	
	private String portOccupyNum="0";//上报占用端子数
	
	private String portErrorNum="0";//上报差异数
	
	private String occupyBat="0.0";//端口占用率
	
	private String errorNumBat="0.0";//端口差异率
	
	private String areaName;
	
	private String devName;
	
	private String devType;
	
	private String devTypeName;
	
	private Long devId;
	
	private String dataContent;//数据
	
	public void setIsNotOccup(String isNotOccup) {
		this.isNotOccup = getdataValues(1);
	}

	public void setIsOccup(String isOccup) {
		this.isOccup = getdataValues(2);
	}

	public void setPortFreeNum(String portFreeNum) {
		this.portFreeNum = getdataValues(4);
	}

	public void setPortOccupyNum(String portOccupyNum) {
		this.portOccupyNum =getdataValues(5);
	}

	public void setOccupyBat(String occupyBat) {
		this.occupyBat = getdataValues(3);
	}

	public void setErrorNumBat(String errorNumBat) {
		this.errorNumBat = getdataValues(7);
	}

	public void setPortErrorNum(String portErrorNum) {
		this.portErrorNum = getdataValues(6);
	}


	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
		
		this.isNotOccup = getdataValues(1);

		this.isOccup = getdataValues(2);
	
		this.portFreeNum = getdataValues(4);
	
		this.portOccupyNum = getdataValues(5);
	
		this.portErrorNum = getdataValues(6);

		this.occupyBat = getdataValues(3);

		this.errorNumBat = getdataValues(7);
		
	}
	
	/**
	 * 赋值
	* @Title: getdataValues 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param row
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月26日 上午10:57:31 
	* @version V1.0
	 */
	private String getdataValues(Integer row) {
		if(dataContent==null || "".equals(dataContent)) {
			return "0";
		}
		String [] dataContents = dataContent.split(",");
		String returnValue = "0";
		for(int i=1;i<=dataContents.length;i++) {
			if(row == i) {
				returnValue = dataContents[i-1];
			}
		}
		return returnValue;
	}

	public Long getDataId() {
		return dataId;
	}

	public String getDataType() {
		return dataType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public Long getOrgId() {
		return orgId;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public String getAreaCode2() {
		return areaCode2;
	}

	public String getIsNotOccup() {
		return isNotOccup;
	}

	public String getIsOccup() {
		return isOccup;
	}

	public String getPortFreeNum() {
		return portFreeNum;
	}

	public String getPortOccupyNum() {
		return portOccupyNum;
	}

	public String getPortErrorNum() {
		return portErrorNum;
	}

	public String getOccupyBat() {
		return occupyBat;
	}

	public String getErrorNumBat() {
		return errorNumBat;
	}

	public String getAreaName() {
		return areaName;
	}

	public String getDevName() {
		return devName;
	}

	public String getDevType() {
		return devType;
	}

	public String getDataContent() {
		return dataContent;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevTypeName() {
		return devTypeName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Long getDevId() {
		return devId;
	}
	
}
