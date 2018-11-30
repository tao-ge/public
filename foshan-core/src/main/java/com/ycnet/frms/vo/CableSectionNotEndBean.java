package com.ycnet.frms.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CableSectionNotEndBean {
	
	private String sectId;
	
	private String sectCode ;
	
	private String secName ;
	
	private Long fiberNum;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date createTime;
	
	private Long createUser;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date lastModifyTime;
	
	private Long lastModifyUser;
	
	private String adevName;
	
	private String zdevName;
	
	private String sectState;
	
	private String asurveyResult;
	
	private String zsurveyResult;
	
	private String isTerminatZ;
	
	private String isTerminatA;
	
	private Long devIdZ;
	
	private Long devIdA;
	
	private String aType;
	
	private String zType;
	
	private String adevCode;
	
	private String zdevCode;
	
	private String createUserName;
	
	private String modifyUserName;
	
	private Long orderDevIdA;
	
	private Long orderDevIdZ;
	
	private String areaCode1;

	public String getSectId() {
		return sectId;
	}

	public void setSectId(String sectId) {
		this.sectId = sectId;
	}

	public String getSectCode() {
		return sectCode;
	}

	public void setSectCode(String sectCode) {
		this.sectCode = sectCode;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
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

	public String getSectState() {
		return sectState;
	}

	public void setSectState(String sectState) {
		this.sectState = sectState;
	}

	public String getAsurveyResult() {
		return asurveyResult;
	}

	public void setAsurveyResult(String asurveyResult) {
		this.asurveyResult = asurveyResult;
	}

	public String getZsurveyResult() {
		return zsurveyResult;
	}

	public void setZsurveyResult(String zsurveyResult) {
		this.zsurveyResult = zsurveyResult;
	}

	public String getIsTerminatZ() {
		return isTerminatZ;
	}

	public void setIsTerminatZ(String isTerminatZ) {
		this.isTerminatZ = isTerminatZ;
	}

	public String getIsTerminatA() {
		return isTerminatA;
	}

	public void setIsTerminatA(String isTerminatA) {
		this.isTerminatA = isTerminatA;
	}

	public Long getDevIdZ() {
		return devIdZ;
	}

	public void setDevIdZ(Long devIdZ) {
		this.devIdZ = devIdZ;
	}

	public Long getDevIdA() {
		return devIdA;
	}

	public void setDevIdA(Long devIdA) {
		this.devIdA = devIdA;
	}

	public String getaType() {
		return aType;
	}

	public void setaType(String aType) {
		this.aType = aType;
	}

	public String getzType() {
		return zType;
	}

	public void setzType(String zType) {
		this.zType = zType;
	}

	public String getAdevCode() {
		return adevCode;
	}

	public void setAdevCode(String adevCode) {
		this.adevCode = adevCode;
	}

	public String getZdevCode() {
		return zdevCode;
	}

	public void setZdevCode(String zdevCode) {
		this.zdevCode = zdevCode;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public Long getOrderDevIdA() {
		return orderDevIdA;
	}

	public void setOrderDevIdA(Long orderDevIdA) {
		this.orderDevIdA = orderDevIdA;
	}

	public Long getOrderDevIdZ() {
		return orderDevIdZ;
	}

	public void setOrderDevIdZ(Long orderDevIdZ) {
		this.orderDevIdZ = orderDevIdZ;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	

}
