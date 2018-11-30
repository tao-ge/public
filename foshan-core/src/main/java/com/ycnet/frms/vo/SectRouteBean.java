package com.ycnet.frms.vo;

import java.util.Date;

public class SectRouteBean{

	private Long sectId;
	private Long logid;
	private Long userId;
	
	private String sectState;//光缆段状态 zhouyu 18/1/4
	
	private Integer sectStateall;
	
	
	public Integer getSectStateall() {
		return sectStateall;
	}

	public void setSectStateall(Integer sectStateall) {
		this.sectStateall = sectStateall;
	}

	public String getSectState() {
		return sectState;
	}

	public void setSectState(String sectState) {
		this.sectState = sectState;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLogid() {
		return logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}
    
	private Long fiberNum;
    
    private String devName;
    
    private String devName1;
    
    private String zdevName;
    
    private String adevName;

    private String secName;

    private Long adevId;
    
    private Long zdevId;
    
    private String side;
    
    private Long discNum;
    private String logcontent;
    private Date createTime;
    public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogType1() {
		return logType1;
	}

	public void setLogType1(String logType1) {
		this.logType1 = logType1;
	}

	private String logType;
    private String logType1;
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLogcontent() {
		return logcontent;
	}

	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}

	private Long routeId;
    private Long orgId;
    public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	private String areaCode1;
    
    
	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
	}

	public String getDevName1() {
		return devName1;
	}

	public void setDevName1(String devName1) {
		this.devName1 = devName1;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getZdevName() {
		return zdevName;
	}

	public void setZdevName(String zdevName) {
		this.zdevName = zdevName;
	}

	public String getAdevName() {
		return adevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
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

	public Long getDiscNum() {
		return discNum;
	}

	public void setDiscNum(Long discNum) {
		this.discNum = discNum;
	}
	
}
