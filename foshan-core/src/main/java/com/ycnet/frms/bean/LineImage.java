package com.ycnet.frms.bean;

import java.util.Date;

public class LineImage {
    private Long lineImageId;

    private Long lineId;

    private String imgUrl;

    private Date createTime;

    private Long createUser;

    public Long getLineImageId() {
        return lineImageId;
    }

    public void setLineImageId(Long lineImageId) {
        this.lineImageId = lineImageId;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
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
}