package com.ycnet.frms.bean;

import java.util.Date;

public class PipingSection {
    private Long pipingSectId;

    private String pipingName;

    private String pipingSectType;

    private String isImport;

    private String phyLen;

    private String mapLen;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;
    
    private String zgPipingName;//资管管道段名称
    
    private String remark;//备注信息
    
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getZgPipingName() {
		return zgPipingName;
	}

	public void setZgPipingName(String zgPipingName) {
		this.zgPipingName = zgPipingName;
	}

	public Long getPipingSectId() {
        return pipingSectId;
    }

    public void setPipingSectId(Long pipingSectId) {
        this.pipingSectId = pipingSectId;
    }

    public String getPipingName() {
        return pipingName;
    }

    public void setPipingName(String pipingName) {
        this.pipingName = pipingName == null ? null : pipingName.trim();
    }

    public String getPipingSectType() {
        return pipingSectType;
    }

    public void setPipingSectType(String pipingSectType) {
        this.pipingSectType = pipingSectType == null ? null : pipingSectType.trim();
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport == null ? null : isImport.trim();
    }

    public String getPhyLen() {
        return phyLen;
    }

    public void setPhyLen(String phyLen) {
        this.phyLen = phyLen == null ? null : phyLen.trim();
    }

    public String getMapLen() {
        return mapLen;
    }

    public void setMapLen(String mapLen) {
        this.mapLen = mapLen == null ? null : mapLen.trim();
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
}