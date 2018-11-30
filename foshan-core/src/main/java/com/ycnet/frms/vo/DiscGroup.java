package com.ycnet.frms.vo;

import java.util.List;

public class DiscGroup {
	private Long devId;
	
	private String devName;
	
	private String devCode;
	
	private String devState;
	
	private Long sectId;
	
	private List<Group1> groups;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public List<Group1> getGroups() {
		return groups;
	}

	public void setGroups(List<Group1> groups) {
		this.groups = groups;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}
	
	
	
}
