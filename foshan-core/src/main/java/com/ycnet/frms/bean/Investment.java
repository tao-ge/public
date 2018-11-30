package com.ycnet.frms.bean;

import java.util.Date;
import java.util.List;

/**
 * 直熔信息实体类
* @desc: frms-core  
* @author: DZY  
* @createTime: 2017年12月16日 下午4:35:05  
* @history:  
* @version: v1.0
 */
public class Investment {
    private Long investId;

    private String investName;

    private String investCode;

    private Long devId;

    private String remark;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;
    
    private List<CablesectionInvest> cablesectionInvestList;

  
	public List<CablesectionInvest> getCablesectionInvestList() {
		return cablesectionInvestList;
	}

	public void setCablesectionInvestList(List<CablesectionInvest> cablesectionInvestList) {
		this.cablesectionInvestList = cablesectionInvestList;
	}

	public Long getInvestId() {
        return investId;
    }

    public void setInvestId(Long investId) {
        this.investId = investId;
    }

    public String getInvestName() {
        return investName;
    }

    public void setInvestName(String investName) {
        this.investName = investName == null ? null : investName.trim();
    }

    public String getInvestCode() {
        return investCode;
    }

    public void setInvestCode(String investCode) {
        this.investCode = investCode == null ? null : investCode.trim();
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}