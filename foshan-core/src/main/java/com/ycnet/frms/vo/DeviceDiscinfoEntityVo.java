package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.DeviceDiscinfoEntity;


public class DeviceDiscinfoEntityVo extends DeviceDiscinfoEntity{
	
	private String discName;
	
	private Long discPortNum;
	
	private String discCode;
	
	private Long discId;
	
	private List<FiberdiscVo> fiberList;

	public String getDiscName() {
		return discName;
	}


	public Long getDiscId() {
		return discId;
	}

	public void setDiscName(String discName) {
		this.discName = discName;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}


	public Long getDiscPortNum() {
		return discPortNum;
	}


	public void setDiscPortNum(Long discPortNum) {
		this.discPortNum = discPortNum;
	}


	public String getDiscCode() {
		return discCode;
	}


	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}


	public List<FiberdiscVo> getFiberList() {
		return fiberList;
	}


	public void setFiberList(List<FiberdiscVo> fiberList) {
		this.fiberList = fiberList;
	}
	
	
}
