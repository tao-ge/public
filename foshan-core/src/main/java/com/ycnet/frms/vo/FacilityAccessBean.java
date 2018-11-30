package com.ycnet.frms.vo;

import com.ycnet.frms.bean.FacilityAccess;

public class FacilityAccessBean extends FacilityAccess{
	private String mobilePhone;//手机号码
	
	private String accessUserName;//授权人姓名
	
	private String accTime;
	
	private String accTimeEnd;
	
	

	public String getAccTime() {
		return accTime;
	}

	public void setAccTime(String accTime) {
		this.accTime = accTime;
	}

	public String getAccTimeEnd() {
		return accTimeEnd;
	}

	public void setAccTimeEnd(String accTimeEnd) {
		this.accTimeEnd = accTimeEnd;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAccessUserName() {
		return accessUserName;
	}

	public void setAccessUserName(String accessUserName) {
		this.accessUserName = accessUserName;
	}

	
	
	
	
}
