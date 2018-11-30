package com.ycnet.frms.vo.mobile;

import java.util.List;

import com.ycnet.frms.bean.DeviceDiscinfoEntity;

public class DiscinfoGroupMobile {
	
	private Long groupId;
	
	private String side;
	
	private String groupName;
	
	private String groupDesc;
	
	private Long discNum;
	
	private List<DeviceDiscinfoMobile> deviceDiscinfoList;

	public Long getGroupId() {
		return groupId;
	}

	public String getSide() {
		return side;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public Long getDiscNum() {
		return discNum;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public void setDiscNum(Long discNum) {
		this.discNum = discNum;
	}

	public List<DeviceDiscinfoMobile> getDeviceDiscinfoList() {
		return deviceDiscinfoList;
	}

	public void setDeviceDiscinfoList(List<DeviceDiscinfoMobile> deviceDiscinfoList) {
		this.deviceDiscinfoList = deviceDiscinfoList;
	}
	
}
