package com.ycnet.frms.bean;

import java.util.Date;

/**
 * 调度工单设计方案dd_workorder_design
* @desc: opss  
* @author: DZY  
* @createTime: 2017年11月11日 下午3:51:54  
* @history:  
* @version: v1.0
 */
public class WorkorderDesign {
    private Long designId;

    private Long workorderId;

    private Long lightlineId;

    private String designName;

    private String isprimary;

    private String isused;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;
    
    private String planstate;//方案状态
    
    private String routeName;
    
    private Long orgId;//机构ID
    
    

    public String getPlanstate() {
		return planstate;
	}

	public void setPlanstate(String planstate) {
		this.planstate = planstate;
	}

	public Long getDesignId() {
        return designId;
    }

    public void setDesignId(Long designId) {
        this.designId = designId;
    }

    public Long getWorkorderId() {
        return workorderId;
    }

    public void setWorkorderId(Long workorderId) {
        this.workorderId = workorderId;
    }

    public Long getLightlineId() {
        return lightlineId;
    }

    public void setLightlineId(Long lightlineId) {
        this.lightlineId = lightlineId;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName == null ? null : designName.trim();
    }

    public String getIsprimary() {
        return isprimary;
    }

    public void setIsprimary(String isprimary) {
        this.isprimary = isprimary == null ? null : isprimary.trim();
    }

    public String getIsused() {
        return isused;
    }

    public void setIsused(String isused) {
        this.isused = isused == null ? null : isused.trim();
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

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}