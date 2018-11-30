package com.ycnet.frms.vo;

import java.math.BigDecimal;
import java.util.List;

import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.Facility;

public class FacilityBean extends Facility {
	
		private String areaName1;
		
		private String areaName2;
		
		private String devTypeName;
		private String pdevName;
		private String proIdName;//所属工程
		private String flagName;//有效标志
        private String surveyResultName;//核实标志
        private Integer facilityCount;
        
        private String checkTypeName;//核查标志
        
        private String checkUserName;//核查人
        
        private String isLocking = "0";//是否锁定 0否 1是
        
        private String valueName;//代码值名称
        
        private BigDecimal guGEX;//谷歌经度
		
        private BigDecimal guGEY;//谷歌纬度
        
        private String exsitDevice;//是否有绑定中控器
        
        private List<DeviceEntity> DeviceEntityList;
        
		public String getCheckUserName() {
			return checkUserName;
		}

		public String getValueName() {
			return valueName;
		}

		public void setValueName(String valueName) {
			this.valueName = valueName;
		}

		public void setCheckUserName(String checkUserName) {
			this.checkUserName = checkUserName;
		}

		public String getIsLocking() {
			return isLocking;
		}

		public void setIsLocking(String isLocking) {
			this.isLocking = isLocking;
		}

		public String getCheckTypeName() {
			return checkTypeName;
		}

		public void setCheckTypeName(String checkTypeName) {
			this.checkTypeName = checkTypeName;
		}

		public Integer getFacilityCount() {
			return facilityCount;
		}

		public void setFacilityCount(Integer facilityCount) {
			this.facilityCount = facilityCount;
		}

		public String getAreaName1() {
			return areaName1;
		}

		public void setAreaName1(String areaName1) {
			this.areaName1 = areaName1;
		}

		public String getAreaName2() {
			return areaName2;
		}

		public void setAreaName2(String areaName2) {
			this.areaName2 = areaName2;
		}

		public String getDevTypeName() {
			return devTypeName;
		}

		public void setDevTypeName(String devTypeName) {
			this.devTypeName = devTypeName;
		}

		public String getPdevName() {
			return pdevName;
		}

		public void setPdevName(String pdevName) {
			this.pdevName = pdevName;
		}

		public String getProIdName() {
			return proIdName;
		}

		public void setProIdName(String proIdName) {
			this.proIdName = proIdName;
		}

		public String getFlagName() {
			return flagName;
		}

		public void setFlagName(String flagName) {
			this.flagName = flagName;
		}

		public String getSurveyResultName() {
			return surveyResultName;
		}

		public void setSurveyResultName(String surveyResultName) {
			this.surveyResultName = surveyResultName;
		}

		public BigDecimal getGuGEX() {
			return guGEX;
		}

		public BigDecimal getGuGEY() {
			return guGEY;
		}

		public void setGuGEX(BigDecimal guGEX) {
			this.guGEX = guGEX;
		}

		public void setGuGEY(BigDecimal guGEY) {
			this.guGEY = guGEY;
		}

		public String getExsitDevice() {
			return exsitDevice;
		}

		public void setExsitDevice(String exsitDevice) {
			this.exsitDevice = exsitDevice;
		}

		public List<DeviceEntity> getDeviceEntityList() {
			return DeviceEntityList;
		}

		public void setDeviceEntityList(List<DeviceEntity> deviceEntityList) {
			DeviceEntityList = deviceEntityList;
		}

		
		
}
