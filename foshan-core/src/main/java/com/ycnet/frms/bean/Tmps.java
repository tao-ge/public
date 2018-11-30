package com.ycnet.frms.bean;

import java.util.Date;

public class Tmps {
    private Long tmpId;

    private String devImei;

    private Date colTime;

    private String types;

    private String content;

    private Date rptTime;

    private String dealSign;

    private Date dealTime;

    private Long ownerId;

    public Long getTmpId() {
        return tmpId;
    }

    public void setTmpId(Long tmpId) {
        this.tmpId = tmpId;
    }

    public String getDevImei() {
        return devImei;
    }

    public void setDevImei(String devImei) {
        this.devImei = devImei;
    }

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRptTime() {
        return rptTime;
    }

    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }

    public String getDealSign() {
        return dealSign;
    }

    public void setDealSign(String dealSign) {
        this.dealSign = dealSign;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}