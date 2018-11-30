package com.ycnet.frms.bean;

import java.util.Date;

public class CableSectionImage {
    private Long sectImageId;

    private Long sectId;

    private String imgUrl;

    private Date createTime;

    private Long createUser;

    public Long getSectImageId() {
        return sectImageId;
    }

    public void setSectImageId(Long sectImageId) {
        this.sectImageId = sectImageId;
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
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