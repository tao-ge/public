/**   
 * @Package: com.ycnet.frms.vo.mobile.ocii 
 * @author: FL   
 * @date: 2018年10月11日 下午2:11:46 
 */
package com.ycnet.frms.vo.mobile.ocii;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassName: OciiFacilityBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author FL（作者）
 * @date 2018年10月11日 下午2:11:46
 */
public class OciiFacilityBean {

	private String areaName;

	private String longitude;

	private String latitude;

	private Long devId;

	private String devCode;

	private String devName;

	private String devType;

	private String devState;

	private BigDecimal baiduX;

	private BigDecimal baiduY;

	private String devAddr;

	private String devDesc;
	
	public String getAreaName() {
		return areaName==null?"":areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

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

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName==null?"":devName;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public BigDecimal getBaiduX() {
		return baiduX;
	}

	public void setBaiduX(BigDecimal baiduX) {
		this.baiduX = baiduX;
	}

	public BigDecimal getBaiduY() {
		return baiduY;
	}

	public void setBaiduY(BigDecimal baiduY) {
		this.baiduY = baiduY;
	}

	public String getDevAddr() {
		return devAddr==null?"":devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public String getDevDesc() {
		return devDesc==null?"":devDesc;
	}

	public void setDevDesc(String devDesc) {
		this.devDesc = devDesc;
	}

}
