package com.ycnet.frms.vo;

public class Port1 {
	
	private Long fiberDiscId;
	
	private int discColNo;
	
	private String port01;
	
	private int fiberNum;
	
	private String isOccup;
	
	private Long sectId;


	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public int getDiscColNo() {
		return discColNo;
	}

	public void setDiscColNo(int discColNo) {
		this.discColNo = discColNo;
	}

	public String getPort01() {
		return port01;
	}

	public void setPort01(String port01) {
		this.port01 = port01;
	}

	public int getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(int fiberNum) {
		this.fiberNum = fiberNum;
	}

	public Long getFiberDiscId() {
		return fiberDiscId;
	}

	public void setFiberDiscId(Long fiberDiscId) {
		this.fiberDiscId = fiberDiscId;
	}

	public String getIsOccup() {
		return isOccup;
	}

	public void setIsOccup(String isOccup) {
		this.isOccup = isOccup;
	}
	
	
}
