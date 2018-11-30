package com.ycnet.frms.bean;

import java.util.Date;

public class WorkorderImplePlans {
    private Long plansId;

    private Long designroutesId;

    private Long designId;

    private Long lineId;

    private Long adevId;

    private Long afiberDiscId;

    private String portA;

    private String adevData;

    private Long zdevId;

    private Long zfiberDiscId;

    private String portZ;

    private String zdevData;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;

    private String isOccupySame;

    private String isImpleSame;

    private String impleBack;

    public Long getPlansId() {
        return plansId;
    }

    public void setPlansId(Long plansId) {
        this.plansId = plansId;
    }

    public Long getDesignroutesId() {
        return designroutesId;
    }

    public void setDesignroutesId(Long designroutesId) {
        this.designroutesId = designroutesId;
    }

    public Long getDesignId() {
        return designId;
    }

    public void setDesignId(Long designId) {
        this.designId = designId;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Long getAdevId() {
        return adevId;
    }

    public void setAdevId(Long adevId) {
        this.adevId = adevId;
    }

    public Long getAfiberDiscId() {
        return afiberDiscId;
    }

    public void setAfiberDiscId(Long afiberDiscId) {
        this.afiberDiscId = afiberDiscId;
    }

    public String getPortA() {
        return portA;
    }

    public void setPortA(String portA) {
        this.portA = portA == null ? null : portA.trim();
    }

    public String getAdevData() {
        return adevData;
    }

    public void setAdevData(String adevData) {
        this.adevData = adevData == null ? null : adevData.trim();
    }

    public Long getZdevId() {
        return zdevId;
    }

    public void setZdevId(Long zdevId) {
        this.zdevId = zdevId;
    }

    public Long getZfiberDiscId() {
        return zfiberDiscId;
    }

    public void setZfiberDiscId(Long zfiberDiscId) {
        this.zfiberDiscId = zfiberDiscId;
    }

    public String getPortZ() {
        return portZ;
    }

    public void setPortZ(String portZ) {
        this.portZ = portZ == null ? null : portZ.trim();
    }

    public String getZdevData() {
        return zdevData;
    }

    public void setZdevData(String zdevData) {
        this.zdevData = zdevData == null ? null : zdevData.trim();
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

    public String getIsOccupySame() {
        return isOccupySame;
    }

    public void setIsOccupySame(String isOccupySame) {
        this.isOccupySame = isOccupySame == null ? null : isOccupySame.trim();
    }

    public String getIsImpleSame() {
        return isImpleSame;
    }

    public void setIsImpleSame(String isImpleSame) {
        this.isImpleSame = isImpleSame == null ? null : isImpleSame.trim();
    }

    public String getImpleBack() {
        return impleBack;
    }

    public void setImpleBack(String impleBack) {
        this.impleBack = impleBack == null ? null : impleBack.trim();
    }
}