package com.ycnet.frms.vo;


public class CableSectionCX {
	private Long sectId;

	private String sectCode;

	private String secName;

	private Long fiberNum;

	private Long devIdA;

	private Long devIdZ;
	
	private String devAName;

    private String devZName;


	private String sectState;// 光缆状态 0未核对 1正常 2新增 3修改 4删除

	private String sectDec;// 光缆描述

	private String remark; // 光缆备注

	public String getDevAName() {
		return devAName;
	}

	public void setDevAName(String devAName) {
		this.devAName = devAName;
	}

	public String getDevZName() {
		return devZName;
	}

	public void setDevZName(String devZName) {
		this.devZName = devZName;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public String getSectCode() {
		return sectCode;
	}

	public void setSectCode(String sectCode) {
		this.sectCode = sectCode;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
	}

	public Long getDevIdA() {
		return devIdA;
	}

	public void setDevIdA(Long devIdA) {
		this.devIdA = devIdA;
	}

	public Long getDevIdZ() {
		return devIdZ;
	}

	public void setDevIdZ(Long devIdZ) {
		this.devIdZ = devIdZ;
	}

	public String getSectState() {
		return sectState;
	}

	public void setSectState(String sectState) {
		this.sectState = sectState;
	}

	public String getSectDec() {
		return sectDec;
	}

	public void setSectDec(String sectDec) {
		this.sectDec = sectDec;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}