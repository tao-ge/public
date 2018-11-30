package com.ycnet.frms.bean;

import java.util.Date;

public class AlarmPushlog {
    private Long pushId;

    private Long alarmProcessId;

    private Long devId;

    private Date pushTime;

    private String pushSign;

    private String pushSituation;

    private Long orgId;

    public Long getPushId() {
        return pushId;
    }

    public void setPushId(Long pushId) {
        this.pushId = pushId;
    }

    public Long getAlarmProcessId() {
        return alarmProcessId;
    }

    public void setAlarmProcessId(Long alarmProcessId) {
        this.alarmProcessId = alarmProcessId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getPushSign() {
        return pushSign;
    }

    public void setPushSign(String pushSign) {
        this.pushSign = pushSign == null ? null : pushSign.trim();
    }

    public String getPushSituation() {
        return pushSituation;
    }

    public void setPushSituation(String pushSituation) {
        this.pushSituation = pushSituation == null ? null : pushSituation.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}