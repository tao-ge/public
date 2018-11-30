package com.ycnet.frms.vo.mobile;

import java.util.Date;
import java.util.List;

import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.FacilityImgEntity;

public class FaciltyDeviceDtoBean {
	
	private Long devId;
	
	private String devCode;
	
	private String devName;
	
	private String devDesc;
	
	private	String devType;
	
	private	String devState;
	
	private	String  exsitDevice;
	
	private String baiduX;
	
	private String baiduY;
	
    private String longitude;

    private String latitude;
	
	private String devAddr;
	
	private String areaName;
	
	private Date lastModifyTime;
	
	private String areaCode2;//区域编码
	
	private String positionAreas;//返回区域的父级编码 格式:"省编码,市编码,区编码,镇编码"
	
	private List<DeviceEntityBean> DeviceEntityList;
	
	private List<FacilityImgEntity> imgList;//图片集合
	
	

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Long getDevId() {
		return devId;
	}

	public String getDevCode() {
		return devCode;
	}

	public String getDevName() {
		return devName;
	}

	public String getDevType() {
		return devType;
	}

	public String getExsitDevice() {
		return exsitDevice;
	}

	public List<DeviceEntityBean> getDeviceEntityList() {
		return DeviceEntityList;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public void setExsitDevice(String exsitDevice) {
		this.exsitDevice = exsitDevice;
	}

	public void setDeviceEntityList(List<DeviceEntityBean> deviceEntityList) {
		DeviceEntityList = deviceEntityList;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public String getBaiduX() {
		return baiduX;
	}

	public String getBaiduY() {
		return baiduY;
	}

	public void setBaiduX(String baiduX) {
		this.baiduX = baiduX;
	}

	public void setBaiduY(String baiduY) {
		this.baiduY = baiduY;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public List<FacilityImgEntity> getImgList() {
		return imgList;
	}

	public void setImgList(List<FacilityImgEntity> imgList) {
		this.imgList = imgList;
	}

	public String getAreaCode2() {
		return areaCode2;
	}

	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}

	public String getPositionAreas() {
		return positionAreas;
	}

	public void setPositionAreas(String positionAreas) {
		this.positionAreas = positionAreas;
	}

	public String getDevDesc() {
		return devDesc;
	}

	public void setDevDesc(String devDesc) {
		this.devDesc = devDesc;
	}
	
}
