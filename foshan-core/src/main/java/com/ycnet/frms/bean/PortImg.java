package com.ycnet.frms.bean;

import java.util.Date;

public class PortImg {
    private Long portImgId;

    private Long devId;

    private String port01;

    private String imgDesc;

    private Date photoTime;

    private String imgUrl;

    private String flag;

    public Long getPortImgId() {
        return portImgId;
    }

    public void setPortImgId(Long portImgId) {
        this.portImgId = portImgId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getPort01() {
        return port01;
    }

    public void setPort01(String port01) {
        this.port01 = port01 == null ? null : port01.trim();
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