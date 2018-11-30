package com.ycnet.frms.vo.mobile.ocii;

import java.util.List;

public class OcIdentifResult {
    private Long identId;

    private String identCode;

    private Integer portNum;

    private String identType;

    private String identState;

    private List<OcIdentifPortResult> list;

	public Long getIdentId() {
		return identId;
	}

	public void setIdentId(Long identId) {
		this.identId = identId;
	}

	public String getIdentCode() {
		return identCode;
	}

	public void setIdentCode(String identCode) {
		this.identCode = identCode;
	}

	public Integer getPortNum() {
		return portNum;
	}

	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}

	public String getIdentType() {
		return identType;
	}

	public void setIdentType(String identType) {
		this.identType = identType;
	}

	public String getIdentState() {
		return identState;
	}

	public void setIdentState(String identState) {
		this.identState = identState;
	}

	public List<OcIdentifPortResult> getList() {
		return list;
	}

	public void setList(List<OcIdentifPortResult> list) {
		this.list = list;
	}
    
    
}