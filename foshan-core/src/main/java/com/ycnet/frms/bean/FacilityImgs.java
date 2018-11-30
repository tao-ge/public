package com.ycnet.frms.bean;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ycnet.frms.vo.DeviceInfo;
import com.ycnet.frms.vo.mobile.DeviceMobile;

public class FacilityImgs {
	private Long devId;

	private String devCode;

	private String devName;

	private String devType;

	private String devModel;

	private String longitude;

	private String latitude;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date completeDate;

	private String devAddr;

	private String devDesc;

	private Integer fiberDiscNum;

	private Long pdevId;

	private String devImei;

	private String devMac;

	private String areaCode1;

	private String areaName1;

	private String areaCode2;

	private String site;

	private String room;

	private Long proId;

	private Long orgId;

	private String flag;

	private String surveyResult;

	private String imgType;

	private String codeA;

	private String codeZ;

	private BigDecimal baiduX;

	private BigDecimal baiduY;

	private String isTranslated;

	private Date createTime;

	private Long createUser;

	private Date lastModifyTime;

	private Long lastModifyUser;

	// private List<Imgs> imgs;
	private List<Imgs01> imgs01;

	private List<Imgs02> imgs02;

	// 是否为居前井
	private String isbefwell;

	// 设施几通
	private String devKind;

	// 前井信息
	// private List<Facility> frontWellList;

	private String devState;// 设施状态

	private String zgDevName;

	private String devMarking;

	//中控器信息
	
	private DeviceMobile device;

	public String getZgDevName() {
		return zgDevName;
	}

	public void setZgDevName(String zgDevName) {
		this.zgDevName = zgDevName;
	}

	public String getDevMarking() {
		return devMarking;
	}

	public void setDevMarking(String devMarking) {
		this.devMarking = devMarking;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public List<Imgs01> getImgs01() {
		return imgs01;
	}

	public void setImgs01(List<Imgs01> imgs01) {
		this.imgs01 = imgs01;
	}

	public List<Imgs02> getImgs02() {
		return imgs02;
	}

	public void setImgs02(List<Imgs02> imgs02) {
		this.imgs02 = imgs02;
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
		this.devName = devName;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevModel() {
		return devModel;
	}

	public void setDevModel(String devModel) {
		this.devModel = devModel;
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

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public String getDevDesc() {
		return devDesc;
	}

	public void setDevDesc(String devDesc) {
		this.devDesc = devDesc;
	}

	public Integer getFiberDiscNum() {
		return fiberDiscNum;
	}

	public void setFiberDiscNum(Integer fiberDiscNum) {
		this.fiberDiscNum = fiberDiscNum;
	}

	public Long getPdevId() {
		return pdevId;
	}

	public void setPdevId(Long pdevId) {
		this.pdevId = pdevId;
	}

	public String getDevImei() {
		return devImei;
	}

	public void setDevImei(String devImei) {
		this.devImei = devImei;
	}

	public String getDevMac() {
		return devMac;
	}

	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public String getAreaCode2() {
		return areaCode2;
	}

	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSurveyResult() {
		return surveyResult;
	}

	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getCodeA() {
		return codeA;
	}

	public void setCodeA(String codeA) {
		this.codeA = codeA;
	}

	public String getCodeZ() {
		return codeZ;
	}

	public void setCodeZ(String codeZ) {
		this.codeZ = codeZ;
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

	public String getIsTranslated() {
		return isTranslated;
	}

	public void setIsTranslated(String isTranslated) {
		this.isTranslated = isTranslated;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Long getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(Long lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public String getIsbefwell() {
		return isbefwell;
	}

	public void setIsbefwell(String isbefwell) {
		this.isbefwell = isbefwell;
	}

	public String getDevKind() {
		return devKind;
	}

	public void setDevKind(String devKind) {
		this.devKind = devKind;
	}

	public String getAreaName1() {
		return areaName1;
	}

	public void setAreaName1(String areaName1) {
		this.areaName1 = areaName1;
	}

	public DeviceMobile getDevice() {
		return device;
	}

	public void setDevice(DeviceMobile device) {
		this.device = device;
	}

}
