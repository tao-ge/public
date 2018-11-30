package com.ycnet.frms.bean;

import java.util.Date;

public class PipingCable {
    private Long subtubeRefSectId;

    private Long sectId;

    private Long wellId;

    private Long spaceId;

    private Long pipingId;

    private Long subtubeId;

    private Long pipingSectId;

    private String sectState;

    private String isImport;

    private Long checkfiberCount;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;

    public Long getSubtubeRefSectId() {
        return subtubeRefSectId;
    }

    public void setSubtubeRefSectId(Long subtubeRefSectId) {
        this.subtubeRefSectId = subtubeRefSectId;
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
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

    public Long getPipingId() {
        return pipingId;
    }

    public void setPipingId(Long pipingId) {
        this.pipingId = pipingId;
    }

    public Long getSubtubeId() {
        return subtubeId;
    }

    public void setSubtubeId(Long subtubeId) {
        this.subtubeId = subtubeId;
    }

    public Long getPipingSectId() {
        return pipingSectId;
    }

    public void setPipingSectId(Long pipingSectId) {
        this.pipingSectId = pipingSectId;
    }

    public String getSectState() {
        return sectState;
    }

    public void setSectState(String sectState) {
        this.sectState = sectState == null ? null : sectState.trim();
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport == null ? null : isImport.trim();
    }

    public Long getCheckfiberCount() {
        return checkfiberCount;
    }

    public void setCheckfiberCount(Long checkfiberCount) {
        this.checkfiberCount = checkfiberCount;
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