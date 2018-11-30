package com.ycnet.frms.bean;

import java.util.Date;

public class DevicRegImg {
    private Long regImgId;

    private String devDid;

    private String regImgUrl;

    private Date createTime;

    public Long getRegImgId() {
        return regImgId;
    }

    public void setRegImgId(Long regImgId) {
        this.regImgId = regImgId;
    }

    public String getDevDid() {
        return devDid;
    }

    public void setDevDid(String devDid) {
        this.devDid = devDid == null ? null : devDid.trim();
    }

    public String getRegImgUrl() {
        return regImgUrl;
    }

    public void setRegImgUrl(String regImgUrl) {
        this.regImgUrl = regImgUrl == null ? null : regImgUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}