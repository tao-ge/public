package com.ycnet.frms.bean;

import java.math.BigDecimal;
import java.util.Date;

public class FiberdiscEntity {
    private Long fiberDiscId;

    private Long devId;

    private String side;

    private Long discRowNo;

    private String discCode;

    private Long discColNo;

    private String port01;

    private Long sectId;

    private Long fiberNum;

    private String remark;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private String isOccup;

    private String isSheath;

    private String isGlazed1;

    private String isGlazed2;

    private BigDecimal lightLen;

    private BigDecimal lightWane;

    private String detailsPort;

    public Long getFiberDiscId() {
        return fiberDiscId;
    }

    public void setFiberDiscId(Long fiberDiscId) {
        this.fiberDiscId = fiberDiscId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side == null ? null : side.trim();
    }

    public Long getDiscRowNo() {
        return discRowNo;
    }

    public void setDiscRowNo(Long discRowNo) {
        this.discRowNo = discRowNo;
    }

    public String getDiscCode() {
        return discCode;
    }

    public void setDiscCode(String discCode) {
        this.discCode = discCode == null ? null : discCode.trim();
    }

    public Long getDiscColNo() {
        return discColNo;
    }

    public void setDiscColNo(Long discColNo) {
        this.discColNo = discColNo;
    }

    public String getPort01() {
        return port01;
    }

    public void setPort01(String port01) {
        this.port01 = port01 == null ? null : port01.trim();
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public Long getFiberNum() {
        return fiberNum;
    }

    public void setFiberNum(Long fiberNum) {
        this.fiberNum = fiberNum;
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

    public String getIsOccup() {
        return isOccup;
    }

    public void setIsOccup(String isOccup) {
        this.isOccup = isOccup == null ? null : isOccup.trim();
    }

    public String getIsSheath() {
        return isSheath;
    }

    public void setIsSheath(String isSheath) {
        this.isSheath = isSheath == null ? null : isSheath.trim();
    }

    public String getIsGlazed1() {
        return isGlazed1;
    }

    public void setIsGlazed1(String isGlazed1) {
        this.isGlazed1 = isGlazed1 == null ? null : isGlazed1.trim();
    }

    public String getIsGlazed2() {
        return isGlazed2;
    }

    public void setIsGlazed2(String isGlazed2) {
        this.isGlazed2 = isGlazed2 == null ? null : isGlazed2.trim();
    }

    public BigDecimal getLightLen() {
        return lightLen;
    }

    public void setLightLen(BigDecimal lightLen) {
        this.lightLen = lightLen;
    }

    public BigDecimal getLightWane() {
        return lightWane;
    }

    public void setLightWane(BigDecimal lightWane) {
        this.lightWane = lightWane;
    }

    public String getDetailsPort() {
        return detailsPort;
    }

    public void setDetailsPort(String detailsPort) {
        this.detailsPort = detailsPort == null ? null : detailsPort.trim();
    }
}