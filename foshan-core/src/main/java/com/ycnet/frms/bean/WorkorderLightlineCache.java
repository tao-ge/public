package com.ycnet.frms.bean;

import java.util.Date;

public class WorkorderLightlineCache {
    private Long lightlineId;

    private Long workorderId;

    private String routeName;

    private String srvName;

    private Long aroomDevId;

    private String aroomDevCode;

    private String aroomDevName;

    private Long zroomDevId;

    private String zroomDevCode;

    private String zroomDevName;

    private Long aDevId;

    private String aDevCode;

    private String aDevName;

    private Long zDevId;

    private String zDevCode;

    private String zDevName;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private String planstate;
    
    private String orderType;
    
    private String ids;//多选ID集合
    
    

    public String getPlanstate() {
		return planstate;
	}

	public void setPlanstate(String planstate) {
		this.planstate = planstate;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Long getLightlineId() {
        return lightlineId;
    }

    public void setLightlineId(Long lightlineId) {
        this.lightlineId = lightlineId;
    }

    public Long getWorkorderId() {
        return workorderId;
    }

    public void setWorkorderId(Long workorderId) {
        this.workorderId = workorderId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public String getSrvName() {
        return srvName;
    }

    public void setSrvName(String srvName) {
        this.srvName = srvName == null ? null : srvName.trim();
    }

    public Long getAroomDevId() {
        return aroomDevId;
    }

    public void setAroomDevId(Long aroomDevId) {
        this.aroomDevId = aroomDevId;
    }

    public String getAroomDevCode() {
        return aroomDevCode;
    }

    public void setAroomDevCode(String aroomDevCode) {
        this.aroomDevCode = aroomDevCode == null ? null : aroomDevCode.trim();
    }

    public String getAroomDevName() {
        return aroomDevName;
    }

    public void setAroomDevName(String aroomDevName) {
        this.aroomDevName = aroomDevName == null ? null : aroomDevName.trim();
    }

    public Long getZroomDevId() {
        return zroomDevId;
    }

    public void setZroomDevId(Long zroomDevId) {
        this.zroomDevId = zroomDevId;
    }

    public String getZroomDevCode() {
        return zroomDevCode;
    }

    public void setZroomDevCode(String zroomDevCode) {
        this.zroomDevCode = zroomDevCode == null ? null : zroomDevCode.trim();
    }

    public String getZroomDevName() {
        return zroomDevName;
    }

    public void setZroomDevName(String zroomDevName) {
        this.zroomDevName = zroomDevName == null ? null : zroomDevName.trim();
    }

    public Long getaDevId() {
        return aDevId;
    }

    public void setaDevId(Long aDevId) {
        this.aDevId = aDevId;
    }

    public String getaDevCode() {
        return aDevCode;
    }

    public void setaDevCode(String aDevCode) {
        this.aDevCode = aDevCode == null ? null : aDevCode.trim();
    }

    public String getaDevName() {
        return aDevName;
    }

    public void setaDevName(String aDevName) {
        this.aDevName = aDevName == null ? null : aDevName.trim();
    }

    public Long getzDevId() {
        return zDevId;
    }

    public void setzDevId(Long zDevId) {
        this.zDevId = zDevId;
    }

    public String getzDevCode() {
        return zDevCode;
    }

    public void setzDevCode(String zDevCode) {
        this.zDevCode = zDevCode == null ? null : zDevCode.trim();
    }

    public String getzDevName() {
        return zDevName;
    }

    public void setzDevName(String zDevName) {
        this.zDevName = zDevName == null ? null : zDevName.trim();
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

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(Long lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

}