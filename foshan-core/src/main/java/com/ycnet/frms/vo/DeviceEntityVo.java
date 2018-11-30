package com.ycnet.frms.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ycnet.frms.bean.FacilityEntity;


public class DeviceEntityVo{
	private Long codId;

    private String codCode;

    private String codName;

    private String codMac;

    private String codImei;

    private Long devId;

    private String codState;
    
    private Date rptTime;
    
    private String rptTimeSta;
    
    private String rptTimeEnd;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;

    private Long hardId;

    private Date lastHardTime;
    
    private String devNameRoom;//机房名称
    
    private String devCodeRoom;//机房编码
    
    private String devAddr;//设施地址
    
    private String devType;
    
    private BigDecimal baiduX;

    private BigDecimal baiduY;
    
    private DiscInfoEntityVo disc;//光交箱里的端子控制器状态
    
    private List<DiscInfoEntityVo> discList;
    
    private List<FacilityEntity> facilityList;
    
    private String lockWorkStatus;
    
    private String temp;
    
    private String battery;
    
    private String signals;
    
    private String tilt;
    
    private String lockOpen01;//锁1状态
    
    private String doorOpen01;//门1状态
    
    private String lockOpen02;//锁2状态
    
    private String doorOpen02;//门2状态
    
    private String workState;//中控器工作状态
    
	public Long getCodId() {
		return codId;
	}

	public void setCodId(Long codId) {
		this.codId = codId;
	}

	public String getCodCode() {
		return codCode;
	}

	public void setCodCode(String codCode) {
		this.codCode = codCode;
	}

	public String getCodName() {
		return codName;
	}

	public void setCodName(String codName) {
		this.codName = codName;
	}

	public String getCodMac() {
		return codMac;
	}

	public void setCodMac(String codMac) {
		this.codMac = codMac;
	}

	public String getCodImei() {
		return codImei;
	}

	public void setCodImei(String codImei) {
		this.codImei = codImei;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getCodState() {
		return codState;
	}

	public void setCodState(String codState) {
		this.codState = codState;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getHardId() {
		return hardId;
	}

	public void setHardId(Long hardId) {
		this.hardId = hardId;
	}

	public String getDevNameRoom() {
		return devNameRoom;
	}

	public void setDevNameRoom(String devNameRoom) {
		this.devNameRoom = devNameRoom.trim();
	}

	public List<DiscInfoEntityVo> getDiscList() {
		return discList;
	}

	public void setDiscList(List<DiscInfoEntityVo> discList) {
		this.discList = discList;
	}

	public String getDevCodeRoom() {
		return devCodeRoom;
	}

	public void setDevCodeRoom(String devCodeRoom) {
		this.devCodeRoom = devCodeRoom.trim();
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public List<FacilityEntity> getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(List<FacilityEntity> facilityList) {
		this.facilityList = facilityList;
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

	public DiscInfoEntityVo getDisc() {
		return disc;
	}

	public void setDisc(DiscInfoEntityVo disc) {
		this.disc = disc;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}



	public String getRptTimeSta() {
		return rptTimeSta;
	}

	public void setRptTimeSta(String rptTimeSta) {
		this.rptTimeSta = rptTimeSta;
	}

	public String getRptTimeEnd() {
		return rptTimeEnd;
	}

	public void setRptTimeEnd(String rptTimeEnd) {
		this.rptTimeEnd = rptTimeEnd;
	}

	public String getLockWorkStatus() {
		return lockWorkStatus;
	}

	public String getTemp() {
		return temp;
	}

	public String getBattery() {
		return battery;
	}

	public String getSignals() {
		return signals;
	}

	public String getTilt() {
		return tilt;
	}

	public void setLockWorkStatus(String lockWorkStatus) {
		this.lockWorkStatus = lockWorkStatus;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public void setSignals(String signals) {
		this.signals = signals;
	}

	public void setTilt(String tilt) {
		this.tilt = tilt;
	}

	public Date getRptTime() {
		return rptTime;
	}

	public void setRptTime(Date rptTime) {
		this.rptTime = rptTime;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getLockOpen01() {
		return lockOpen01;
	}

	public void setLockOpen01(String lockOpen01) {
		this.lockOpen01 = lockOpen01;
	}

	public String getDoorOpen01() {
		return doorOpen01;
	}

	public void setDoorOpen01(String doorOpen01) {
		this.doorOpen01 = doorOpen01;
	}

	public String getLockOpen02() {
		return lockOpen02;
	}

	public void setLockOpen02(String lockOpen02) {
		this.lockOpen02 = lockOpen02;
	}

	public String getDoorOpen02() {
		return doorOpen02;
	}

	public void setDoorOpen02(String doorOpen02) {
		this.doorOpen02 = doorOpen02;
	}

	public Date getLastHardTime() {
		return lastHardTime;
	}

	public void setLastHardTime(Date lastHardTime) {
		this.lastHardTime = lastHardTime;
	}
	
	
    
    
}
