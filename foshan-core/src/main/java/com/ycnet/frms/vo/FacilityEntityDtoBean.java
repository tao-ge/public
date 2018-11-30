package com.ycnet.frms.vo;

public class FacilityEntityDtoBean {
	private Long devId;

    private String devName;
    
    private Integer fiberDiscNum;

	public Long getDevId() {
		return devId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Integer getFiberDiscNum() {
		return fiberDiscNum;
	}

	public void setFiberDiscNum(Integer fiberDiscNum) {
		this.fiberDiscNum = fiberDiscNum;
	}
    
}
