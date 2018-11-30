package com.ycnet.frms.bean;

import java.util.Date;

public class DeviceEntity {
    private Long codId;

    private String codCode;

    private String codName;

    private String codMac;

    private String codImei;

    private Long devId;
    
    private String devCode;

    private String codState;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;

    private Long hardId;

    private Date lastHardTime;
    
    private String locks;
    
    private String workState;//工作状态 01 正常 02 工作中 03 异常
    
    private String hardState;//硬件更新返回状态 0升级成功 1发送HTTP错误 2获取HTTP失败 3下载文件失败 4文件大小错误 5打开文件失败 6文件检验码错误' AFTER `work_state`; 


    public Long getCodId() {
        return codId;
    }

    public void setCodId(Long codId) {
        this.codId = codId;
    }

    public String getCodCode() {
        return codCode;
    }

    public void setCodCode(String codCode) {
        this.codCode = codCode == null ? null : codCode.trim();
    }

    public String getCodName() {
        return codName;
    }

    public void setCodName(String codName) {
        this.codName = codName == null ? null : codName.trim();
    }

    public String getCodMac() {
        return codMac;
    }

    public void setCodMac(String codMac) {
        this.codMac = codMac == null ? null : codMac.trim();
    }

    public String getCodImei() {
        return codImei;
    }

    public void setCodImei(String codImei) {
        this.codImei = codImei == null ? null : codImei.trim();
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getCodState() {
        return codState;
    }

    public void setCodState(String codState) {
        this.codState = codState == null ? null : codState.trim();
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

    public Long getHardId() {
        return hardId;
    }

    public void setHardId(Long hardId) {
        this.hardId = hardId;
    }

	public String getLocks() {
		return locks;
	}

	public void setLocks(String locks) {
		this.locks = locks;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public Date getLastHardTime() {
		return lastHardTime;
	}

	public void setLastHardTime(Date lastHardTime) {
		this.lastHardTime = lastHardTime;
	}

	public String getHardState() {
		return hardState;
	}

	public void setHardState(String hardState) {
		this.hardState = hardState;
	}
	
	
}