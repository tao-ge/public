package com.ycnet.frms.bean;

import java.util.Date;

public class TempLightPath {
    private Long pathId;

    private Long devId;

    private String devType;

    private Long orgId;

    private Long sectId;

    private String areaCode1;

    private String devName;

    private String secName;

    private Long fnum1;

    private Long fnum2;

    private Long id;

    private String port01;

    private String zcodeType;

    private String txPort;

    private String devCode;

    private String secCode;

    private Date createTime;

    private String routeText;

    public Long getPathId() {
        return pathId;
    }

    public void setPathId(Long pathId) {
        this.pathId = pathId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType == null ? null : devType.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public String getAreaCode1() {
        return areaCode1;
    }

    public void setAreaCode1(String areaCode1) {
        this.areaCode1 = areaCode1 == null ? null : areaCode1.trim();
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName == null ? null : devName.trim();
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName == null ? null : secName.trim();
    }

    public Long getFnum1() {
        return fnum1;
    }

    public void setFnum1(Long fnum1) {
        this.fnum1 = fnum1;
    }

    public Long getFnum2() {
        return fnum2;
    }

    public void setFnum2(Long fnum2) {
        this.fnum2 = fnum2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPort01() {
        return port01;
    }

    public void setPort01(String port01) {
        this.port01 = port01 == null ? null : port01.trim();
    }

    public String getZcodeType() {
        return zcodeType;
    }

    public void setZcodeType(String zcodeType) {
        this.zcodeType = zcodeType == null ? null : zcodeType.trim();
    }

    public String getTxPort() {
        return txPort;
    }

    public void setTxPort(String txPort) {
        this.txPort = txPort == null ? null : txPort.trim();
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode == null ? null : devCode.trim();
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode == null ? null : secCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRouteText() {
        return routeText;
    }

    public void setRouteText(String routeText) {
        this.routeText = routeText == null ? null : routeText.trim();
    }
}