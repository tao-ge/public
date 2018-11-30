package com.ycnet.frms.vo;

import com.ycnet.frms.bean.RemoteUnlockEntity;

public class RemoteUnlockEntityVo extends RemoteUnlockEntity{
	
    private String devCode;
    
    private String devName;
    
    private String applyUserName;
    
    private String operatUserName;
    
    private Long codId;

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

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getOperatUserName() {
		return operatUserName;
	}

	public void setOperatUserName(String operatUserName) {
		this.operatUserName = operatUserName;
	}

	public Long getCodId() {
		return codId;
	}

	public void setCodId(Long codId) {
		this.codId = codId;
	}
}
  
    