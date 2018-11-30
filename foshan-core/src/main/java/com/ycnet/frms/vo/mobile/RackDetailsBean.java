package com.ycnet.frms.vo.mobile;

import java.util.List;

public class RackDetailsBean {
	
	private List<DeviceMobile> deviceMobileList;
	
	private List<DiscinfoGroupMobile> discinfoGroupMobileList;

	public List<DeviceMobile> getDeviceMobileList() {
		return deviceMobileList;
	}

	public List<DiscinfoGroupMobile> getDiscinfoGroupMobileList() {
		return discinfoGroupMobileList;
	}

	public void setDeviceMobileList(List<DeviceMobile> deviceMobileList) {
		this.deviceMobileList = deviceMobileList;
	}

	public void setDiscinfoGroupMobileList(List<DiscinfoGroupMobile> discinfoGroupMobileList) {
		this.discinfoGroupMobileList = discinfoGroupMobileList;
	}
}
