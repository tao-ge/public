package com.ycnet.frms.bean;

import java.util.Date;

public class FacilityImgEntity {
    private Long devImgId;

    private Long devId;

    private String devCode;

    private String imgTypes;

    private String imgDesc;

    private Date photoTime;

    private String imgUrl;

    private String flag;

    private Long inspectId;

    public Long getDevImgId() {
        return devImgId;
    }

    public void setDevImgId(Long devImgId) {
        this.devImgId = devImgId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode == null ? null : devCode.trim();
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

    public Long getInspectId() {
        return inspectId;
    }

    public void setInspectId(Long inspectId) {
        this.inspectId = inspectId;
    }
}