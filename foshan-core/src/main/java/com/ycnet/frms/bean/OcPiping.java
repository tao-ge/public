package com.ycnet.frms.bean;

import java.util.Date;

public class OcPiping {
    private Long pipingId;

    private Long pipingSectId;

    private Short diameter;

    private Short subtubeCount;

    private Short usedsubtubeCount;

    private Short unusedsubtubeCount;

    private String isOccupy;

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

    public Long getPipingSectId() {
        return pipingSectId;
    }

    public void setPipingSectId(Long pipingSectId) {
        this.pipingSectId = pipingSectId;
    }

    public Short getDiameter() {
        return diameter;
    }

    public void setDiameter(Short diameter) {
        this.diameter = diameter;
    }

    public Short getSubtubeCount() {
        return subtubeCount;
    }

    public void setSubtubeCount(Short subtubeCount) {
        this.subtubeCount = subtubeCount;
    }

    public Short getUsedsubtubeCount() {
        return usedsubtubeCount;
    }

    public void setUsedsubtubeCount(Short usedsubtubeCount) {
        this.usedsubtubeCount = usedsubtubeCount;
    }

    public Short getUnusedsubtubeCount() {
        return unusedsubtubeCount;
    }

    public void setUnusedsubtubeCount(Short unusedsubtubeCount) {
        this.unusedsubtubeCount = unusedsubtubeCount;
    }

    public String getIsOccupy() {
        return isOccupy;
    }

    public void setIsOccupy(String isOccupy) {
        this.isOccupy = isOccupy == null ? null : isOccupy.trim();
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
}