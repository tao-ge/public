package com.ycnet.frms.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RemoteUnlockEntity {
    private Long applyId;

    private Long devId;

    private Long applyUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String isOpenlock;

    private Date openlockTime;

    private Long operatUser;

    private Date operatTime;

    private String remark;

    private Long orgId;
    
    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Long getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(Long applyUser) {
        this.applyUser = applyUser;
    }

    public String getIsOpenlock() {
        return isOpenlock;
    }

    public void setIsOpenlock(String isOpenlock) {
        this.isOpenlock = isOpenlock == null ? null : isOpenlock.trim();
    }

    public Date getOpenlockTime() {
        return openlockTime;
    }

    public void setOpenlockTime(Date openlockTime) {
        this.openlockTime = openlockTime;
    }

    public Long getOperatUser() {
        return operatUser;
    }

    public void setOperatUser(Long operatUser) {
        this.operatUser = operatUser;
    }

    public Date getOperatTime() {
        return operatTime;
    }

    public void setOperatTime(Date operatTime) {
        this.operatTime = operatTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}