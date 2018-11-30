package com.ycnet.frms.vo.mobile;

public class FacilityCabinetMobile {

	private Long devId;

    private String devCode;

    private String devName;
    
    private Long otherDevId;

	public Long getDevId() {
		return devId;
	}

	public String getDevCode() {
		return devCode;
	}

	public String getDevName() {
		return devName;
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

	public Long getOtherDevId() {
		return otherDevId;
	}

	public void setOtherDevId(Long otherDevId) {
		this.otherDevId = otherDevId;
	}
}
