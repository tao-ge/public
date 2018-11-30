package com.ycnet.frms.bean;

import java.util.Date;

public class Piping {
    private Long pipingId;

    private Long wellId;

    private Long spaceId;

    private Integer diameter;

    private Long subtubeCount;

    private Integer usedsubtubeCount;

    private Integer unusedsubtubeCount;
    
    private Long pipingSectId;

    private String isImport;

    private String pipingState;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;
    
    private Long orgId;

    public Long getPipingId() {
        return pipingId;
    }

    public void setPipingId(Long pipingId) {
        this.pipingId = pipingId;
    }

    public Long getWellId() {
        return wellId;
    }

    public void setWellId(Long wellId) {
        this.wellId = wellId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public Long getSubtubeCount() {
        return subtubeCount;
    }

    public void setSubtubeCount(Long subtubeCount) {
        this.subtubeCount = subtubeCount;
    }

    public Integer getUsedsubtubeCount() {
        return usedsubtubeCount;
    }

    public void setUsedsubtubeCount(Integer usedsubtubeCount) {
        this.usedsubtubeCount = usedsubtubeCount;
    }

    public Integer getUnusedsubtubeCount() {
        return unusedsubtubeCount;
    }

    public void setUnusedsubtubeCount(Integer unusedsubtubeCount) {
        this.unusedsubtubeCount = unusedsubtubeCount;
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport == null ? null : isImport.trim();
    }

    public String getPipingState() {
        return pipingState;
    }

    public void setPipingState(String pipingState) {
        this.pipingState = pipingState == null ? null : pipingState.trim();
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

	public Long getPipingSectId() {
		return pipingSectId;
	}

	public void setPipingSectId(Long pipingSectId) {
		this.pipingSectId = pipingSectId;
	}
    
    
}