package com.ycnet.frms.bean;

import java.util.Date;

public class Tdisc {
    private Long id;

    private String sectCode;

    private String side;

    private Integer discRowNo;

    private Integer discColNo;

    private Integer fiberNum;

    private String remark;

    private String devCode;

    private String port01;

    private String details;

    private Long devId;

    private Long sectId;

    private Long userId;

    private Long orgId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectCode() {
        return sectCode;
    }

    public void setSectCode(String sectCode) {
        this.sectCode = sectCode;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Integer getDiscRowNo() {
        return discRowNo;
    }

    public void setDiscRowNo(Integer discRowNo) {
        this.discRowNo = discRowNo;
    }

    public Integer getDiscColNo() {
        return discColNo;
    }

    public void setDiscColNo(Integer discColNo) {
        this.discColNo = discColNo;
    }

    public Integer getFiberNum() {
        return fiberNum;
    }

    public void setFiberNum(Integer fiberNum) {
        this.fiberNum = fiberNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getPort01() {
        return port01;
    }

    public void setPort01(String port01) {
        this.port01 = port01;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
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