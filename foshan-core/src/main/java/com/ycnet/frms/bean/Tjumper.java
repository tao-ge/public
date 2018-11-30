package com.ycnet.frms.bean;

import java.util.Date;

public class Tjumper {
    private Long id;

    private String acode;

    private String zcode;

    private String unknownPointName;

    private String srvName;

    private String devCode;

    private String details;

    private Long adevId;

    private Long zdevId;

    private Long userId;

    private Long orgId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    public String getZcode() {
        return zcode;
    }

    public void setZcode(String zcode) {
        this.zcode = zcode;
    }

    public String getUnknownPointName() {
        return unknownPointName;
    }

    public void setUnknownPointName(String unknownPointName) {
        this.unknownPointName = unknownPointName;
    }

    public String getSrvName() {
        return srvName;
    }

    public void setSrvName(String srvName) {
        this.srvName = srvName;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getAdevId() {
        return adevId;
    }

    public void setAdevId(Long adevId) {
        this.adevId = adevId;
    }

    public Long getZdevId() {
        return zdevId;
    }

    public void setZdevId(Long zdevId) {
        this.zdevId = zdevId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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