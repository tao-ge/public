package com.ycnet.frms.bean;

import java.util.Date;

/**
 * 光缆直熔信息实体类
* @desc: frms-core  
* @author: DZY  
* @createTime: 2017年12月16日 下午5:40:33  
* @history:  
* @version: v1.0
 */
public class CablesectionInvest {
    private Long sectInvestId;

    private Long fiberNum;

    private Long fiberNoStart;

    private Long fiberNoEnd;

    private Long devId;

    private Long upSectId;

    private Long downSectId;

    private Long investId;

    private String remark;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;

    public Long getSectInvestId() {
        return sectInvestId;
    }

    public void setSectInvestId(Long sectInvestId) {
        this.sectInvestId = sectInvestId;
    }

    public Long getFiberNum() {
        return fiberNum;
    }

    public void setFiberNum(Long fiberNum) {
        this.fiberNum = fiberNum;
    }

    public Long getFiberNoStart() {
        return fiberNoStart;
    }

    public void setFiberNoStart(Long fiberNoStart) {
        this.fiberNoStart = fiberNoStart;
    }

    public Long getFiberNoEnd() {
        return fiberNoEnd;
    }

    public void setFiberNoEnd(Long fiberNoEnd) {
        this.fiberNoEnd = fiberNoEnd;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Long getUpSectId() {
        return upSectId;
    }

    public void setUpSectId(Long upSectId) {
        this.upSectId = upSectId;
    }

    public Long getDownSectId() {
        return downSectId;
    }

    public void setDownSectId(Long downSectId) {
        this.downSectId = downSectId;
    }

    public Long getInvestId() {
        return investId;
    }

    public void setInvestId(Long investId) {
        this.investId = investId;
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