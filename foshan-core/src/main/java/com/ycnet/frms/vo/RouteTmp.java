package com.ycnet.frms.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ycnet.frms.bean.Route;

public class RouteTmp{
	
	private Long routeId;
	
	private Date createTime;
	
	private String createTime1;
	
	private String adevName;
	
	private String zdevName;
	
	private String routeName;

	private String routeText;
	
	private String zAreaCode;
	
	private String aotherName;
	
	private String zotherName;
	
	private Long adevId;
	
	private Long zdevId;
	
	private String acode;
	
	private String zcode;
	
	private Long orgId;

	private String devType;
	
	private String areaCode1;

	private String 	isSameDistrict;//是否同区    0是  1否    -
	
	private String areaCode0;
	
	private String areaCode2;
	
	
	private String transmissionSection;//传输段
	
	private String transmissionSystem;//传输系统
	
	private Integer	isFG = 0;//是否分光器    0否  1是    -
	
	
	
	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public String getZcode() {
		return zcode;
	}

	public void setZcode(String zcode) {
		this.zcode = zcode;
	}

	public Integer getIsFG() {
		return isFG;
	}

	public void setIsFG(Integer isFG) {
		this.isFG = isFG;
	}

	public Long getAdevId() {
		return adevId;
	}

	public void setAdevId(Long adevId) {
		this.adevId = adevId;
	}

	public Long getZdevId() {
		return zdevId;
	}

	public void setZdevId(Long zdevId) {
		this.zdevId = zdevId;
	}

	public String getTransmissionSection() {
		return transmissionSection;
	}

	public void setTransmissionSection(String transmissionSection) {
		this.transmissionSection = transmissionSection;
	}

	public String getTransmissionSystem() {
		return transmissionSystem;
	}

	public void setTransmissionSystem(String transmissionSystem) {
		this.transmissionSystem = transmissionSystem;
	}

	public String getAreaCode0() {
		return areaCode0;
	}

	public void setAreaCode0(String areaCode0) {
		this.areaCode0 = areaCode0;
	}

	public String getAreaCode2() {
		return areaCode2;
	}

	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}

	public String getIsSameDistrict() {
		return isSameDistrict;
	}

	public void setIsSameDistrict(String isSameDistrict) {
		this.isSameDistrict = isSameDistrict;
	}

	public String getAotherName() {
		return aotherName;
	}

	public void setAotherName(String aotherName) {
		this.aotherName = aotherName;
	}

	public String getZotherName() {
		return zotherName;
	}

	public void setZotherName(String zotherName) {
		this.zotherName = zotherName;
	}

	public String getzAreaCode() {
		return zAreaCode;
	}

	public void setzAreaCode(String zAreaCode) {
		this.zAreaCode = zAreaCode;
	}

	public String getCreateTime1() {
		return createTime1;
	}

	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getAdevName() {
		return adevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}

	public String getZdevName() {
		return zdevName;
	}

	public void setZdevName(String zdevName) {
		this.zdevName = zdevName;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteText() {
		return routeText;
	}

	public void setRouteText(String routeText) {
		this.routeText = routeText;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}
	
	


	
}