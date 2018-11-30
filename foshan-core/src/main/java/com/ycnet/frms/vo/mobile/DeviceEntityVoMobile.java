package com.ycnet.frms.vo.mobile;

import java.math.BigDecimal;
import java.util.List;

import com.ycnet.frms.vo.DeviceFactilityEntity;


public class DeviceEntityVoMobile{
		
		private Long codId;

	    private String codCode;

	    private String codName;

	    private String codMac;

	    private Long devId;

	    private String devName;//机房名称
	    
	    private String devCode;//机房编码
	    
	    private String devAddr;//设施地址
	    
	    private String devType;
	    
	    private BigDecimal baiduX;

	    private BigDecimal baiduY;
	    
	    private List<DeviceFactilityEntity> deviceFactilityList;//机柜列表

		public Long getCodId() {
			return codId;
		}

		public String getCodCode() {
			return codCode;
		}

		public String getCodName() {
			return codName;
		}

		public String getCodMac() {
			return codMac;
		}

		public Long getDevId() {
			return devId;
		}

		public String getDevName() {
			return devName;
		}

		public String getDevCode() {
			return devCode;
		}

		public String getDevAddr() {
			return devAddr;
		}

		public String getDevType() {
			return devType;
		}

		public BigDecimal getBaiduX() {
			return baiduX;
		}

		public BigDecimal getBaiduY() {
			return baiduY;
		}

		public List<DeviceFactilityEntity> getDeviceFactilityList() {
			return deviceFactilityList;
		}

		public void setCodId(Long codId) {
			this.codId = codId;
		}

		public void setCodCode(String codCode) {
			this.codCode = codCode;
		}

		public void setCodName(String codName) {
			this.codName = codName;
		}

		public void setCodMac(String codMac) {
			this.codMac = codMac;
		}

		public void setDevId(Long devId) {
			this.devId = devId;
		}

		public void setDevName(String devName) {
			this.devName = devName;
		}

		public void setDevCode(String devCode) {
			this.devCode = devCode;
		}

		public void setDevAddr(String devAddr) {
			this.devAddr = devAddr;
		}

		public void setDevType(String devType) {
			this.devType = devType;
		}

		public void setBaiduX(BigDecimal baiduX) {
			this.baiduX = baiduX;
		}

		public void setBaiduY(BigDecimal baiduY) {
			this.baiduY = baiduY;
		}

		public void setDeviceFactilityList(List<DeviceFactilityEntity> deviceFactilityList) {
			this.deviceFactilityList = deviceFactilityList;
		}
}
