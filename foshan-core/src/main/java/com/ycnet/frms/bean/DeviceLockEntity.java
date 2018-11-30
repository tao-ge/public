package com.ycnet.frms.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DeviceLockEntity {
    private Long lockId;

    private String lockName;

    private String lockCode;

    private Long codId;

    private Long devId;

    private Date regTime;

    private Long regUser;

    private Date rptTime;

    private String validateSign;

    private String lockWorkStatus;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    
    private String isUpload;
    
    private String uploadCode;
    
    private Long orgId;

    public Long getLockId() {
        return lockId;
    }

    public void setLockId(Long lockId) {
        this.lockId = lockId;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName == null ? null : lockName.trim();
    }

    public String getLockCode() {
        return lockCode;
    }

    public void setLockCode(String lockCode) {
        this.lockCode = lockCode == null ? null : lockCode.trim();
    }

    public Long getCodId() {
        return codId;
    }

    public void setCodId(Long codId) {
        this.codId = codId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Long getRegUser() {
        return regUser;
    }

    public void setRegUser(Long regUser) {
        this.regUser = regUser;
    }

    public Date getRptTime() {
        return rptTime;
    }

    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }

    public String getValidateSign() {
        return validateSign;
    }

    public void setValidateSign(String validateSign) {
        this.validateSign = validateSign == null ? null : validateSign.trim();
    }

    public String getLockWorkStatus() {
        return lockWorkStatus;
    }

    public void setLockWorkStatus(String lockWorkStatus) {
        this.lockWorkStatus = lockWorkStatus == null ? null : lockWorkStatus.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

	public Date getUploadTime() {
		return uploadTime;
	}

	public String getIsUpload() {
		return isUpload;
	}

	public String getUploadCode() {
		return uploadCode;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	public void setUploadCode(String uploadCode) {
		this.uploadCode = uploadCode;
	}
}