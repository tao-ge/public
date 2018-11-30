package com.ycnet.frms.bean;

import java.util.Date;

public class WellImg {
    private Long wellImgId;

    private Long wellId;

    private String imgTypes;

    private String imgDesc;

    private Date photoTime;

    private String imgUrl;

    private String flag;

    public Long getWellImgId() {
        return wellImgId;
    }

    public void setWellImgId(Long wellImgId) {
        this.wellImgId = wellImgId;
    }

    public Long getWellId() {
        return wellId;
    }

    public void setWellId(Long wellId) {
        this.wellId = wellId;
    }

    public String getImgTypes() {
        return imgTypes;
    }

    public void setImgTypes(String imgTypes) {
        this.imgTypes = imgTypes == null ? null : imgTypes.trim();
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc == null ? null : imgDesc.trim();
    }

    public Date getPhotoTime() {
        return photoTime;
    }

    public void setPhotoTime(Date photoTime) {
        this.photoTime = photoTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}