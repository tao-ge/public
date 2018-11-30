package com.ycnet.frms.vo.mobile;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DeviceLockInfoMobile {
	
	private String name;//设施名-锁名称+锁
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rptTime;
	
	private String lockCode;
	
	private String codCode;
	
	private Long lockId;
	
	private String devCode;

	public String getName() {
		return name;
	}

	public String getLockCode() {
		return lockCode;
	}

	public String getCodCode() {
		return codCode;
	}

	public Long getLockId() {
		return lockId;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLockCode(String lockCode) {
		this.lockCode = lockCode;
	}

	public void setCodCode(String codCode) {
		this.codCode = codCode;
	}

	public void setLockId(Long lockId) {
		this.lockId = lockId;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public Date getRptTime() {
		return rptTime;
	}

	public void setRptTime(Date rptTime) {
		this.rptTime = rptTime;
	}
	
}
