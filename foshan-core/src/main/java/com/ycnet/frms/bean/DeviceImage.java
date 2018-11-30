package com.ycnet.frms.bean;

import java.util.Date;

public class DeviceImage {
    private Long imageId;

    private String oprStyle;

    private String imageUrl;

    private String devImei;

    private Date colTime;

    private Date createTime;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getOprStyle() {
        return oprStyle;
    }

    public void setOprStyle(String oprStyle) {
        this.oprStyle = oprStyle == null ? null : oprStyle.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getDevImei() {
        return devImei;
    }

    public void setDevImei(String devImei) {
        this.devImei = devImei == null ? null : devImei.trim();
    }

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}