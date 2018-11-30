package com.ycnet.frms.vo;

import java.util.List;

public class WorkorderFiberdiscabindImgBean {
	
	private Long discId;
	
	private String  discName;
	
	private String discCode;
	
	private String side;
	
	private Long discRowNo;
	
	private Long discColNo;
	
	private Long portNum;
	
	private Long fiberDiscId;
	
	private String devName;
	
	private Long groupId;
	
	private String groupName;
	
	private String devCode;
	
	private Long devId;
	
	private String remark;
	
	private String codMac;//绑定中控器的MAC
	
	private String devData;//设备检测数据
	
	private String port01;//端子编码
	
	private String groupDesc;//分组描述
	
	private String devType;//设施类型
	
	private String discContrCode;//端子控制器编码
	
	/**
	 * @return the devType
	 */
	public String getDevType() {
		return devType;
	}

	/**
	 * @param devType the devType to set
	 */
	public void setDevType(String devType) {
		this.devType = devType;
	}

	/**
	 * @return the discContrCode
	 */
	public String getDiscContrCode() {
		return discContrCode;
	}

	/**
	 * @param discContrCode the discContrCode to set
	 */
	public void setDiscContrCode(String discContrCode) {
		this.discContrCode = discContrCode;
	}

	public String getDiscName() {
		return discName;
	}

	public String getDiscCode() {
		return discCode;
	}

	public String getSide() {
		return side;
	}

	public Long getDiscColNo() {
		return discColNo;
	}

	public void setDiscName(String discName) {
		this.discName = discName;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public void setDiscColNo(Long discColNo) {
		this.discColNo = discColNo;
	}

	public Long getPortNum() {
		return portNum;
	}

	public Long getFiberDiscId() {
		return fiberDiscId;
	}

	public void setPortNum(Long portNum) {
		this.portNum = portNum;
	}

	public void setFiberDiscId(Long fiberDiscId) {
		this.fiberDiscId = fiberDiscId;
	}

	public String getDevName() {
		return devName;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getDevCode() {
		return devCode;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Long getDiscRowNo() {
		return discRowNo;
	}

	public void setDiscRowNo(Long discRowNo) {
		this.discRowNo = discRowNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCodMac() {
		return codMac;
	}

	public void setCodMac(String codMac) {
		this.codMac = codMac;
	}

	public String getDevData() {
		return devData;
	}

	public void setDevData(String devData) {
		this.devData = devData;
	}

	public String getPort01() {
		return port01;
	}

	public void setPort01(String port01) {
		this.port01 = port01;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public Long getDiscId() {
		return discId;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}

}
