package com.ycnet.frms.vo;

import java.util.List;

public class RemoteUnlockBean {
	
	private String did;
	
	private String expire_time;//授权时长
	
	private String platform;//设备所属平台
	
	private String userId;//开锁用户ID
	
	private String unlockingType;//开锁类型  1 远程开锁	2 扫码开锁		3 小程序开锁	4 机械开锁

	public String getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUnlockingType() {
		return unlockingType;
	}

	public void setUnlockingType(String unlockingType) {
		this.unlockingType = unlockingType;
	}
}
  
    