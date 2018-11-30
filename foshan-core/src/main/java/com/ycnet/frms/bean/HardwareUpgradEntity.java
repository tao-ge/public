package com.ycnet.frms.bean;

import java.util.Date;

public class HardwareUpgradEntity {
    private Long hardId;

    private String hardVersion;
    
    private Long hardCrc;
    
    private Long hardSize;
    
    private String hardType;

    private Date uploadTime;

    private Long userId;

    private String hardUrl;
    
    private String remark;
    
    private Long orgId;

    public Long getHardId() {
        return hardId;
    }

    public void setHardId(Long hardId) {
        this.hardId = hardId;
    }

    public String getHardVersion() {
        return hardVersion;
    }

    public void setHardVersion(String hardVersion) {
        this.hardVersion = hardVersion == null ? null : hardVersion.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHardUrl() {
        return hardUrl;
    }

    public void setHardUrl(String hardUrl) {
        this.hardUrl = hardUrl == null ? null : hardUrl.trim();
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getHardCrc() {
		return hardCrc;
	}

	public void setHardCrc(Long hardCrc) {
		this.hardCrc = hardCrc;
	}

	public Long getHardSize() {
		return hardSize;
	}

	public void setHardSize(Long hardSize) {
		this.hardSize = hardSize;
	}

	public String getHardType() {
		return hardType;
	}

	public void setHardType(String hardType) {
		this.hardType = hardType;
	}
}