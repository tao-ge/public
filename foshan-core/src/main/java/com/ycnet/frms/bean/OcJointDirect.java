package com.ycnet.frms.bean;

import java.util.Date;

public class OcJointDirect {
    private Long jointId;

    private Long devId;

    private Long sectIdA;

    private Integer fiberStartA;

    private Integer fiberEndA;

    private Long sectIdZ;

    private Integer fiberStartZ;

    private Integer fiberEndZ;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;

    public Long getJointId() {
        return jointId;
    }

    public void setJointId(Long jointId) {
        this.jointId = jointId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Long getSectIdA() {
        return sectIdA;
    }

    public void setSectIdA(Long sectIdA) {
        this.sectIdA = sectIdA;
    }

    public Integer getFiberStartA() {
        return fiberStartA;
    }

    public void setFiberStartA(Integer fiberStartA) {
        this.fiberStartA = fiberStartA;
    }

    public Integer getFiberEndA() {
        return fiberEndA;
    }

    public void setFiberEndA(Integer fiberEndA) {
        this.fiberEndA = fiberEndA;
    }

    public Long getSectIdZ() {
        return sectIdZ;
    }

    public void setSectIdZ(Long sectIdZ) {
        this.sectIdZ = sectIdZ;
    }

    public Integer getFiberStartZ() {
        return fiberStartZ;
    }

    public void setFiberStartZ(Integer fiberStartZ) {
        this.fiberStartZ = fiberStartZ;
    }

    public Integer getFiberEndZ() {
        return fiberEndZ;
    }

    public void setFiberEndZ(Integer fiberEndZ) {
        this.fiberEndZ = fiberEndZ;
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