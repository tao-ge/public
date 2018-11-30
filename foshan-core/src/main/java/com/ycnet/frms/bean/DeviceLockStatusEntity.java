package com.ycnet.frms.bean;

import java.util.Date;

public class DeviceLockStatusEntity {
	private Long lockStatusId;

    private Long devId;

    private Long lockId01;

    private Long lockId02;

    private Date colTime;

    private Date rptTime;

    private String oprStyle;

    private String lockOpen01;

    private String doorOpen01;

    private String lockOpen02;

    private String doorOpen02;

    private String temp;

    private String battery;

    private String tilt;

    private String signals;

    private String alarmSign;

    private String dealSign;

    private String curStatus;

    private Long orgId;

    public Long getLockStatusId() {
        return lockStatusId;
    }

    public void setLockStatusId(Long lockStatusId) {
        this.lockStatusId = lockStatusId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Long getLockId01() {
        return lockId01;
    }

    public void setLockId01(Long lockId01) {
        this.lockId01 = lockId01;
    }

    public Long getLockId02() {
        return lockId02;
    }

    public void setLockId02(Long lockId02) {
        this.lockId02 = lockId02;
    }

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }

    public Date getRptTime() {
        return rptTime;
    }

    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }

    public String getOprStyle() {
        return oprStyle;
    }

    public void setOprStyle(String oprStyle) {
        this.oprStyle = oprStyle == null ? null : oprStyle.trim();
    }

    public String getLockOpen01() {
        return lockOpen01;
    }

    public void setLockOpen01(String lockOpen01) {
        this.lockOpen01 = lockOpen01 == null ? null : lockOpen01.trim();
    }

    public String getDoorOpen01() {
        return doorOpen01;
    }

    public void setDoorOpen01(String doorOpen01) {
        this.doorOpen01 = doorOpen01 == null ? null : doorOpen01.trim();
    }

    public String getLockOpen02() {
        return lockOpen02;
    }

    public void setLockOpen02(String lockOpen02) {
        this.lockOpen02 = lockOpen02 == null ? null : lockOpen02.trim();
    }

    public String getDoorOpen02() {
        return doorOpen02;
    }

    public void setDoorOpen02(String doorOpen02) {
        this.doorOpen02 = doorOpen02 == null ? null : doorOpen02.trim();
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery == null ? null : battery.trim();
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt == null ? null : tilt.trim();
    }

    public String getSignals() {
        return signals;
    }

    public void setSignals(String signals) {
        this.signals = signals == null ? null : signals.trim();
    }

    public String getAlarmSign() {
        return alarmSign;
    }

    public void setAlarmSign(String alarmSign) {
        this.alarmSign = alarmSign == null ? null : alarmSign.trim();
    }

    public String getDealSign() {
        return dealSign;
    }

    public void setDealSign(String dealSign) {
        this.dealSign = dealSign == null ? null : dealSign.trim();
    }

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus == null ? null : curStatus.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}