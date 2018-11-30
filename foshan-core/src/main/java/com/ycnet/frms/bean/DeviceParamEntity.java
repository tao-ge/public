package com.ycnet.frms.bean;


public class DeviceParamEntity {
	
    private Long paramId;

    private String lowTempThd;

    private String highTempShd;

    private String batteryThd;

    private String tilt;

    private Long dormantTime;

    private Long selfCheckingTime;

    private Long reportTime;

    private Long lockAbnorTime;
    
    private Long pollTime;
    
    private String ip;

    private String port;
    
    private Long orgId;
    
    private String checkContent;
    
    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getLowTempThd() {
        return lowTempThd;
    }

    public void setLowTempThd(String lowTempThd) {
        this.lowTempThd = lowTempThd == null ? null : lowTempThd.trim();
    }

    public String getHighTempShd() {
        return highTempShd;
    }

    public void setHighTempShd(String highTempShd) {
        this.highTempShd = highTempShd == null ? null : highTempShd.trim();
    }

    public String getBatteryThd() {
        return batteryThd;
    }

    public void setBatteryThd(String batteryThd) {
        this.batteryThd = batteryThd == null ? null : batteryThd.trim();
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt == null ? null : tilt.trim();
    }

    public Long getDormantTime() {
        return dormantTime;
    }

    public void setDormantTime(Long dormantTime) {
        this.dormantTime = dormantTime;
    }

    public Long getSelfCheckingTime() {
        return selfCheckingTime;
    }

    public void setSelfCheckingTime(Long selfCheckingTime) {
        this.selfCheckingTime = selfCheckingTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

	public Long getLockAbnorTime() {
		return lockAbnorTime;
	}

	public void setLockAbnorTime(Long lockAbnorTime) {
		this.lockAbnorTime = lockAbnorTime;
	}

	public Long getPollTime() {
		return pollTime;
	}

	public void setPollTime(Long pollTime) {
		this.pollTime = pollTime;
	}

	public Long getReportTime() {
		return reportTime;
	}

	public void setReportTime(Long reportTime) {
		this.reportTime = reportTime;
	}

	public Long getOrgId() {
		return orgId;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
}