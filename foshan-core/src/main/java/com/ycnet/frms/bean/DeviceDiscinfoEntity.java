package com.ycnet.frms.bean;

import java.util.Date;

public class DeviceDiscinfoEntity {
	private Long discId;

    private Long codId;

    private String discContrCode;

    private String discContrState;

    private Long devId;

    private Long groupId;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private String discSeq;

    private Integer portNum;

    private Long portOccupyNum;

    private Long portFreeNum;

    private Long portErrorNum;

    private Date lastReportTime;

    private String lastReportData;

    private Long orgId;
    
    private String discContrId;//检测板编码【不含顺序号】
    
    
    
    public String getDiscContrId() {
		return discContrId;
	}

	public void setDiscContrId(String discContrId) {
		this.discContrId = discContrId;
	}

	public Long getDiscId() {
        return discId;
    }

    public void setDiscId(Long discId) {
        this.discId = discId;
    }

    public Long getCodId() {
        return codId;
    }

    public void setCodId(Long codId) {
        this.codId = codId;
    }

    public String getDiscContrCode() {
        return discContrCode;
    }

    public void setDiscContrCode(String discContrCode) {
        this.discContrCode = discContrCode == null ? null : discContrCode.trim();
    }

    public String getDiscContrState() {
        return discContrState;
    }

    public void setDiscContrState(String discContrState) {
        this.discContrState = discContrState == null ? null : discContrState.trim();
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public String getDiscSeq() {
        return discSeq;
    }

    public void setDiscSeq(String discSeq) {
        this.discSeq = discSeq == null ? null : discSeq.trim();
    }

    public Integer getPortNum() {
        return portNum;
    }

    public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }

    public Long getPortOccupyNum() {
        return portOccupyNum;
    }

    public void setPortOccupyNum(Long portOccupyNum) {
        this.portOccupyNum = portOccupyNum;
    }

    public Long getPortFreeNum() {
        return portFreeNum;
    }

    public void setPortFreeNum(Long portFreeNum) {
        this.portFreeNum = portFreeNum;
    }

    public Long getPortErrorNum() {
        return portErrorNum;
    }

    public void setPortErrorNum(Long portErrorNum) {
        this.portErrorNum = portErrorNum;
    }

    public Date getLastReportTime() {
        return lastReportTime;
    }

    public void setLastReportTime(Date lastReportTime) {
        this.lastReportTime = lastReportTime;
    }

    public String getLastReportData() {
        return lastReportData;
    }

    public void setLastReportData(String lastReportData) {
        this.lastReportData = lastReportData == null ? null : lastReportData.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}