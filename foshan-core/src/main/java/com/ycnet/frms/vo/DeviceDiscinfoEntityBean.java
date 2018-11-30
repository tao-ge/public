package com.ycnet.frms.vo;

import java.util.Date;
import java.util.List;

import com.ycnet.frms.bean.DeviceDiscinfoEntity;

public class DeviceDiscinfoEntityBean extends DeviceDiscinfoEntity{
	
	private String devName;
	
	private String devCode;
	
	private String areaCode;
	
	private String devTypeName;
	
	private String groupName;
	
	private String discName;
	
	private String side;
	
	private Integer discPortNum;
	
	private Integer lastReportDataSize;
	
	private List<String> lastReportDataList;
	
	private String startTime;
	
	private String endTime;
	
	private String discCode;
	
	private Long fibPortNum;//普查端子总数
	
	private Long fibPortFreeNum;//普查端子空闲数
	
	private Long fibPortOccupyNum;//普查端子占用数
	
	private String codCode;
	
	public String getDevName() {
		return devName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDiscName() {
		return discName;
	}

	public void setDiscName(String discName) {
		this.discName = discName;
	}

	public Integer getLastReportDataSize() {
		return lastReportDataSize;
	}

	public void setLastReportDataSize(Integer lastReportDataSize) {
		this.lastReportDataSize = lastReportDataSize;
	}

	public List<String> getLastReportDataList() {
		return lastReportDataList;
	}

	public void setLastReportDataList(List<String> lastReportDataList) {
		this.lastReportDataList = lastReportDataList;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Integer getDiscPortNum() {
		return discPortNum;
	}

	public void setDiscPortNum(Integer discPortNum) {
		this.discPortNum = discPortNum;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public Long getFibPortNum() {
		return fibPortNum;
	}

	public void setFibPortNum(Long fibPortNum) {
		this.fibPortNum = fibPortNum;
	}

	public Long getFibPortFreeNum() {
		return fibPortFreeNum;
	}

	public void setFibPortFreeNum(Long fibPortFreeNum) {
		this.fibPortFreeNum = fibPortFreeNum;
	}

	public Long getFibPortOccupyNum() {
		return fibPortOccupyNum;
	}

	public void setFibPortOccupyNum(Long fibPortOccupyNum) {
		this.fibPortOccupyNum = fibPortOccupyNum;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getDevTypeName() {
		return devTypeName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public String getCodCode() {
		return codCode;
	}

	public void setCodCode(String codCode) {
		this.codCode = codCode;
	}

	
	
}