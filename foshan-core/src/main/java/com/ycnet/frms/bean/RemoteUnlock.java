package com.ycnet.frms.bean;

import java.math.BigDecimal;
import java.util.Date;

public class RemoteUnlock {
    private Long applyId;

    private Long devId;

    private Long applyUser;
    
    private String applyUserName;//申请人名称

    private Integer applyTime;

    private Long operatUser;
    
    private String operatUserName;//操作人名称

    private Date operatTime;

    private Long orgId;

    private String remark;
    
    private String devName;//设施名称
    
    private String devCode;//设施编码

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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getOperatUserName() {
		return operatUserName;
	}

	public void setOperatUserName(String operatUserName) {
		this.operatUserName = operatUserName;
	}

	public Integer getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Integer applyTime) {
		this.applyTime = applyTime;
	}
}