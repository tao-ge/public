package com.ycnet.frms.bean;

import java.util.Date;

public class TransfLogEntity {
    private Long logId;

    private String logType;

    private String logTarget;

    private String logContent;

    private Date logDate;

    private String ip;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getLogTarget() {
        return logTarget;
    }

    public void setLogTarget(String logTarget) {
        this.logTarget = logTarget == null ? null : logTarget.trim();
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent == null ? null : logContent.trim();
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

}