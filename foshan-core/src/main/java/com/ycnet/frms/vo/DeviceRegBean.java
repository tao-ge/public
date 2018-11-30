package com.ycnet.frms.vo;

import com.ycnet.frms.bean.DeviceReg;

/**
 * 新建bena类，实时监控
 * 
 * @author fl
 *
 */
public class DeviceRegBean extends DeviceReg {
	
	private String fdevName;//设施名称
	
	private String baiduX;
	
	private String baiduY;
	
	private String latitude;//纬度
	
	private String longitude;//经度

	public String getFdevName() {
		return fdevName;
	}

	public void setFdevName(String fdevName) {
		this.fdevName = fdevName;
	}

	public String getBaiduX() {
		return baiduX;
	}

	public void setBaiduX(String baiduX) {
		this.baiduX = baiduX;
	}

	public String getBaiduY() {
		return baiduY;
	}

	public void setBaiduY(String baiduY) {
		this.baiduY = baiduY;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	

}
