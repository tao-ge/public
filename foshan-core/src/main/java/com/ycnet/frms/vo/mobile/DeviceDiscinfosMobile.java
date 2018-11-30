package com.ycnet.frms.vo.mobile;

import java.util.Date;
import java.util.List;

	public class DeviceDiscinfosMobile {

	private Long codId;

    private Long devId;

    private String discContrId;// 检测板ID
    
    private List<DeviceDiscinfos> deviceDiscinfoList;

	public Long getCodId() {
		return codId;
	}

	public Long getDevId() {
		return devId;
	}


	public void setCodId(Long codId) {
		this.codId = codId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public List<DeviceDiscinfos> getDeviceDiscinfoList() {
		return deviceDiscinfoList;
	}

	public void setDeviceDiscinfoList(List<DeviceDiscinfos> deviceDiscinfoList) {
		this.deviceDiscinfoList = deviceDiscinfoList;
	}

	public String getDiscContrId() {
		return discContrId;
	}

	public void setDiscContrId(String discContrId) {
		this.discContrId = discContrId;
	}

	
}
