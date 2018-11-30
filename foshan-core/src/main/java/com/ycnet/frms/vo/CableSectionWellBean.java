package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Well;

public class CableSectionWellBean {

	private Long sectId;
	
	private String sectCode;
	
	private String secName;
	
	private String sectState;
	
	private int fiberNum;
	
	private List<Well> wellList;
	
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

	public List<Well> getWellList() {
		return wellList;
	}

	public void setWellList(List<Well> wellList) {
		this.wellList = wellList;
	}


	public void setFiberNum(int fiberNum) {
		this.fiberNum = fiberNum;
	}

	public String getSectState() {
		return sectState;
	}

	public int getFiberNum() {
		return fiberNum;
	}

	public void setSectState(String sectState) {
		this.sectState = sectState;
	}

	
}
