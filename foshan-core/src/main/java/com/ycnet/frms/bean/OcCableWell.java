package com.ycnet.frms.bean;

import java.util.Date;

public class OcCableWell {
    private Long cableWellId;

    private Long sectId;

    private Long wellDevId;

    private String isWell;

    private Integer sectNum;

    private Integer sectPointNo;

    private Integer sectPointSeq;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;

    private String isRecogn;

    public Long getCableWellId() {
        return cableWellId;
    }

    public void setCableWellId(Long cableWellId) {
        this.cableWellId = cableWellId;
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public Long getWellDevId() {
        return wellDevId;
    }

    public void setWellDevId(Long wellDevId) {
        this.wellDevId = wellDevId;
    }

    public String getIsWell() {
        return isWell;
    }

    public void setIsWell(String isWell) {
        this.isWell = isWell == null ? null : isWell.trim();
    }

    public Integer getSectNum() {
        return sectNum;
    }

    public void setSectNum(Integer sectNum) {
        this.sectNum = sectNum;
    }

    public Integer getSectPointNo() {
        return sectPointNo;
    }

    public void setSectPointNo(Integer sectPointNo) {
        this.sectPointNo = sectPointNo;
    }

    public Integer getSectPointSeq() {
        return sectPointSeq;
    }

    public void setSectPointSeq(Integer sectPointSeq) {
        this.sectPointSeq = sectPointSeq;
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

    public String getIsRecogn() {
        return isRecogn;
    }

    public void setIsRecogn(String isRecogn) {
        this.isRecogn = isRecogn == null ? null : isRecogn.trim();
    }
}