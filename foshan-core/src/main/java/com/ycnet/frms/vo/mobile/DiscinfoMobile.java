package com.ycnet.frms.vo.mobile;

import java.util.List;

import com.ycnet.frms.vo.Group;

public class DiscinfoMobile {
	
	private String devName;
	
	private Long devId;
	
	private List<Group> glist;

	public Long getDevId() {
		return devId;
	}

	public List<Group> getGlist() {
		return glist;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setGlist(List<Group> glist) {
		this.glist = glist;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}
	
}
