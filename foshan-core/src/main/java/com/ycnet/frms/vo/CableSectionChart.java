package com.ycnet.frms.vo;

import java.util.List;

public class CableSectionChart {
	private Long devId;
	
	private String devCode;
	
	private String devName;
	
	private String notoEnd;//未成端纤芯序号用，隔开
	
	private List<Group> groupList;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public String getNotoEnd() {
		return notoEnd;
	}

	public void setNotoEnd(String notoEnd) {
		this.notoEnd = notoEnd;
	}
	
	
	
}
