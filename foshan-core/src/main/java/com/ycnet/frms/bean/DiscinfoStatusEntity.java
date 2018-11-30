package com.ycnet.frms.bean;

import java.util.Date;

public class DiscinfoStatusEntity {
    private Long discStatusId;

    private Long codId;

    private Long discId;

    private Long devId;

    private Long groupId;

    private Long upPortNum;

    private Date upTime;
    
    private Date createTime;

    private String upData;

    private Long orgId;
    
    public Long getDiscStatusId() {
        return discStatusId;
    }

    public void setDiscStatusId(Long discStatusId) {
        this.discStatusId = discStatusId;
    }

    public Long getCodId() {
        return codId;
    }

    public void setCodId(Long codId) {
        this.codId = codId;
    }

    public Long getDiscId() {
        return discId;
    }

    public void setDiscId(Long discId) {
        this.discId = discId;
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

    public Long getUpPortNum() {
        return upPortNum;
    }

    public void setUpPortNum(Long upPortNum) {
        this.upPortNum = upPortNum;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getUpData() {
        return upData;
    }

    public void setUpData(String upData) {
        this.upData = upData == null ? null : upData.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}