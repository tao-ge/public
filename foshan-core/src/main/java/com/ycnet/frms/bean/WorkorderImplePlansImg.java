package com.ycnet.frms.bean;

import java.util.Date;

public class WorkorderImplePlansImg {
    private Long planImgId;

    private String imgType;

    private Long plansId;

    private String imgUrl;

    private Date createTime;

    private Long createUser;

    private Long orgId;

    private String imgDesc;

    public Long getPlanImgId() {
        return planImgId;
    }

    public void setPlanImgId(Long planImgId) {
        this.planImgId = planImgId;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType == null ? null : imgType.trim();
    }

    public Long getPlansId() {
        return plansId;
    }

    public void setPlansId(Long plansId) {
        this.plansId = plansId;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc == null ? null : imgDesc.trim();
    }
}