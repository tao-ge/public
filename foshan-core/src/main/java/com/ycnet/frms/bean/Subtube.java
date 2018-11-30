package com.ycnet.frms.bean;

import java.util.Date;

public class Subtube {
    private Long subtubeId;

    private String subtubeName;

    private Long pipingId;

    private Long colorId;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;

    public Long getSubtubeId() {
        return subtubeId;
    }

    public void setSubtubeId(Long subtubeId) {
        this.subtubeId = subtubeId;
    }

    public String getSubtubeName() {
        return subtubeName;
    }

    public void setSubtubeName(String subtubeName) {
        this.subtubeName = subtubeName == null ? null : subtubeName.trim();
    }

    public Long getPipingId() {
        return pipingId;
    }

    public void setPipingId(Long pipingId) {
        this.pipingId = pipingId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
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