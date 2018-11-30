package com.ycnet.frms.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PipelineSection {
    private Long pipesId;

    private String pipesName;

    private String pipesType;

    private Long fdevId;

    private Long bdevId;

    private Long fspaceId;

    private Long bspaceId;

    private String phyLen;

    private String mapLen;

    private String remark;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date createTime;

    private Long createUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date modifyTime;

    private Long modifyUser;

    private Long orgId;

    public Long getPipesId() {
        return pipesId;
    }

    public void setPipesId(Long pipesId) {
        this.pipesId = pipesId;
    }

    public String getPipesName() {
        return pipesName;
    }

    public void setPipesName(String pipesName) {
        this.pipesName = pipesName == null ? null : pipesName.trim();
    }

    public String getPipesType() {
        return pipesType;
    }

    public void setPipesType(String pipesType) {
        this.pipesType = pipesType == null ? null : pipesType.trim();
    }

    public Long getFdevId() {
        return fdevId;
    }

    public void setFdevId(Long fdevId) {
        this.fdevId = fdevId;
    }

    public Long getBdevId() {
        return bdevId;
    }

    public void setBdevId(Long bdevId) {
        this.bdevId = bdevId;
    }

    public Long getFspaceId() {
        return fspaceId;
    }

    public void setFspaceId(Long fspaceId) {
        this.fspaceId = fspaceId;
    }

    public Long getBspaceId() {
        return bspaceId;
    }

    public void setBspaceId(Long bspaceId) {
        this.bspaceId = bspaceId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}