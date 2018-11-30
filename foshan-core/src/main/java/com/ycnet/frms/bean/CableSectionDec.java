package com.ycnet.frms.bean;

import java.util.Date;

public class CableSectionDec {
    private Long sectDecId;

    private Long sectId;

    private String devADec;

    private String devZDec;

    private String zgSectDec;

    private String zgRouteName;

    private Long fiberNo;

    private String fiberState;

    private Long modifyUser;

    private Date modifyTime;

    private Long createUser;

    private Date createTime;

    private Long orgId;

    private String zgRouteText;
    
    private String acode;
    
    private String zcode;
    
    private String routeText;
    
    private String zgRouteId;//资管光路ID

    public String getZgRouteId() {
		return zgRouteId;
	}

	public void setZgRouteId(String zgRouteId) {
		this.zgRouteId = zgRouteId;
	}

	public String getRouteText() {
		return routeText;
	}

	public void setRouteText(String routeText) {
		this.routeText = routeText;
	}

	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public String getZcode() {
		return zcode;
	}

	public void setZcode(String zcode) {
		this.zcode = zcode;
	}

	public Long getSectDecId() {
        return sectDecId;
    }

    public void setSectDecId(Long sectDecId) {
        this.sectDecId = sectDecId;
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public String getDevADec() {
        return devADec;
    }

    public void setDevADec(String devADec) {
        this.devADec = devADec;
    }

    public String getDevZDec() {
        return devZDec;
    }

    public void setDevZDec(String devZDec) {
        this.devZDec = devZDec;
    }

    public String getZgSectDec() {
        return zgSectDec;
    }

    public void setZgSectDec(String zgSectDec) {
        this.zgSectDec = zgSectDec;
    }

    public String getZgRouteName() {
        return zgRouteName;
    }

    public void setZgRouteName(String zgRouteName) {
        this.zgRouteName = zgRouteName;
    }

    public Long getFiberNo() {
        return fiberNo;
    }

    public void setFiberNo(Long fiberNo) {
        this.fiberNo = fiberNo;
    }


    public String getFiberState() {
        return fiberState;
    }

    public void setFiberState(String fiberState) {
        this.fiberState = fiberState;
    }

    public Long getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getZgRouteText() {
        return zgRouteText;
    }

    public void setZgRouteText(String zgRouteText) {
        this.zgRouteText = zgRouteText;
    }
}