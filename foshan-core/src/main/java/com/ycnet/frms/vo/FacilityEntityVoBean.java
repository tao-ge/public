package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.FacilityEntity;

public class FacilityEntityVoBean extends FacilityEntity{
	
	private Integer exsitDevice;
	
	private List<DeviceEntity> DeviceEntityList;

	public List<DeviceEntity> getDeviceEntityList() {
		return DeviceEntityList;
	}

	public void setDeviceEntityList(List<DeviceEntity> deviceEntityList) {
		DeviceEntityList = deviceEntityList;
	}

	public Integer getExsitDevice() {
		return exsitDevice;
	}

	public void setExsitDevice(Integer exsitDevice) {
		this.exsitDevice = exsitDevice;
	}
}
