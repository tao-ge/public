package com.ycnet.frms.vo;

public class WorkorderFiberdiscabindVo {

	private WorkorderFiberdiscabindVoA fiberA;
	
	private WorkorderFiberdiscabindVoZ fiberZ;
	
	private Long devId; //设施ID
	
	private String isBend = "0";//始末端
	
	private String devCode;
	
	private String devState;
	
	private String devName;
	
	private String devType;

	public WorkorderFiberdiscabindVoA getFiberA() {
		return fiberA;
	}

	public void setFiberA(WorkorderFiberdiscabindVoA fiberA) {
		this.fiberA = fiberA;
	}

	public WorkorderFiberdiscabindVoZ getFiberZ() {
		return fiberZ;
	}

	public void setFiberZ(WorkorderFiberdiscabindVoZ fiberZ) {
		this.fiberZ = fiberZ;
	}

	public Long getDevId() {
		return devId;
	}

	public String getIsBend() {
		return isBend;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setIsBend(String isBend) {
		this.isBend = isBend;
	}

	public String getDevCode() {
		return devCode;
	}

	public String getDevState() {
		return devState;
	}

	public String getDevName() {
		return devName;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	
	
	
}
