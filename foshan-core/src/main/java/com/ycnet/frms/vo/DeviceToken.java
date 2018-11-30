package com.ycnet.frms.vo;

import java.util.Date;

public class DeviceToken {

	private Date login;
	
	private Long userId;
	
	private String flag;
	
	private String platform;//设备所属平台
	
	private String lockType;//开锁类型  1 远程开锁	2 扫码开锁		3 小程序开锁	4 机械开锁
	
	public Date getLogin() {
		return login;
	}

	public void setLogin(Date login) {
		this.login = login;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getLockType() {
		return lockType;
	}

	public void setLockType(String lockType) {
		this.lockType = lockType;
	}
	
	
}
