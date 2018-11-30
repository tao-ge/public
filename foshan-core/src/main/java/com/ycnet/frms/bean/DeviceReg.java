package com.ycnet.frms.bean;

import java.util.Date;

public class DeviceReg {
    private Long regId;

    private Long devId;

    private String devCode;

    private String devName;

    private String devImei;

    private String devMac;

    private Date regTime;

    private Date rptTime;

    private String validateSign;

    private Long orgId;
    
    private String hwDeviceId;//华为平台设备ID
    
    private String fName;//设施名称
    
    private String devPlatform;//设备所属平台  
    
    private String devStatus;//设备状态
    
    private String redeployingType;
    
    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevImei() {
        return devImei;
    }

    public void setDevImei(String devImei) {
        this.devImei = devImei;
    }

    public String getDevMac() {
        return devMac;
    }

    public void setDevMac(String devMac) {
        this.devMac = devMac;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
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
        this.validateSign = validateSign;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

	public String getHwDeviceId() {
		return hwDeviceId;
	}

	public void setHwDeviceId(String hwDeviceId) {
		this.hwDeviceId = hwDeviceId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getDevPlatform() {
		return devPlatform;
	}

	public void setDevPlatform(String devPlatform) {
		this.devPlatform = devPlatform;
	}

	public String getDevStatus() {
		return devStatus;
	}

	public void setDevStatus(String devStatus) {
		this.devStatus = devStatus;
	}

	public String getRedeployingType() {
		return redeployingType;
	}

	public void setRedeployingType(String redeployingType) {
		this.redeployingType = redeployingType;
	}
	
}