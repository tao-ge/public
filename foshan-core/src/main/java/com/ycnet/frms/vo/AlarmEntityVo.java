package com.ycnet.frms.vo;

import java.math.BigDecimal;
import java.util.Date;

	public class AlarmEntityVo {

	private Long alarmId;

    private Long lockStatusId;

    private Long devId;

    private Date alarmTime;

    private Date rptTime;

    private String alarmTypes;

    private String alarmContent;

    private String dealSign;

    private String dealSituation;

    private Date dealTime;

    private Long userId;

    private String userName;
    
    private String devCode;
    
    private String devName;
    
    private String devAddr;
    
    private BigDecimal baiduX;
    
    private BigDecimal baiduY;
    
    private Date createTime;
    
    private Long orgId;

	public Long getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Long alarmId) {
		this.alarmId = alarmId;
	}

	public Long getLockStatusId() {
		return lockStatusId;
	}

	public void setLockStatusId(Long lockStatusId) {
		this.lockStatusId = lockStatusId;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public Date getRptTime() {
		return rptTime;
	}

	public void setRptTime(Date rptTime) {
		this.rptTime = rptTime;
	}

	public String getAlarmTypes() {
		return alarmTypes;
	}

	public void setAlarmTypes(String alarmTypes) {
		this.alarmTypes = alarmTypes;
	}

	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	public String getDealSign() {
		return dealSign;
	}

	public void setDealSign(String dealSign) {
		this.dealSign = dealSign;
	}

	public String getDealSituation() {
		return dealSituation;
	}

	public void setDealSituation(String dealSituation) {
		this.dealSituation = dealSituation;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
    
    
}
