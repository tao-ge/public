package com.ycnet.frms.vo.mobile;

public class FacilityOdf {
	
	private Long devId;
	
	private String devCode;

	private String devName;

	private String devType;

	private String devState;//设施状态 0 未核对 1 正常 2 新增 3修改 4删除

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

	public String getDevState() {
		return devState;
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

	public void setDevState(String devState) {
		this.devState = devState;
	}
}
