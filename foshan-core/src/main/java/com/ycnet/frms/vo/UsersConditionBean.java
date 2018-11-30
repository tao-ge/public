package com.ycnet.frms.vo;

public class UsersConditionBean {

	private String userCode;
	
	private String userPwd;
	
	private String mobileImei;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getMobileImei() {
		return mobileImei;
	}

	public void setMobileImei(String mobileImei) {
		this.mobileImei = mobileImei;
	}

	public UsersConditionBean() {
		
	}

	public UsersConditionBean(String userCode, String userPwd, String mobileImei) {
		super();
		this.userCode = userCode;
		this.userPwd = userPwd;
		this.mobileImei = mobileImei;
	}
	
	
}
