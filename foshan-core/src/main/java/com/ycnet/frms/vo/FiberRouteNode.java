package com.ycnet.frms.vo;

import java.io.Serializable;

public class FiberRouteNode implements Serializable{

	private Long devId;
	
	private String code;
	
	private Long sectId;
	
	private Long fiberNo;
	
	private String lineType;
	
	private String unknowPointName;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public Long getFiberNo() {
		return fiberNo;
	}

	public void setFiberNo(Long fiberNo) {
		this.fiberNo = fiberNo;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getUnknowPointName() {
		return unknowPointName;
	}

	public void setUnknowPointName(String unknowPointName) {
		this.unknowPointName = unknowPointName;
	}

	
	
}
